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

package org.dasein.cloud.ci;

import java.util.ArrayList;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.dasein.cloud.CloudException;
import org.dasein.cloud.CloudProvider;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.ProviderContext;
import org.dasein.cloud.ResourceStatus;

public abstract class AbstractConvergedHttpLoadBalancer<T extends CloudProvider> implements ConvergedHttpLoadBalancerSupport {
    private T provider;

    public AbstractConvergedHttpLoadBalancer(@Nonnull T provider) {
        this.provider = provider;
    }

    protected final @Nonnull T getProvider() {
        return provider;
    }

    protected final @Nonnull ProviderContext getContext() throws CloudException {
        ProviderContext ctx = getProvider().getContext();

        if( ctx == null ) {
            throw new CloudException("No context was specified for this request");
        }
        return ctx;
    }

    @Override
    public @Nullable ConvergedHttpLoadBalancer getConvergedHttpLoadBalancer(@Nonnull String convergedHttpLoadBalancerName) throws CloudException, InternalException {
        throw new InternalException("Operation not supported for this cloud");
    }

    @Override
    public @Nonnull Iterable<ResourceStatus> listConvergedHttpLoadBalancerStatus() throws InternalException, CloudException {
        throw new InternalException("Operation not supported for this cloud");
    }

    @Override
    public String createConvergedHttpLoadBalancer(@Nonnull ConvergedHttpLoadBalancer withConvergedHttpLoadBalancerOptions) throws CloudException, InternalException {
        throw new InternalException("Operation not supported for this cloud");
    }

    @Override
    public void removeConvergedHttpLoadBalancers(@Nonnull String globalForwardingRuleName) throws CloudException, InternalException {
        throw new InternalException("Operation not supported for this cloud");
    }

}
