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

package org.dasein.cloud.identity;

import org.dasein.cloud.AbstractProviderService;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.CloudProvider;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.Requirement;

import javax.annotation.Nonnull;
import java.util.Locale;

/**
 * An abstract implementation of the Dasein Cloud Shell Key Support. Implementation classes override selected
 * methods to bootstrap concrete implementations.
 * @author Stas Maksimov (stas.maksimov@enstratius.com)
 * @since 2015.05
 * @version 2015.05 - initial version
 */
public abstract class AbstractShellKeySupport<T extends CloudProvider> extends AbstractProviderService<T> implements ShellKeySupport {

    protected AbstractShellKeySupport(T provider) {
        super(provider);
    }

    @Deprecated
    @Override
    public @Nonnull String getProviderTermForKeypair(@Nonnull Locale locale) {
        try {
            return getCapabilities().getProviderTermForKeypair(locale);
        }
        catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    @Deprecated
    @Override
    public Requirement getKeyImportSupport() throws CloudException, InternalException {
        return getCapabilities().identifyKeyImportRequirement();
    }
}
