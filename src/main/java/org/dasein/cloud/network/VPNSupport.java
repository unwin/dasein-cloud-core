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
import org.dasein.cloud.network.VPNConnection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("UnusedDeclaration")
public interface VPNSupport extends AccessControlledService {
    static public final ServiceAction ANY                = new ServiceAction("VPN:ANY");

    static public final ServiceAction ATTACH             = new ServiceAction("VPN:ATTACH");
    static public final ServiceAction CONNECT_GATEWAY    = new ServiceAction("VPN:CONNECT_GATEWAY");
    static public final ServiceAction CREATE_GATEWAY     = new ServiceAction("VPN:CREATE_GATEWAY");
    static public final ServiceAction CREATE_VPN         = new ServiceAction("VPN:CREATE_VPN");
    static public final ServiceAction DETACH             = new ServiceAction("VPN:DETACH");
    static public final ServiceAction DISCONNECT_GATEWAY = new ServiceAction("VPN:DISCONNECT_GATEWAY");
    static public final ServiceAction GET_GATEWAY        = new ServiceAction("VPN:GET_GATEWAY");
    static public final ServiceAction GET_VPN            = new ServiceAction("VPN:GET_VPN");
    static public final ServiceAction LIST_GATEWAY       = new ServiceAction("VPN:LIST_GATEWAY");
    static public final ServiceAction LIST_VPN           = new ServiceAction("VPN:LIST_VPN");
    static public final ServiceAction REMOVE_GATEWAY     = new ServiceAction("VPN:REMOVE_GATEWAY");
    static public final ServiceAction REMOVE_VPN         = new ServiceAction("VPN:REMOVE_VPN");

    public void attachToVLAN(@Nonnull String providerVpnId, @Nonnull String providerVlanId) throws CloudException, InternalException;
    
    public void connectToGateway(@Nonnull String providerVpnId, @Nonnull String toGatewayId) throws CloudException, InternalException;
    
    public VPNGateway connectToVPNGateway(String vpnName, String endpoint, String name, String description, VPNProtocol protocol, String sharedSecret, String cidr) throws CloudException, InternalException;
    
    @Deprecated
    public @Nonnull VPN createVPN(@Nullable String inProviderDataCenterId, @Nonnull String name, @Nonnull String description, @Nonnull VPNProtocol protocol) throws CloudException, InternalException;
    
    public @Nonnull VPN createVPN(@Nonnull VPNLaunchOptions vpnLaunchOptions) throws CloudException, InternalException;
    
    public @Nonnull VPNGateway createVPNGateway(@Nonnull String endpoint, @Nonnull String name, @Nonnull String description, @Nonnull VPNProtocol protocol, @Nonnull String bgpAsn) throws CloudException, InternalException;
    
    public void deleteVPN(@Nonnull String providerVpnId) throws CloudException, InternalException;
    
    public void deleteVPNGateway(@Nonnull String providerVPNGatewayId) throws CloudException, InternalException;
    
    public void detachFromVLAN(@Nonnull String providerVpnId, @Nonnull String providerVlanId) throws CloudException, InternalException;
    
    public void disconnectFromGateway(@Nonnull String providerVpnId, @Nonnull String fromGatewayId) throws CloudException, InternalException;

    public @Nonnull VPNCapabilities getCapabilities()throws CloudException, InternalException;
    
    public @Nullable VPNGateway getGateway(@Nonnull String gatewayId) throws CloudException, InternalException;
    
    public @Nullable VPN getVPN(@Nonnull String providerVpnId) throws CloudException, InternalException;

    @Deprecated
    public Requirement getVPNDataCenterConstraint() throws CloudException, InternalException;

    public @Nonnull Iterable<VPNConnection> listGatewayConnections(@Nonnull String toGatewayId) throws CloudException, InternalException;

    public @Nonnull Iterable<ResourceStatus> listGatewayStatus() throws CloudException, InternalException;

    public @Nonnull Iterable<VPNGateway> listGateways() throws CloudException, InternalException;

    public @Nonnull Iterable<VPNGateway> listGatewaysWithBgpAsn(@Nonnull String bgpAsn) throws CloudException, InternalException;

    public @Nonnull Iterable<VPNConnection> listVPNConnections(@Nonnull String toVpnId) throws CloudException, InternalException;

    public @Nonnull Iterable<ResourceStatus> listVPNStatus() throws CloudException, InternalException;

    public @Nonnull Iterable<VPN> listVPNs() throws CloudException, InternalException;

    @Deprecated
    public @Nonnull Iterable<VPNProtocol> listSupportedVPNProtocols() throws CloudException, InternalException;
    
    public boolean isSubscribed() throws CloudException, InternalException;
}
