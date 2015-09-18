package org.dasein.cloud.identity;

/**
 * @author Stas Maksimov (stas.maksimov@software.dell.com)
 * @since 2015.09
 */
public class CloudPolicyFilterOptions {
    private CloudPolicyType policyType;

    /**
     *
     * @param policyType
     * @return
     */
    public CloudPolicyFilterOptions getInstance(CloudPolicyType policyType) {
        CloudPolicyFilterOptions opts = new CloudPolicyFilterOptions();
        opts.policyType = policyType;
        return opts;
    }

    public CloudPolicyType getPolicyType() {
        return policyType;
    }
}
