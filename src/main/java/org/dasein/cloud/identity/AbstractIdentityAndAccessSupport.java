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

import org.dasein.cloud.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author Stas Maksimov (stas.maksimov@software.dell.com)
 * @since 2015.09
 */
public abstract class AbstractIdentityAndAccessSupport<T extends CloudProvider> extends AbstractProviderService<T> implements IdentityAndAccessSupport {

    protected AbstractIdentityAndAccessSupport(T provider) {
        super(provider);
    }

    @Nonnull
    @Override
    public Iterable<CloudGroup> listGroups(@Nullable String pathBase) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Groups cannot be listed in " + getProvider().getCloudName());
    }

    @Nonnull
    @Override
    public Iterable<CloudGroup> listGroupsForUser(@Nonnull String providerUserId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Groups cannot be listed for user in " + getProvider().getCloudName());
    }

    @Override
    public @Nullable CloudPolicy getPolicy(@Nonnull String providerPolicyId, @Nullable CloudPolicyFilterOptions options) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Policies cannot be retrieved in " + getProvider().getCloudName());
    }

    @Override
    public @Nonnull CloudPolicyRule[] getPolicyRules(@Nonnull String providerPolicyId, @Nullable CloudPolicyFilterOptions options) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Policy rules cannot be retrieved in " + getProvider().getCloudName());
    }

    @Override
    public @Nonnull Iterable<CloudPolicy> listPolicies(@Nonnull CloudPolicyFilterOptions opts) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Policies cannot be listed in " + getProvider().getCloudName());
    }

    @Override
    public @Nonnull Iterable<CloudUser> listUsersInGroup(@Nonnull String inProviderGroupId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Users cannot be listed for group in " + getProvider().getCloudName());
    }

    @Override
    public @Nonnull Iterable<CloudUser> listUsersInPath(@Nullable String pathBase) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Users cannot be listed in path in " + getProvider().getCloudName());
    }

    @Override
    public @Nonnull Iterable<CloudUser> listUsersForPolicy(@Nonnull String providerPolicyId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Users cannot be listed for policy in " + getProvider().getCloudName());
    }

    @Override
    public @Nonnull Iterable<CloudGroup> listGroupsForPolicy(@Nonnull String providerPolicyId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Groups cannot be listed in path in " + getProvider().getCloudName());
    }

    @Override
    public void removeAccessKey(@Nonnull String sharedKeyPart, @Nullable String providerUserId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Access keys cannot be removed in " + getProvider().getCloudName());
    }

    @Override
    public void enableAccessKey(@Nonnull String sharedKeyPart, @Nullable String providerUserId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Access keys cannot be enabled in " + getProvider().getCloudName());
    }

    @Override
    public void disableAccessKey(@Nonnull String sharedKeyPart, @Nullable String providerUserId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Access keys cannot be disabled in " + getProvider().getCloudName());
    }

    @Override
    public @Nonnull Iterable<AccessKey> listAccessKeys(@Nullable String providerUserId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Access keys cannot be listed in " + getProvider().getCloudName());
    }

    @Nonnull
    @Override
    public AccessKey createAccessKey(@Nonnull String providerUserId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Access keys cannot be created in " + getProvider().getCloudName());
    }

    @Override
    public void removeConsoleAccess(@Nonnull String providerUserId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Console access cannot be removed in " + getProvider().getCloudName());
    }

    @Override
    public void removeGroup(@Nonnull String providerGroupId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Groups cannot be removed in " + getProvider().getCloudName());
    }

    @Override
    public void removeUser(@Nonnull String providerUserId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Users cannot be removed in " + getProvider().getCloudName());
    }

    @Override
    public void removeUserFromGroup(@Nonnull String providerUserId, @Nonnull String providerGroupId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Users cannot be removed from groups in " + getProvider().getCloudName());
    }

    @Override
    public void modifyGroup(@Nonnull String providerGroupId, @Nullable String newGroupName, @Nullable String newPath) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Groups cannot be modified in " + getProvider().getCloudName());
    }

    @Override
    public void modifyUser(@Nonnull String providerUserId, @Nullable String newUserName, @Nullable String newPath) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Users cannot be modified in " + getProvider().getCloudName());
    }

    @Nonnull
    @Override
    public Iterable<String> listServices() throws CloudException, InternalException {
        throw new OperationNotSupportedException("Services cannot be listed in " + getProvider().getCloudName());
    }

    @Nonnull
    @Override
    public Iterable<ServiceAction> listServiceActions(@Nullable String forService) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Service actions cannot be listed in " + getProvider().getCloudName());
    }

    @Nonnull
    @Override
    public String createPolicy(@Nonnull CloudPolicyOptions options) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Policies cannot be created in " + getProvider().getCloudName());
    }

    @Override
    public void modifyPolicy(@Nonnull String providerPolicyId, @Nonnull CloudPolicyOptions options) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Policies cannot be modified in " + getProvider().getCloudName());
    }

    @Override
    public void removePolicy(@Nonnull String providerPolicyId, @Nullable CloudPolicyFilterOptions options) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Policies cannot be removed in " + getProvider().getCloudName());
    }

    @Override
    public void attachPolicyToUser(@Nonnull String providerPolicyId, @Nonnull String providerUserId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Policies cannot be attached to a user in " + getProvider().getCloudName());
    }

    @Override
    public void detachPolicyFromUser(@Nonnull String providerPolicyId, @Nonnull String providerUserId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Policies cannot be detached from a user in " + getProvider().getCloudName());
    }

    @Override
    public void attachPolicyToGroup(@Nonnull String providerPolicyId, @Nonnull String providerGroupId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Policies cannot be attached to a group in " + getProvider().getCloudName());
    }

    @Override
    public void detachPolicyFromGroup(@Nonnull String providerPolicyId, @Nonnull String providerGroupId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Policies cannot be detached from a group in " + getProvider().getCloudName());
    }

    @Override
    public void addUserToGroups(@Nonnull String providerUserId, @Nonnull String... providerGroupIds) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Users cannot be added to groups in " + getProvider().getCloudName());
    }

    @Nonnull
    @Override
    public CloudGroup createGroup(@Nonnull String groupName, @Nullable String path, boolean asAdminGroup) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Groups cannot be created in " + getProvider().getCloudName());
    }

    @Nonnull
    @Override
    public CloudUser createUser(@Nonnull String userName, @Nullable String path, @Nullable String... autoJoinGroupIds) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Users cannot be created in " + getProvider().getCloudName());
    }

    @Override
    public void enableConsoleAccess(@Nonnull String providerUserId, @Nonnull byte[] password) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Console access cannot be enabled in " + getProvider().getCloudName());
    }

    @Override
    public void updateConsoleAccess(@Nonnull String providerUserId, @Nonnull byte[] oldPassword, @Nonnull byte[] newPassword) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Console access cannot be updated in " + getProvider().getCloudName());
    }

    @Override
    public @Nullable String getConsoleUrl() throws CloudException, InternalException {
        throw new OperationNotSupportedException("Console URL cannot be retrieved in " + getProvider().getCloudName());
    }

    @Nullable
    @Override
    public CloudGroup getGroup(@Nonnull String providerGroupId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Groups cannot be retrieved in " + getProvider().getCloudName());
    }

    @Nullable
    @Override
    public CloudUser getUser(@Nonnull String providerUserId) throws CloudException, InternalException {
        throw new OperationNotSupportedException("Users cannot be retrieved in " + getProvider().getCloudName());
    }
}