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
import java.util.Arrays;

/**
 * @author Stas Maksimov (stas.maksimov@software.dell.com)
 * @since 2015.09
 */
@SuppressWarnings("unused")
public class CloudPolicyRule {
    private CloudPermission permission;
    private String[]        resources;
    private ServiceAction[] actions;
    private boolean         exceptActions;

    /**
     * Construct a new cloud policy rule instance
     * @param permission Does policy allow or deny the actions
     * @param actions Which action does the policy govern, all if empty
     * @return new cloud policy rule instance
     */
    public static @Nonnull CloudPolicyRule getInstance(
            @Nonnull CloudPermission permission,
            @Nullable ServiceAction ... actions) {
        return CloudPolicyRule.getInstance(permission, false, actions);
    }

    /**
     * @param resources Which resource(s) does the policy govern, any resource if not set
     * @return this
     */
    public @Nonnull CloudPolicyRule withResources(@Nullable String ... resources) {
        this.resources = resources;
        return this;
    }
    /**
     * Construct a new cloud policy rule instance
     * @param permission Does policy allow or deny the actions
     * @param exceptActions Indicates if the permission will apply to all actions except the ones defined in this rule
     * @param actions Which actions does the policy govern, all if empty
     * @return new cloud policy rule instance
     */
    public static @Nonnull CloudPolicyRule getInstance(@Nonnull CloudPermission permission, boolean exceptActions, @Nullable ServiceAction ... actions) {
        return new CloudPolicyRule(permission, exceptActions, actions);
    }

    private CloudPolicyRule(CloudPermission permission, boolean exceptActions, ServiceAction[] actions) {
        this.actions = actions;
        this.permission = permission;
        this.exceptActions = exceptActions;
    }

    /**
     * Permission of a cloud policy rule: allow or deny
     * @return Type of permission of a cloud policy rule
     */
    public @Nonnull CloudPermission getPermission() {
        return permission;
    }

    /**
     * Resource(s) to which the cloud police rule applies
     * @return resource identifier to which the policy rule applies, empty for any resource
     */
    public @Nonnull String[] getResources() {
        if( resources == null ) {
            resources = new String[0];
        }
        return resources;
    }

    /**
     * Actions that are allowed or denied on the defined resource
     * @return actions
     */
    public @Nonnull ServiceAction[] getActions() {
        if( actions == null ) {
            actions = new ServiceAction[0];
        }
        return actions;
    }

    /**
     * Indicates whether the permission applies to the actions of the rule or all other actions
     * @return {@code true} if the permission applies to all other actions, {@code false} if the permission applies to
     * actions defined in the rule
     */
    public boolean isExceptActions() {
        return exceptActions;
    }

    @Override
    public @Nonnull String toString() {
        return permission
                + ": "
                + (exceptActions ? "NOT " : "")
                + (getActions().length == 0 ? "ANY" : Arrays.toString(getActions()))
                + "/"
                + (getResources().length == 0 ? "ANY" : Arrays.toString(getResources()));
    }

}
