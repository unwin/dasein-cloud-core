package org.dasein.cloud.identity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;

/**
 * @author Stas Maksimov (stas.maksimov@software.dell.com)
 * @since 2015.09
 */
public class CloudPolicyRule {
    private CloudPermission permission;
    private String          resourceId;
    private ServiceAction[] actions;

    /**
     * Construct a new cloud policy rule instance
     * @param permission Does policy allow or deny the actions
     * @param actions Which actions does the policy govern, all if empty
     * @param resourceId Which resource does the policy govern, <code>null</code> for any resource
     * @return
     */
    public static CloudPolicyRule getInstance(
            @Nonnull CloudPermission permission,
            @Nonnull ServiceAction[] actions,
            @Nullable String resourceId) {
        return new CloudPolicyRule(actions, permission, resourceId);
    }

    private CloudPolicyRule(ServiceAction[] actions, CloudPermission permission, String resourceId) {
        this.actions = actions;
        this.permission = permission;
        this.resourceId = resourceId;
    }

    /**
     * Permission of a cloud policy rule: allow or deny
     * @return Type of permission of a cloud policy rule
     */

    public CloudPermission getPermission() {
        return permission;
    }

    public String getResourceId() {
        return resourceId;
    }

    public ServiceAction[] getActions() {
        return actions;
    }

    @Override
    public @Nonnull String toString() {
        return permission + ": " + (actions.length == 0 ? "*" : Arrays.toString(actions)) + "/" + resourceId;
    }

}
