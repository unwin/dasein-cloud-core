package org.dasein.cloud.network;

import java.util.Arrays;

import javax.annotation.Nonnull;

public class VPNGatewayCreateOptions {

    private String name = null;
    private String description = null;
    private VPNProtocol protocol = null;
    private String endpoint = null;

    private String bgpAsn = null;

    private String cidr = null;
    private String sharedSecret = null;
    private String vlanName = null;
    private String vpnName = null;

    private VPNGatewayCreateOptions() { }

    private VPNGatewayCreateOptions(@Nonnull String name, @Nonnull String description, @Nonnull VPNProtocol protocol, @Nonnull String endpoint) {
        this.name = name;
        this.description = description;
        this.protocol = protocol;
        this.endpoint = endpoint;
    }

    static public @Nonnull VPNGatewayCreateOptions getInstance(@Nonnull String name, @Nonnull String description, @Nonnull VPNProtocol protocol, @Nonnull String endpoint) {
        return new VPNGatewayCreateOptions(name, description, protocol, endpoint);
    }

    public @Nonnull VPNGatewayCreateOptions withCidr(@Nonnull String cidr) {
        this.cidr = cidr;
        return this;
    }

    public @Nonnull VPNGatewayCreateOptions withSharedSecret(@Nonnull String sharedSecret) {
        this.sharedSecret = sharedSecret;
        return this;
    }

    public @Nonnull VPNGatewayCreateOptions withBgpAsn(@Nonnull String bgpAsn) {
        this.bgpAsn = bgpAsn;
        return this;
    }

    public @Nonnull VPNGatewayCreateOptions withVlanName(@Nonnull String vlanName) {
        this.vlanName = vlanName;
        return this;
    }

    public @Nonnull VPNGatewayCreateOptions withVpnName(@Nonnull String vpnName) {
        this.vpnName = vpnName;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public VPNProtocol getProtocol() {
        return protocol;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getBgpAsn() {
        return bgpAsn;
    }

    public String getCidr() {
        return cidr;
    }

    public String getSharedSecret() {
        return sharedSecret;
    }

    public String getVlanName() {
        return vlanName;
    }

    public String getVpnName() {
        return vpnName;
    }

}
