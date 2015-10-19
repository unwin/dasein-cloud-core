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

package org.dasein.cloud;

import javax.annotation.Nonnull;

/**
 * Created by stas on 28/04/2015.
 */
public abstract class AbstractProviderService<T extends CloudProvider> {
    private T provider;

    protected AbstractProviderService(T provider) {
        this.provider = provider;
    }

    protected final @Nonnull T getProvider() {
        return provider;
    }

    /**
     * @return the current authentication context for any calls through this support object
     * @throws InternalException no context was set
     */
    protected final @Nonnull ProviderContext getContext() throws InternalException {
        ProviderContext ctx = getProvider().getContext();
        if( ctx == null ) {
            throw new InternalException("No context was specified for this request");
        }
        return ctx;
    }
}
