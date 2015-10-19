/**
 * Copyright (C) 2009-2015 Dell, Inc.
 * See annotations for authorship information
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */

package org.dasein.cloud.util.requester.fluent;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.CloudProvider;
import org.dasein.cloud.util.requester.*;
import org.dasein.cloud.util.requester.streamprocessors.*;
import org.json.JSONObject;
import org.w3c.dom.Document;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * DaseinParallelRequest class is a wrapper for Apache HTTP client. It unifies Dasein's REST calls to the clouds APIs.
 * It executes multiple http requests in parallel.
 *
 * <pre>
 * <code>
 *      List&lt;HttpUriRequest&gt; requestList = new ArrayList&lt;HttpUriRequest&gt;();
 *
 *      //...add HttpUriRequests to the list...
 *
 *      List&lt;String&gt; results = new DaseinParallelRequest(cloudProvider, httpClientBuilder, requestList).execute();
 *      List&lt;Document&gt; resultsAsDocument = new DaseinParallelRequest(cloudProvider, httpClientBuilder, requestList).withDocumentProcessor().execute();
 * </code>
 * </pre>
 *
 * @author Vlad Munthiu
 * */
public class DaseinParallelRequest implements CompositeParallelRequester {
    private CloudProvider provider;
    private HttpClientBuilder httpClientBuilder;
    private ArrayList<HttpUriRequest> httpUriRequests;

    /**
     * Constructs a new DaseinParallelRequest instance, ready to execute http calls to a specified list of Uri.
     *
     * @param provider the current CloudProvider instance
     * @param httpClientBuilder HTTP client builder
     * @param httpUriRequests a list of HTTP URI requests
     **/
    public DaseinParallelRequest(@Nonnull CloudProvider provider, @Nonnull HttpClientBuilder httpClientBuilder, @Nonnull ArrayList<HttpUriRequest> httpUriRequests){
        if(provider == null)
            throw new IllegalArgumentException("Parameter provider cannot be null.");

        if(httpClientBuilder == null)
            throw new IllegalArgumentException("Parameter httpClientBuilder cannot be null.");

        if(httpUriRequests == null)
            throw new IllegalArgumentException("Parameter httpUriRequest cannot be null");

        this.provider = provider;
        this.httpClientBuilder = httpClientBuilder;
        this.httpUriRequests = httpUriRequests;
    }

    /**
     * Constructs a instance of a DaseinParallelRequestExecutor with a XML stream processor that, once the HTTP requests have been
     * finished, will perform a deserialization of the XML responses into the specified type T and return all results as a List of T.
     * <pre>
     *     List&lt;DaseinDriverType&gt; results = new DaseinParallelRequest(cloudProvider, httpClientBuilder, httpUriRequests).withXmlProcessor(DaseinDriverType.class).execute();
     * </pre>
     * @param classType the type of the expected model
     * @return a list of instances of the classType type representing the responses XML
     **/
    @Override
    public <T> ParallelRequester<T> withXmlProcessor(@Nonnull Class<T> classType) {
        if(classType == null)
            throw new IllegalArgumentException("Parameter classType cannot be null");

        return new DaseinParallelRequestExecutor<T>(this.provider, this.httpClientBuilder, this.httpUriRequests,
                new DaseinResponseHandler<T>(new XmlStreamToObjectProcessor(), classType));
    }

    /**
     * Constructs a instance of a DaseinParallelRequestExecutor with a XML stream processor that, once the HTTP requests have been
     * finished, will perform a deserialization of the XML responses into the specified type T and return all results as a List of T.
     * A valid instance of a DriverToCoreMapper should be passed in, so that a mapping from a driver model type ( T ) to a Dasein Core
     * model( V ) to be performed after the response is received.
     *
     * <pre>
     *     List&lt;DaseinCoreType&gt; results = new DaseinParallelRequest(cloudProvider, httpClientBuilder, httpUriRequests)
     *                  .withXmlProcessor(new DriverToCoreMapper&lt;DaseinDriverType, DaseinCoreType&gt;() {
     *                           &#64;Override
     *                           public DaseinCoreType mapFrom(DaseinDriverType entity) {
     *                                  //map entity to a new instance of DaseinCoreType
     *                           }
     *                      DaseinDriverType.class).execute();
     * </pre>
     *
     * @param mapper an implementation of {@link DriverToCoreMapper} interface
     * @param classType the type of the expected model
     * @return an instance of the V type which should be a Dasien Core type.
     **/
    @Override
    public <T, V> ParallelRequester<V> withXmlProcessor(@Nonnull DriverToCoreMapper<T, V> mapper, @Nonnull Class<T> classType) {
        if(mapper == null)
            throw new IllegalArgumentException("Parameter mapper cannot be null");

        if(classType == null)
            throw new IllegalArgumentException("Parameter classType cannot be null");

        return new DaseinParallelRequestExecutor<V>(this.provider, this.httpClientBuilder, this.httpUriRequests,
                new DaseinResponseHandlerWithMapper<T, V>(new XmlStreamToObjectProcessor(), mapper, classType));
    }

    /**
     * Constructs a instance of a DaseinParallelRequestExecutor with a JSON stream processor that, once the HTTP requests have been
     * finished, will perform a deserialization of the JSON responses into the specified type T and return all results as a List of T.
     *
     * <pre>
     *     List&lt;DaseinDriverType&gt; results = new DaseinParallelRequest(cloudProvider, httpClientBuilder, httpUriRequests).withJsonProcessor(DaseinDriverType.class).execute();
     * </pre>
     *
     * @param classType the type of the expected model
     * @return a list of instances of the classType type representing the responses JSON
     **/
    @Override
    public <T> ParallelRequester<T> withJsonProcessor(@Nonnull Class<T> classType) {
        if(classType == null)
            throw new IllegalArgumentException("Parameter classType cannot be null");

        return new DaseinParallelRequestExecutor<T>(this.provider, this.httpClientBuilder, this.httpUriRequests,
                new DaseinResponseHandler<T>(new JsonStreamToObjectProcessor(), classType));
    }

    /**
     * Constructs a instance of a DaseinParallelRequestExecutor with a JSON stream processor that, once the HTTP requests have been
     * finished, will perform a deserialization of the JSON responses into the specified type T and return all results as a List of T.
     * A valid instance of a DriverToCoreMapper should be passed in, so that a mapping from a driver model type ( T ) to a Dasein Core
     * model( V ) to be performed after the response is received.
     *
     * <pre>
     *     List&lt;DaseinCoreType&gt; results = new DaseinParallelRequest(cloudProvider, httpClientBuilder, httpUriRequests)
     *                  .withJsonProcessor(new DriverToCoreMapper&lt;DaseinDriverType, DaseinCoreType&gt;() {
     *                           &#64;Override
     *                           public DaseinCoreType mapFrom(DaseinDriverType entity) {
     *                                  //map entity to a new instance of DaseinCoreType
     *                           }
     *                      DaseinDriverType.class).execute();
     * </pre>
     *
     * @param mapper an implementation of {@link DriverToCoreMapper} interface
     * @param classType the type of the expected model
     * @return an instance of the V type which should be a Dasien Core type.
     **/
    @Override
    public <T, V> ParallelRequester<V> withJsonProcessor(@Nonnull DriverToCoreMapper<T, V> mapper, @Nonnull Class<T> classType) {
        if(mapper == null)
            throw new IllegalArgumentException("Parameter mapper cannot be null");

        if(classType == null)
            throw new IllegalArgumentException("Parameter classType cannot be null");

        return new DaseinParallelRequestExecutor<V>(this.provider, this.httpClientBuilder, this.httpUriRequests,
                new DaseinResponseHandlerWithMapper<T, V>(new JsonStreamToObjectProcessor(), mapper, classType));
    }

    /**
     * Constructs a instance of a DaseinRequestExecutor with a stream processor that, once the HTTP requests have been
     * finished, will try to parse the response stream into a valid XML Document object and return all results as a List of Document objects.
     *
     * <pre>
     *     List&lt;Document&gt; documentResults = new DaseinParallelRequest(cloudProvider, httpClientBuilder, httpUriRequests).withDocumentProcessor().execute();
     * </pre>
     **/
    @Override
    public <T> DaseinParallelRequestExecutor<Document> withDocumentProcessor() {
        return new DaseinParallelRequestExecutor<Document>(this.provider, this.httpClientBuilder, this.httpUriRequests,
                new DaseinResponseHandler<Document>(new StreamToDocumentProcessor(), Document.class));
    }

    /**
     * Constructs a instance of a DaseinRequestExecutor with a stream processor that, once the HTTP requests have been
     * finished, will try to parse the response stream into a valid JSONObject object and return all results as a List of JSONObject objects.
     *
     * <pre>
     *     List&lt;JSONObject&gt; jsonResults = new DaseinParallelRequest(cloudProvider, httpClientBuilder, httpUriRequests).withJSONObjectProcessor().execute();
     * </pre>
     **/
    @Override
    public <T> DaseinParallelRequestExecutor<JSONObject> withJSONObjectProcessor() {
        return new DaseinParallelRequestExecutor<JSONObject>(this.provider, this.httpClientBuilder, this.httpUriRequests,
                new DaseinResponseHandler<JSONObject>(new StreamToJSONObjectProcessor(), JSONObject.class));
    }

    /**
     * Executes a HTTP requests using a string processor for the response.
     *
     * <pre>
     *     List&lt;String&gt; results = new DaseinRequest(cloudProvider, httpClientBuilder, httpUriRequests).execute();
     * </pre>
     *
     * @return a string representing the response of the current HTTP call.
     **/
    @Override
    public List<String> execute() throws CloudException {
        return new DaseinParallelRequestExecutor<String>(this.provider, this.httpClientBuilder, this.httpUriRequests,
                new DaseinResponseHandler<String>(new StreamToStringProcessor(), String.class)).execute();
    }
}
