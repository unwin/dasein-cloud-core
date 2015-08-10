package org.dasein.cloud.util.requester.fluent;

import org.dasein.cloud.CloudException;
import org.dasein.cloud.util.requester.DaseinParallelRequestExecutor;
import org.dasein.cloud.util.requester.DaseinRequestExecutor;
import org.dasein.cloud.util.requester.DriverToCoreMapper;
import org.json.JSONObject;
import org.w3c.dom.Document;

import java.util.List;

/**
 * Created by vmunthiu on 6/17/2015.
 */
public interface CompositeParallelRequester extends ParallelRequester<String> {
    <T> ParallelRequester<T> withXmlProcessor(Class<T> classType);
    <T, V> ParallelRequester<V> withXmlProcessor(DriverToCoreMapper<T, V> mapper, Class<T> classType);
    <T> ParallelRequester<T> withJsonProcessor(Class<T> classType);
    <T, V> ParallelRequester<V> withJsonProcessor(DriverToCoreMapper<T, V> mapper, Class<T> classType);
    <T> DaseinParallelRequestExecutor<Document> withDocumentProcessor();
    <T> DaseinParallelRequestExecutor<JSONObject> withJSONObjectProcessor();
    List<String> execute() throws CloudException;
}
