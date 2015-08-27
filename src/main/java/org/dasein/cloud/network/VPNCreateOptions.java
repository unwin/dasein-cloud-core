package org.dasein.cloud.network;

import java.util.Arrays;

import javax.annotation.Nonnull;

public class VPNCreateOptions {

    private String providerVlanId = null;
    private String providerDataCenterId = null;
    private String[] labels = new String[0];
    private String name = null;
    private String description = null;
    private VPNProtocol protocol = null;

    private VPNCreateOptions() { }

    private VPNCreateOptions(@Nonnull String name, @Nonnull String description, @Nonnull VPNProtocol protocol) {
        this.name = name;
        this.description = description;
        this.protocol = protocol;
    }

    static public @Nonnull VPNCreateOptions getInstance(@Nonnull String name, @Nonnull String description, @Nonnull VPNProtocol protocol) {
        return new VPNCreateOptions(name, description, protocol);
    }

    /**
     * Specifies any labels to be added to the VPN
     * @param labels one or more labels to be added to new VPN
     * @return this
     */
    public @Nonnull VPNCreateOptions withLabels(String... labels) {
        if (labels != null) {
            this.labels  = Arrays.copyOf(labels, labels.length);
        }
        return this;
    }

    public @Nonnull VPNCreateOptions withProviderVlanId(@Nonnull String providerVlanId) {
        this. providerVlanId = providerVlanId;
        return this;
    }

    public @Nonnull VPNCreateOptions withProviderDataCenterId(@Nonnull String providerDataCenterId) {
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

    public VPNProtocol getProtocol() {
        return protocol;
    }

    public String getDescription() {
        return description;
    }
}
