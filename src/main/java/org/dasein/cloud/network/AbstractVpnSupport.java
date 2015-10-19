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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.dasein.cloud.AbstractProviderService;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.CloudProvider;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.OperationNotSupportedException;
import org.dasein.cloud.Requirement;
import org.dasein.cloud.ResourceStatus;


public abstract class AbstractVpnSupport<T extends CloudProvider> extends AbstractProviderService<T> implements VpnSupport {

    protected AbstractVpnSupport(T provider) {
        super(provider);
    }

    public void attachToVlan(@Nonnull String providerVpnId, @Nonnull String providerVlanId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("attachToVlan is not currently implemented in " + getProvider().getCloudName());
    }

    public void connectToGateway(@Nonnull String providerVpnId, @Nonnull String toGatewayId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("connectToGateway is not currently implemented in " + getProvider().getCloudName());
    }

    @Deprecated
    public final @Nonnull Vpn createVpn(@Nullable String inProviderDataCenterId, @Nonnull String name, @Nonnull String description, @Nonnull VpnProtocol protocol) throws CloudException, InternalException {
        return createVpn(VpnCreateOptions.getInstance(name, description, protocol));
    }

    public @Nonnull Vpn createVpn(@Nonnull VpnCreateOptions vpnLaunchOptions) throws CloudException, InternalException {
        throw new OperationNotSupportedException("createVpn is not currently implemented in " + getProvider().getCloudName());
    }

    @Deprecated
    public final @Nonnull VpnGateway createVpnGateway(@Nonnull String endpoint, @Nonnull String name, @Nonnull String description, @Nonnull VpnProtocol protocol, @Nonnull String bgpAsn) throws CloudException, InternalException {
        return createVpnGateway(VpnGatewayCreateOptions.getInstance(name, description, protocol, endpoint).withBgpAsn(bgpAsn));
    }

    public void deleteVpn(@Nonnull String providerVpnId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("deleteVpn is not currently implemented in " + getProvider().getCloudName());
    }

    public void deleteVpnGateway(@Nonnull String providerVpnGatewayId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("deleteVpnGateway is not currently implemented in " + getProvider().getCloudName());
    }

    public void detachFromVlan(@Nonnull String providerVpnId, @Nonnull String providerVlanId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("detachFromVlan is not currently implemented in " + getProvider().getCloudName());
    }

    public void disconnectFromGateway(@Nonnull String providerVpnId, @Nonnull String fromGatewayId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("disconnectFromGateway is not currently implemented in " + getProvider().getCloudName());
    }

    public @Nullable VpnGateway getGateway(@Nonnull String gatewayId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("getGateway is not currently implemented in " + getProvider().getCloudName());
    }

    public @Nullable VpnGateway getVpnGateway(@Nonnull String gatewayId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("getVpnGateway is not currently implemented in " + getProvider().getCloudName());
    }

    public @Nullable Vpn getVpn(@Nonnull String providerVpnId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("getVpn is not currently implemented in " + getProvider().getCloudName());
    }

    @Deprecated
    public Requirement getVpnDataCenterConstraint() throws CloudException, InternalException {
        throw new OperationNotSupportedException("getVpnDataCenterConstraint is not currently implemented in " + getProvider().getCloudName());
    }

    public @Nonnull Iterable<VpnConnection> listGatewayConnections(@Nonnull String toGatewayId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("listGatewayConnections is not currently implemented in " + getProvider().getCloudName());
    }

    public @Nonnull Iterable<ResourceStatus> listGatewayStatus() throws CloudException, InternalException {
        throw new OperationNotSupportedException("listGatewayStatus is not currently implemented in " + getProvider().getCloudName());
    }

    public @Nonnull Iterable<VpnGateway> listGateways() throws CloudException, InternalException {
        throw new OperationNotSupportedException("listGateways is not currently implemented in " + getProvider().getCloudName());
    }

    public @Nonnull Iterable<VpnGateway> listGatewaysWithBgpAsn(@Nonnull String bgpAsn) throws CloudException, InternalException {
        throw new OperationNotSupportedException("listGatewaysWithBgpAsn is not currently implemented in " + getProvider().getCloudName());
    }

    public @Nonnull Iterable<VpnConnection> listVpnConnections(@Nonnull String toVpnId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("listVpnConnections is not currently implemented in " + getProvider().getCloudName());
    }

    public @Nonnull Iterable<ResourceStatus> listVpnStatus() throws CloudException, InternalException {
        throw new OperationNotSupportedException("listVpnStatus is not currently implemented in " + getProvider().getCloudName());
    }

    public @Nonnull Iterable<Vpn> listVpns() throws CloudException, InternalException {
        throw new OperationNotSupportedException("listVpns is not currently implemented in " + getProvider().getCloudName());
    }

    @Deprecated
    public @Nonnull Iterable<VpnProtocol> listSupportedVpnProtocols() throws CloudException, InternalException {
        throw new OperationNotSupportedException("listSupportedVpnProtocols is not currently implemented in " + getProvider().getCloudName());
    }
}
