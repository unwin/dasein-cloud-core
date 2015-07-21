package org.dasein.cloud.util.requester.fluent;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.CloudProvider;
import org.dasein.cloud.util.requester.*;
import org.dasein.cloud.util.requester.streamprocessors.*;
import org.json.JSONObject;
import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vmunthiu on 6/17/2015.
 */
public class DaseinParallelRequest implements CompositeParallelRequester {
    private CloudProvider provider;
    private HttpClientBuilder httpClientBuilder;
    private ArrayList<HttpUriRequest> httpUriRequests;

    public DaseinParallelRequest(CloudProvider provider, HttpClientBuilder httpClientBuilder, ArrayList<HttpUriRequest> httpUriRequests){
        this.provider = provider;
        this.httpClientBuilder = httpClientBuilder;
        this.httpUriRequests = httpUriRequests;
    }

    @Override
    public <T> ParallelRequester<T> withXmlProcessor(Class<T> classType) {
        return new DaseinParallelRequestExecutor<T>(this.provider, this.httpClientBuilder, this.httpUriRequests,
                new DaseinResponseHandler<T>(new XmlStreamToObjectProcessor(), classType));
    }

    @Override
    public <T, V> ParallelRequester<V> withXmlProcessor(DriverToCoreMapper<T, V> mapper, Class<T> classType) {
        return new DaseinParallelRequestExecutor<V>(this.provider, this.httpClientBuilder, this.httpUriRequests,
                new DaseinResponseHandlerWithMapper<T, V>(new XmlStreamToObjectProcessor(), mapper, classType));
    }

    @Override
    public <T> ParallelRequester<T> withJsonProcessor(Class<T> classType) {
        return new DaseinParallelRequestExecutor<T>(this.provider, this.httpClientBuilder, this.httpUriRequests,
                new DaseinResponseHandler<T>(new JsonStreamToObjectProcessor(), classType));
    }

    @Override
    public <T, V> ParallelRequester<V> withJsonProcessor(DriverToCoreMapper<T, V> mapper, Class<T> classType) {
        return new DaseinParallelRequestExecutor<V>(this.provider, this.httpClientBuilder, this.httpUriRequests,
                new DaseinResponseHandlerWithMapper<T, V>(new JsonStreamToObjectProcessor(), mapper, classType));
    }

    @Override
    public <T> DaseinParallelRequestExecutor<Document> withDocumentProcessor() {
        return new DaseinParallelRequestExecutor<Document>(this.provider, this.httpClientBuilder, this.httpUriRequests,
                new DaseinResponseHandler<Document>(new StreamToDocumentProcessor(), Document.class));
    }

    @Override
    public <T> DaseinParallelRequestExecutor<JSONObject> withJSONObjectProcessor() {
        return new DaseinParallelRequestExecutor<JSONObject>(this.provider, this.httpClientBuilder, this.httpUriRequests,
                new DaseinResponseHandler<JSONObject>(new StreamToJSONObjectProcessor(), JSONObject.class));
    }

    @Override
    public List<String> execute() throws CloudException {
        return new DaseinParallelRequestExecutor<String>(this.provider, this.httpClientBuilder, this.httpUriRequests,
                new DaseinResponseHandler<String>(new StreamToStringProcessor(), String.class)).execute();
    }
}
