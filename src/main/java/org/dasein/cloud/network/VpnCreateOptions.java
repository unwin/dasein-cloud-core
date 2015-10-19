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

package org.dasein.cloud.network;

import java.util.Arrays;

import javax.annotation.Nonnull;

public class VpnCreateOptions {

    private String providerVlanId;
    private String providerDataCenterId;
    private String[] labels;
    private String name;
    private String description;
    private VpnProtocol protocol;

    private VpnCreateOptions() { }

    private VpnCreateOptions(@Nonnull String name, @Nonnull String description, @Nonnull VpnProtocol protocol) {
        this.name = name;
        this.description = description;
        this.protocol = protocol;
    }

    static public @Nonnull VpnCreateOptions getInstance(@Nonnull String name, @Nonnull String description, @Nonnull VpnProtocol protocol) {
        return new VpnCreateOptions(name, description, protocol);
    }

    /**
     * Specifies any labels to be added to the VPN
     * @param labels one or more labels to be added to new VPN
     * @return this
     */
    public @Nonnull VpnCreateOptions withLabels(String... labels) {
        if (labels != null) {
            this.labels  = Arrays.copyOf(labels, labels.length);
        }
        return this;
    }

    public @Nonnull VpnCreateOptions withProviderVlanId(@Nonnull String providerVlanId) {
        this. providerVlanId = providerVlanId;
        return this;
    }

    public @Nonnull VpnCreateOptions withProviderDataCenterId(@Nonnull String providerDataCenterId) {
        this.providerDataCenterId = providerDataCenterId;
        return this;
    }

    public String getProviderVlanId() {
        return providerVlanId;
    }

    public String getProviderDataCenterId() {
        return providerDataCenterId;
    }

    public String[] getLabels() {
        if (null == labels) {
            return new String[0];
        }
        return labels;
    }

    public String getName() {
        return name;
    }

    public VpnProtocol getProtocol() {
        return protocol;
    }

    public String getDescription() {
        return description;
    }
}
