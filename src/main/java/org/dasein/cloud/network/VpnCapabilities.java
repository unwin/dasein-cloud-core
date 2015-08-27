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

import org.dasein.cloud.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Describes the capabilities of a region within a cloud for a specific account.
 * <p>Created by George Reese: 2/27/14 3:01 PM</p>
 * @author George Reese
 * @version 2014.03 initial version
 * @since 2014.03
 */
public interface VpnCapabilities extends Capabilities {
    /**
     * Lists the protocols supported for VPNs in the cloud
     * @return list of Protocol types
     * @throws CloudException an error occurred in the cloud identifying this requirement
     * @throws InternalException an error occurred within the Dasein Cloud implementation identifying this requirement
     */
    @Nonnull Iterable<VpnProtocol> listSupportedVPNProtocols() throws CloudException, InternalException;

    /**
     * Indicates whether a VPN is tied to a specific VLAN
     * @return the requirement level for VPN VLAN
     * @throws CloudException an error occurred in the cloud identifying this requirement
     * @throws InternalException an error occurred within the Dasein Cloud implementation identifying this requirement
     */
    @Nonnull Requirement getVPNVLANConstraint() throws CloudException, InternalException;

    /**
     * Returns the visible scope of a VPN or null if not applicable for the specific cloud
     * @return the visible scope of a VPN
     */
    @Nullable VisibleScope getVpnVisibleScope();

}
