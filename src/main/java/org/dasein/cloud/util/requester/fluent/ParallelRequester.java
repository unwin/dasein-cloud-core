package org.dasein.cloud.util.requester.fluent;

import org.dasein.cloud.CloudException;

import java.util.List;

/**
 * Created by vmunthiu on 6/17/2015.
 */
public interface ParallelRequester<T> {
    List<T> execute() throws CloudException;
}
