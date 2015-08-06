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


public abstract class AbstractVPNSupport<T extends CloudProvider> extends AbstractProviderService<T> implements VPNSupport {

    protected AbstractVPNSupport(T provider) {
        super(provider);
    }

    public void attachToVLAN(@Nonnull String providerVpnId, @Nonnull String providerVlanId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("attachToVLAN is not currently implemented in " + getProvider().getCloudName());
    }

    public void connectToGateway(@Nonnull String providerVpnId, @Nonnull String toGatewayId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("connectToGateway is not currently implemented in " + getProvider().getCloudName());
    }

    public VPNGateway connectToVPNGateway(String vpnName, String endpoint, String name, String description, VPNProtocol protocol, String sharedSecret, String cidr) throws CloudException, InternalException {
        throw new OperationNotSupportedException("connectToVPNGateway is not currently implemented in " + getProvider().getCloudName());
    }

    @Deprecated
    public @Nonnull VPN createVPN(@Nullable String inProviderDataCenterId, @Nonnull String name, @Nonnull String description, @Nonnull VPNProtocol protocol) throws CloudException, InternalException {
        throw new OperationNotSupportedException("createVPN is not currently implemented in " + getProvider().getCloudName());
    }

    public @Nonnull VPN createVPN(@Nonnull VpnLaunchOptions vpnLaunchOptions) throws CloudException, InternalException {
        throw new OperationNotSupportedException("createVPN is not currently implemented in " + getProvider().getCloudName());
    }

    public @Nonnull VPNGateway createVPNGateway(@Nonnull String endpoint, @Nonnull String name, @Nonnull String description, @Nonnull VPNProtocol protocol, @Nonnull String bgpAsn) throws CloudException, InternalException {
        throw new OperationNotSupportedException("createVPNGateway is not currently implemented in " + getProvider().getCloudName());
    }

    public void deleteVPN(@Nonnull String providerVpnId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("deleteVPN is not currently implemented in " + getProvider().getCloudName());
    }

    public void deleteVPNGateway(@Nonnull String providerVPNGatewayId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("deleteVPNGateway is not currently implemented in " + getProvider().getCloudName());
    }

    public void detachFromVLAN(@Nonnull String providerVpnId, @Nonnull String providerVlanId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("detachFromVLAN is not currently implemented in " + getProvider().getCloudName());
    }

    public void disconnectFromGateway(@Nonnull String providerVpnId, @Nonnull String fromGatewayId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("disconnectFromGateway is not currently implemented in " + getProvider().getCloudName());
    }

    public @Nullable VPNGateway getGateway(@Nonnull String gatewayId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("getGateway is not currently implemented in " + getProvider().getCloudName());
    }

    public @Nullable VPNGateway getVPNGateway(@Nonnull String gatewayId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("getVPNGateway is not currently implemented in " + getProvider().getCloudName());
    }

    public @Nullable VPN getVPN(@Nonnull String providerVpnId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("getVPN is not currently implemented in " + getProvider().getCloudName());
    }

    @Deprecated
    public Requirement getVPNDataCenterConstraint() throws CloudException, InternalException {
        throw new OperationNotSupportedException("getVPNDataCenterConstraint is not currently implemented in " + getProvider().getCloudName());
    }

    public @Nonnull Iterable<VPNConnection> listGatewayConnections(@Nonnull String toGatewayId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("listGatewayConnections is not currently implemented in " + getProvider().getCloudName());
    }

    public @Nonnull Iterable<ResourceStatus> listGatewayStatus() throws CloudException, InternalException {
        throw new OperationNotSupportedException("listGatewayStatus is not currently implemented in " + getProvider().getCloudName());
    }

    public @Nonnull Iterable<VPNGateway> listGateways() throws CloudException, InternalException {
        throw new OperationNotSupportedException("listGateways is not currently implemented in " + getProvider().getCloudName());
    }

    public @Nonnull Iterable<VPNGateway> listGatewaysWithBgpAsn(@Nonnull String bgpAsn) throws CloudException, InternalException {
        throw new OperationNotSupportedException("listGatewaysWithBgpAsn is not currently implemented in " + getProvider().getCloudName());
    }

    public @Nonnull Iterable<VPNConnection> listVPNConnections(@Nonnull String toVpnId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("listVPNConnections is not currently implemented in " + getProvider().getCloudName());
    }

    public @Nonnull Iterable<ResourceStatus> listVPNStatus() throws CloudException, InternalException {
        throw new OperationNotSupportedException("listVPNStatus is not currently implemented in " + getProvider().getCloudName());
    }

    public @Nonnull Iterable<VPN> listVPNs() throws CloudException, InternalException {
        throw new OperationNotSupportedException("listVPNs is not currently implemented in " + getProvider().getCloudName());
    }

    @Deprecated
    public @Nonnull Iterable<VPNProtocol> listSupportedVPNProtocols() throws CloudException, InternalException {
        throw new OperationNotSupportedException("listSupportedVPNProtocols is not currently implemented in " + getProvider().getCloudName());
    }
}
