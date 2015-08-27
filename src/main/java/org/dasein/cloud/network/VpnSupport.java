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

import org.dasein.cloud.AccessControlledService;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.Requirement;
import org.dasein.cloud.ResourceStatus;
import org.dasein.cloud.identity.ServiceAction;
import org.dasein.cloud.network.VpnConnection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("UnusedDeclaration")
public interface VpnSupport extends AccessControlledService {
    static final ServiceAction ANY                = new ServiceAction("VPN:ANY");

    static final ServiceAction ATTACH             = new ServiceAction("VPN:ATTACH");
    static final ServiceAction CONNECT_GATEWAY    = new ServiceAction("VPN:CONNECT_GATEWAY");
    static final ServiceAction CREATE_GATEWAY     = new ServiceAction("VPN:CREATE_GATEWAY");
    static final ServiceAction CREATE_VPN         = new ServiceAction("VPN:CREATE_VPN");
    static final ServiceAction DETACH             = new ServiceAction("VPN:DETACH");
    static final ServiceAction DISCONNECT_GATEWAY = new ServiceAction("VPN:DISCONNECT_GATEWAY");
    static final ServiceAction GET_GATEWAY        = new ServiceAction("VPN:GET_GATEWAY");
    static final ServiceAction GET_VPN            = new ServiceAction("VPN:GET_VPN");
    static final ServiceAction LIST_GATEWAY       = new ServiceAction("VPN:LIST_GATEWAY");
    static final ServiceAction LIST_VPN           = new ServiceAction("VPN:LIST_VPN");
    static final ServiceAction REMOVE_GATEWAY     = new ServiceAction("VPN:REMOVE_GATEWAY");
    static final ServiceAction REMOVE_VPN         = new ServiceAction("VPN:REMOVE_VPN");

    void attachToVlan(@Nonnull String providerVpnId, @Nonnull String providerVlanId) throws CloudException, InternalException;

    void connectToGateway(@Nonnull String providerVpnId, @Nonnull String toGatewayId) throws CloudException, InternalException;

    @Deprecated
    @Nonnull Vpn createVpn(@Nullable String inProviderDataCenterId, @Nonnull String name, @Nonnull String description, @Nonnull VpnProtocol protocol) throws CloudException, InternalException;

    @Nonnull Vpn createVpn(@Nonnull VpnCreateOptions vpnLaunchOptions) throws CloudException, InternalException;

    @Deprecated
    @Nonnull VpnGateway createVpnGateway(@Nonnull String endpoint, @Nonnull String name, @Nonnull String description, @Nonnull VpnProtocol protocol, @Nonnull String bgpAsn) throws CloudException, InternalException;

    @Nonnull VpnGateway createVpnGateway(@Nonnull VpnGatewayCreateOptions vpnGatewayCreateOptions) throws CloudException, InternalException;

    void deleteVpn(@Nonnull String providerVpnId) throws CloudException, InternalException;

    void deleteVpnGateway(@Nonnull String providerVpnGatewayId) throws CloudException, InternalException;

    void detachFromVlan(@Nonnull String providerVpnId, @Nonnull String providerVlanId) throws CloudException, InternalException;

    void disconnectFromGateway(@Nonnull String providerVpnId, @Nonnull String fromGatewayId) throws CloudException, InternalException;

    @Nonnull VpnCapabilities getCapabilities()throws CloudException, InternalException;

    @Nullable VpnGateway getGateway(@Nonnull String gatewayId) throws CloudException, InternalException;

    @Nullable Vpn getVpn(@Nonnull String providerVpnId) throws CloudException, InternalException;

    @Deprecated
    Requirement getVpnDataCenterConstraint() throws CloudException, InternalException;

    @Nonnull Iterable<VpnConnection> listGatewayConnections(@Nonnull String toGatewayId) throws CloudException, InternalException;

    @Nonnull Iterable<ResourceStatus> listGatewayStatus() throws CloudException, InternalException;

    @Nonnull Iterable<VpnGateway> listGateways() throws CloudException, InternalException;

    @Nonnull Iterable<VpnGateway> listGatewaysWithBgpAsn(@Nonnull String bgpAsn) throws CloudException, InternalException;

    @Nonnull Iterable<VpnConnection> listVpnConnections(@Nonnull String toVpnId) throws CloudException, InternalException;

    @Nonnull Iterable<ResourceStatus> listVpnStatus() throws CloudException, InternalException;

    @Nonnull Iterable<Vpn> listVpns() throws CloudException, InternalException;

    @Deprecated
    @Nonnull Iterable<VpnProtocol> listSupportedVpnProtocols() throws CloudException, InternalException;

    boolean isSubscribed() throws CloudException, InternalException;
}
