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

package org.dasein.cloud.identity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author Stas Maksimov (stas.maksimov@software.dell.com)
 * @since 2015.09
 */
public class CloudPolicyFilterOptions {
    private CloudPolicyType[] policyTypes;
    private String providerUserId;
    private String providerGroupId;

    /**
     * Create an instance of policy filtering options, when {@code policyTypes} includes {@link CloudPolicyType#INLINE_POLICY}
     * the {@link CloudPolicyFilterOptions#providerUserId} or {@link CloudPolicyFilterOptions#providerGroupId} are required.
     * @param policyTypes which policy types to include
     * @return an instance of policy filtering options
     */
    public static CloudPolicyFilterOptions getInstance(@Nonnull CloudPolicyType ... policyTypes) {
        CloudPolicyFilterOptions opts = new CloudPolicyFilterOptions();
        opts.policyTypes = policyTypes;
        return opts;
    }

    /**
     * Get policy types requested in this filtering options
     * @return cloud policy type
     */
    public CloudPolicyType[] getPolicyTypes() {
        return policyTypes;
    }

    /**
     * Define a {@code providerUserId} for which to select inline policies, required for {@link CloudPolicyType#INLINE_POLICY}
     * @param providerUserId the unique ID of the user against which the policy search will be made
     * @return this
     */
    public CloudPolicyFilterOptions withProviderUserId(@Nullable String providerUserId) {
        this.providerUserId = providerUserId;
        return this;
    }

    /**
     * Get the {@code providerUserId} for which to select inline policies, required for {@link CloudPolicyType#INLINE_POLICY}
     * @return a user ID
     */
    public String getProviderUserId() {
        return providerUserId;
    }

    /**
     * Define a {@code providerGroupId} for which to select inline policies, required for {@link CloudPolicyType#INLINE_POLICY}
     * @param providerGroupId the unique ID of the group against which the policy search will be made
     * @return this
     */
    public CloudPolicyFilterOptions withProviderGroupId(@Nullable String providerGroupId) {
        this.providerGroupId = providerGroupId;
        return this;
    }

    /**
     * Get the {@code providerGroupId} for which to select inline policies, required for {@link CloudPolicyType#INLINE_POLICY}
     * @return a user ID
     */
    public String getProviderGroupId() {
        return providerGroupId;
    }
}
