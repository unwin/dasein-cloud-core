package org.dasein.cloud.network;

import org.dasein.cloud.AbstractProviderService;
import org.dasein.cloud.CloudProvider;


public abstract class AbstractVPNSupport<T extends CloudProvider> extends AbstractProviderService<T> implements VPNSupport {

    protected AbstractVPNSupport(T provider) {
        super(provider);
    }

}
