package org.dasein.cloud.util.requester;

import org.apache.http.HttpHost;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.CloudProvider;

import java.util.Properties;

/**
 * Created by vmunthiu on 6/17/2015.
 */
public abstract class AbstractDaseinRequestExecutor<T> {
    protected CloudProvider provider;
    protected HttpClientBuilder httpClientBuilder;
    private ResponseHandler<T> responseHandler;

    protected AbstractDaseinRequestExecutor(CloudProvider provider, HttpClientBuilder httpClientBuilder, ResponseHandler<T> responseHandler){
        this.provider = provider;
        this.httpClientBuilder = httpClientBuilder;
        this.responseHandler = responseHandler;
    }

    protected T execute(HttpUriRequest httpUriRequest) throws CloudException {
        httpClientBuilder = setProxyIfRequired(httpClientBuilder);

        try {
            CloseableHttpClient httpClient = this.httpClientBuilder.build();
            try {
                return httpClient.execute(httpUriRequest, this.responseHandler);
            }
            finally{
                httpClient.close();
            }
        } catch (Exception e){
            throw translateException(e);
        }
    }

    protected CloudException translateException(Exception exception) {
        if(exception instanceof  CloudResponseException) {
            CloudResponseException e = (CloudResponseException) exception;
            return new CloudException(e.getErrorType(), e.getHttpCode(), e.getProviderCode(), e.getMessage());
        } else {
            return new CloudException(exception.getMessage());
        }		         }

    protected HttpClientBuilder setProxyIfRequired(HttpClientBuilder httpClientBuilder)
    {
        HttpProxyConfig httpProxyConfig = getHttpProxyConfigData();
        if(httpProxyConfig != null){
            HttpHost proxy = new HttpHost(httpProxyConfig.getHost(), httpProxyConfig.getPort());
            DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
            httpClientBuilder.setRoutePlanner(routePlanner);
        }

        return httpClientBuilder;
    }

    private HttpProxyConfig getHttpProxyConfigData()
    {
        Properties p = provider.getContext().getCustomProperties();

        HttpProxyConfig httpProxyConfig = null;
        if( p != null && p.getProperty("proxyHost") != null && p.getProperty("proxyPort") != null) {
            if(p.getProperty("proxyPort").length() > 0) {
                httpProxyConfig = new HttpProxyConfig(p.getProperty("proxyHost"), Integer.parseInt(p.getProperty("proxyPort")));
            }
        }
        else {
            p = System.getProperties();
            if (p != null && p.getProperty("proxyHost") != null && p.getProperty("proxyPort") != null) {
                if (p.getProperty("proxyPort").length() > 0) {
                    httpProxyConfig = new HttpProxyConfig(p.getProperty("proxyHost"), Integer.parseInt(p.getProperty("proxyPort")));
                }
            }
        }
        return httpProxyConfig;
    }

    private class HttpProxyConfig
    {
        private String host;
        private Integer port;

        public HttpProxyConfig(String host, Integer port)
        {
            this.host = host;
            this.port = port;
        }

        public String getHost() {
            return host;
        }

        public Integer getPort() {
            return port;
        }
    }
}
