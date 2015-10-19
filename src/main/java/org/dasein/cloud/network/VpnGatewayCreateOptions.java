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

public class VpnGatewayCreateOptions {

    private String name = null;
    private String description = null;
    private VpnProtocol protocol = null;
    private String endpoint = null;

    private String bgpAsn = null;

    private String cidr = null;
    private String sharedSecret = null;
    private String vlanName = null;
    private String vpnName = null;

    private VpnGatewayCreateOptions() { }

    private VpnGatewayCreateOptions(@Nonnull String name, @Nonnull String description, @Nonnull VpnProtocol protocol, @Nonnull String endpoint) {
        this.name = name;
        this.description = description;
        this.protocol = protocol;
        this.endpoint = endpoint;
    }

    static public @Nonnull VpnGatewayCreateOptions getInstance(@Nonnull String name, @Nonnull String description, @Nonnull VpnProtocol protocol, @Nonnull String endpoint) {
        return new VpnGatewayCreateOptions(name, description, protocol, endpoint);
    }

    public @Nonnull VpnGatewayCreateOptions withCidr(@Nonnull String cidr) {
        this.cidr = cidr;
        return this;
    }

    public @Nonnull VpnGatewayCreateOptions withSharedSecret(@Nonnull String sharedSecret) {
        this.sharedSecret = sharedSecret;
        return this;
    }

    public @Nonnull VpnGatewayCreateOptions withBgpAsn(@Nonnull String bgpAsn) {
        this.bgpAsn = bgpAsn;
        return this;
    }

    public @Nonnull VpnGatewayCreateOptions withVlanName(@Nonnull String vlanName) {
        this.vlanName = vlanName;
        return this;
    }

    public @Nonnull VpnGatewayCreateOptions withVpnName(@Nonnull String vpnName) {
        this.vpnName = vpnName;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public VpnProtocol getProtocol() {
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
