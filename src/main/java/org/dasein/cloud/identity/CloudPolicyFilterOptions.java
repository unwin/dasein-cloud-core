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
     * @param policyTypes
     * @return
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
