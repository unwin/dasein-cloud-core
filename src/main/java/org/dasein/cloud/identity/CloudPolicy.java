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
 * Represents a policy tied to a user or group in the cloud.
 * @author George Reese (george.reese@imaginary.com)
 * @since 2012.02
 * @version 2012.02
 */
public class CloudPolicy {
    /**
     * Constract a new inline or managed cloud policy object
     * @param providerPolicyId Provider policy ID, required for both types of polices
     * @param name Policy name, required for both types of policies
     * @param description Policy description, optional
     * @param permission Does policy allow or deny an action, required for inline policies and if not set the object is assumed to represent a managed policy
     * @param action Which action does the policy govern, optional for inline policies
     * @param resourceId Which resource does the policy govern, for inline policies <code>null</code> for any resource
     * @return policy object
     */
    static public CloudPolicy getInstance(@Nonnull String providerPolicyId,
                                          @Nonnull String name,
                                          @Nullable String description,
                                          @Nullable CloudPermission permission,
                                          @Nullable ServiceAction action,
                                          @Nullable String resourceId) {
        CloudPolicy policy = new CloudPolicy();

        policy.providerPolicyId = providerPolicyId;
        policy.name = name;
        policy.description = description;
        policy.permission = permission;
        policy.action = action;
        policy.resourceId = resourceId;
        return policy;
    }

    /**
     * Constract a new managed cloud policy object
     * @param providerPolicyId Provider policy ID, required for both types of polices
     * @param name Policy name, required for both types of policies
     * @param description Policy description, optional
     * @return Object representing a managed policy
     */
    static public CloudPolicy getInstance(@Nonnull String providerPolicyId,
                                          @Nonnull String name,
                                          @Nullable String description) {
        CloudPolicy policy = new CloudPolicy();

        policy.providerPolicyId = providerPolicyId;
        policy.name = name;
        policy.description = description;
        return policy;
    }

    private ServiceAction   action;
    private String          name;
    private String          description;
    private CloudPermission permission;
    private String          providerPolicyId;
    private String          resourceId;

    private CloudPolicy() { }

    public @Nullable ServiceAction getAction() {
        return action;
    }

    public @Nonnull String getName() {
        return name;
    }

    public @Nullable String getDescription() {
        return description;
    }

    /**
     * Permission of an inline policy
     * @return Type of permission of an inline policy or <code>null</code> if policy is a managed kind
     */
    public @Nullable CloudPermission getPermission() {
        return permission;
    }

    public @Nonnull String getProviderPolicyId() {
        return providerPolicyId;
    }

    public @Nullable String getResourceId() {
        return resourceId;
    }

    public boolean isManaged() {
        return getPermission() == null;
    }

    @Override
    public @Nonnull String toString() {
        return (isManaged() ? name : (permission + "/" + action + "/" + resourceId)) + " [#" + providerPolicyId + "]";
    }
}
