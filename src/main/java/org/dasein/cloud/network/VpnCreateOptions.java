package org.dasein.cloud.network;

import java.util.Arrays;

import javax.annotation.Nonnull;

public class VpnCreateOptions {

    private String providerVlanId = null;
    private String providerDataCenterId = null;
    private String[] labels = new String[0];
    private String name = null;
    private String description = null;
    private VpnProtocol protocol = null;

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
