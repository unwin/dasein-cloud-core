package org.dasein.cloud.util.requester;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.CloudProvider;
import org.dasein.cloud.util.requester.fluent.ParallelRequester;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by vmunthiu on 6/17/2015.
 */
public class DaseinParallelRequestExecutor<T> extends AbstractDaseinRequestExecutor<T> implements ParallelRequester<T> {
    private ArrayList<HttpUriRequest> httpUriRequests;

    public DaseinParallelRequestExecutor(CloudProvider provider, HttpClientBuilder httpClientBuilder, ArrayList<HttpUriRequest> httpUriRequests, ResponseHandler<T> responseHandler){
        super(provider, httpClientBuilder, responseHandler);
        this.httpUriRequests = httpUriRequests;
    }

    public List<T> execute() throws CloudException {
        final HttpClientBuilder clientBuilder = setProxyIfRequired(httpClientBuilder);

        List<T> results = new ArrayList<T>();
        List<Callable<T>> tasks = new ArrayList<Callable<T>>();
        for (final HttpUriRequest httpUriRequest : httpUriRequests) {
            tasks.add(new Callable<T>() {
                @Override
                public T call() throws Exception {
                    return execute(httpUriRequest);
                }
            });
        }

        try {
            ExecutorService executorService = Executors.newFixedThreadPool(httpUriRequests.size());
            List<Future<T>> futures = executorService.invokeAll(tasks);
            for (Future<T> future : futures) {
                T result = future.get();
                results.add(result);
            }
            return results;
        } catch (Exception e) {
            throw new CloudException(e.getMessage());
        }
    }
}
