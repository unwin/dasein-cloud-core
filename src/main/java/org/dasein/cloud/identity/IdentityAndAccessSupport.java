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

import org.dasein.cloud.AccessControlledService;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Support for identifying and managing identities at the cloud provider for service like AWS IAM.
 * @author George Reese (george.reese@imaginary.com)
 * @since 2012.02
 * @version 2012.02
 * @version 2013.04 added methods to remove policies
 */
@SuppressWarnings("unused")
public interface IdentityAndAccessSupport extends AccessControlledService {
    ServiceAction ANY                 = new ServiceAction("IAM:ANY");
    ServiceAction ADD_GROUP_ACCESS    = new ServiceAction("IAM:ADD_GROUP_ACCESS");
    ServiceAction ADD_USER_ACCESS     = new ServiceAction("IAM:ADD_USER_ACCESS");
    ServiceAction CREATE_USER         = new ServiceAction("IAM:CREATE_USER");
    ServiceAction CREATE_GROUP        = new ServiceAction("IAM:CREATE_GROUP");
    ServiceAction DISABLE_API         = new ServiceAction("IAM:DISABLE_API");
    ServiceAction DISABLE_CONSOLE     = new ServiceAction("IAM:DISABLE_CONSOLE");
    ServiceAction DROP_FROM_GROUP     = new ServiceAction("IAM:DROP_FROM_GROUP");
    ServiceAction ENABLE_API          = new ServiceAction("IAM:ENABLE_API");
    ServiceAction ENABLE_CONSOLE      = new ServiceAction("IAM:ENABLE_CONSOLE");
    ServiceAction GET_ACCESS_KEY      = new ServiceAction("IAM:GET_ACCESS_KEY");
    ServiceAction GET_GROUP           = new ServiceAction("IAM:GET_GROUP");
    ServiceAction GET_GROUP_POLICY    = new ServiceAction("IAM:GET_GROUP_POLICY");
    ServiceAction GET_USER            = new ServiceAction("IAM:GET_USER");
    ServiceAction GET_USER_POLICY     = new ServiceAction("IAM:GET_USER_POLICY");
    ServiceAction JOIN_GROUP          = new ServiceAction("IAM:JOIN_GROUP");
    ServiceAction LIST_ACCESS_KEYS    = new ServiceAction("IAM:LIST_ACCESS_KEYS");
    ServiceAction LIST_GROUP          = new ServiceAction("IAM:LIST_GROUP");
    ServiceAction LIST_USER           = new ServiceAction("IAM:LIST_USER");
    ServiceAction REMOVE_GROUP        = new ServiceAction("IAM:REMOVE_GROUP");
    ServiceAction REMOVE_GROUP_ACCESS = new ServiceAction("IAM:REMOVE_GROUP_ACCESS");
    ServiceAction REMOVE_USER         = new ServiceAction("IAM:REMOVE_USER");
    ServiceAction REMOVE_USER_ACCESS  = new ServiceAction("IAM:REMOVE_USER_ACCESS");
    ServiceAction UPDATE_GROUP        = new ServiceAction("IAM:UPDATE_GROUP");
    ServiceAction UPDATE_USER         = new ServiceAction("IAM:UPDATE_USER");

    /**
     * Provides access to meta-data about identity and access capabilities in the current region of this cloud.
     * @return a description of the features supported by this region of this cloud
     * @throws InternalException an error occurred within the Dasein Cloud API implementation
     * @throws CloudException an error occurred within the cloud provider
     */
    @Nonnull IdentityAndAccessCapabilities getCapabilities() throws CloudException, InternalException;

    /**
     * Adds an existing user to the specified existing groups.
     * @param providerUserId the unique cloud provider ID for the user to add
     * @param providerGroupIds one or more unique cloud provider IDs for the groups to be added to
     * @throws CloudException an error occurred with the cloud provider adding the user
     * @throws InternalException an error occurred within the Dasein Cloud implementation while adding the user
     */
    void addUserToGroups(@Nonnull String providerUserId, @Nonnull String ... providerGroupIds) throws CloudException, InternalException;

    /**
     * Creates a new group with the cloud provider belonging in the specified path.
     * @param groupName the name of the new group
     * @param path the parent path into which the group is placed
     * @param asAdminGroup if the group should be granted full admin privileges (see {@link IdentityAndAccessCapabilities#supportsAccessControls()})
     * @return the newly created group
     * @throws CloudException an error occurred with the cloud provider while creating the group
     * @throws InternalException an error occurred within the Dasein Cloud implementation while creating the group
     */
    @Nonnull CloudGroup createGroup(@Nonnull String groupName, @Nullable String path, boolean asAdminGroup) throws CloudException, InternalException;

    /**
     * Creates a new user with the cloud provider belonging in the specified path.
     * @param userName the name of the new user to create
     * @param path the parent path into which the user is placed
     * @param autoJoinGroupIds any groups the user should automatically be joined to
     * @return the newly created cloud user (even if the auto-joins failed!)
     * @throws CloudException an error occurred creating the user with the cloud provider
     * @throws InternalException an error occurred within the Dasein Cloud implementation while creating the user
     */
    @Nonnull CloudUser createUser(@Nonnull String userName, @Nullable String path, @Nullable String ... autoJoinGroupIds) throws CloudException, InternalException;

    /**
     * Enables the specified user to access the cloud API via their own API keys.
     * @param providerUserId the user to grant API access to, create a root credential if {@code null}
     * @return the access keys for the user if any, otherwise the root credentials
     * @throws CloudException an error occurred within the cloud provider enabling API access
     * @throws InternalException an error occurred within the Dasein Cloud implementation while enabling access
     */
    @Nonnull AccessKey createAccessKey(@Nullable String providerUserId) throws CloudException, InternalException;

    /**
     * Enables console access for the specified user with the specified password.
     * @param providerUserId the cloud provider ID of the user to add console access for
     * @param password the password to use for access to the console 
     * @throws CloudException an error occurred in the cloud provider enabling console access
     * @throws InternalException an error occurred within the Dasein Cloud implementation while enabling console access
     */
    void enableConsoleAccess(@Nonnull String providerUserId, @Nonnull byte[] password) throws CloudException, InternalException;

    /**
     * Updates the password used for console access for the specified user
     * @param providerUserId the cloud provider ID of the user to add console access for
     * @param oldPassword the old password used for access to the console
     * @param newPassword the new password to be used for access to the console
     * @throws CloudException an error occurred in the cloud provider enabling console access
     * @throws InternalException an error occurred within the Dasein Cloud implementation while enabling console access
     */
    void updateConsoleAccess(@Nonnull String providerUserId, @Nonnull byte[] oldPassword, @Nonnull byte[] newPassword) throws CloudException, InternalException;

    /**
     * Provides the cloud console URL where cloud user may sign-in to using their credentials.
     * @see IdentityAndAccessSupport#enableConsoleAccess(String, byte[])
     * @see IdentityAndAccessCapabilities#supportsConsoleAccess()
     * @return the URL of the cloud console
     * @throws CloudException an error occurred with the cloud provider in determining support
     * @throws InternalException a local error occurred while determining support
     */
    @Nullable String getConsoleUrl() throws CloudException, InternalException;

    /**
     * Provides a reference to the specified group.
     * @param providerGroupId the unique ID of the target group
     * @return the specified group if it exists
     * @throws CloudException an error occurred in the cloud provider fetching the specified group
     * @throws InternalException an error occurred in the Dasein Cloud implementation while fetching the specified group
     */
    @Nullable CloudGroup getGroup(@Nonnull String providerGroupId) throws CloudException, InternalException;

    /**
     * Provides a reference to the specified managed or inline policy.
     * @param providerPolicyId the unique ID of the target policy
     * @param options policy is assumed to be managed if {@code null}, must not be {@code null} for inline policies and should contain either a user ID or group ID which the policy is associated with.
     * @return the specified policy if it exists
     * @throws CloudException an error occurred in the cloud provider fetching the specified policy
     * @throws InternalException an error occurred in the Dasein Cloud implementation while fetching the specified policy
     */
    @Nullable CloudPolicy getPolicy(@Nonnull String providerPolicyId, @Nullable CloudPolicyFilterOptions options) throws CloudException, InternalException;

    /**
     * Provides a list of policy rules for a specific managed or inline policy.
     * @param providerPolicyId the unique ID of the target policy
     * @param options policy is assumed to be managed if {@code null}, must not be {@code null} for inline policies and should contain either a user ID or group ID which the policy is associated with.
     * @return the list of policy rules
     * @throws CloudException an error occurred in the cloud provider fetching the specified policy
     * @throws InternalException an error occurred in the Dasein Cloud implementation while fetching the specified policy
     */
    @Nonnull CloudPolicyRule[] getPolicyRules(@Nonnull String providerPolicyId, @Nullable CloudPolicyFilterOptions options) throws CloudException, InternalException;

    /**
     * Provides a reference to the specified user.
     * @param providerUserId the unique ID of the target user
     * @return the specified user if it exists
     * @throws CloudException an error occurred in the cloud provider fetching the specified user
     * @throws InternalException an error occurred in the Dasein Cloud implementation while fetching the specified user
     */
    @Nullable CloudUser getUser(@Nonnull String providerUserId) throws CloudException, InternalException;
    /**
     * @return true if this cloud supports IdM features in the current region and this account has access to them
     * @throws CloudException an error occurred in the cloud provider determining subscription status
     * @throws InternalException an error occurred within the Dasein Cloud implementation while determining subscription status
     */
    boolean isSubscribed() throws CloudException, InternalException;

    /**
     * List all API access keys either for a specified user or for a current user, which may include root credentials
     * @param providerUserId the user ID for which the access keys are listed, if specified
     * @return all matching access keys
     * @throws CloudException an error occurred with the cloud provider while listing access keys
     * @throws InternalException an error occurred  within the Dasein Cloud implementation while processing the request
     */
    @Nonnull Iterable<AccessKey> listAccessKeys(@Nullable String providerUserId) throws CloudException, InternalException;

    /**
     * Lists all groups or all groups with the specified path base
     * @param pathBase the optional base path to search in
     * @return all matching groups in the cloud provider
     * @throws CloudException an error occurred listing groups from the cloud provider
     * @throws InternalException an error occurred within the Dasein Cloud implementation processing the request
     */
    @Nonnull Iterable<CloudGroup> listGroups(@Nullable String pathBase) throws CloudException, InternalException;

    /**
     * Lists all groups to which a specified user belongs.
     * @param providerUserId the user to search on
     * @return all groups in which the specified user belongs
     * @throws CloudException an error occurred with the cloud provider searching for the specified user's groups
     * @throws InternalException an error occurred within the Dasein Cloud implementation executing the search
     */
    @Nonnull Iterable<CloudGroup> listGroupsForUser(@Nonnull String providerUserId) throws CloudException, InternalException;

    /**
     * Lists the policies.
     * @param options the filter criteria
     * @return the list of matching policies
     * @throws CloudException an error occurred with the cloud provider listing the policies
     * @throws InternalException an error occurred within the Dasein Cloud implementation executing the listing
     */
    @Nonnull Iterable<CloudPolicy> listPolicies(@Nonnull CloudPolicyFilterOptions options) throws CloudException, InternalException;

    /**
     * Lists all users belonging to the specified group.
     * @param inProviderGroupId the ID of the group in which to search
     * @return a list of all users belonging to the specified group
     * @throws CloudException an error occurred with the cloud provider while performing the search
     * @throws InternalException an error occurred within the Dasein Cloud implementation while performing the search
     */
    @Nonnull Iterable<CloudUser> listUsersInGroup(@Nonnull String inProviderGroupId) throws CloudException, InternalException;

    /**
     * Lists all users or all users within the specified path base
     * @param pathBase the optional base path to search in
     * @return all matching users
     * @throws CloudException an error occurred with the cloud provider while performing the search
     * @throws InternalException an error occurred within the Dasein Cloud implementation while performing the search
     */
    @Nonnull Iterable<CloudUser> listUsersInPath(@Nullable String pathBase) throws CloudException, InternalException;

    /**
     * Lists all users assigned to a managed policy
     * @param providerPolicyId the ID of the managed policy for which associated users are returned
     * @throws CloudException an error occurred with the cloud provider while performing the search
     * @throws InternalException an error occurred within the Dasein Cloud implementation while performing the search
     */
    @Nonnull Iterable<CloudUser> listUsersForPolicy(@Nonnull String providerPolicyId) throws CloudException, InternalException;

    /**
     * Lists all groups assigned to a managed policy
     * @param providerPolicyId the ID of the managed policy for which associated groups are returned
     * @throws CloudException an error occurred with the cloud provider while performing the search
     * @throws InternalException an error occurred within the Dasein Cloud implementation while performing the search
     */
    @Nonnull Iterable<CloudGroup> listGroupsForPolicy(@Nonnull String providerPolicyId) throws CloudException, InternalException;

    /**
     * Removes a previously created API access key associated with a user, if any - otherwise remove root API access key
     * @param sharedKeyPart the shared part of the key to remove
     * @param providerUserId the user whose access should be removed, if any
     * @throws CloudException an error occurred in the cloud provider while removing the access key
     * @throws InternalException an error occurred within the Dasein Cloud implementation while removing the access key
     */
    void removeAccessKey(@Nonnull String sharedKeyPart, @Nullable String providerUserId) throws CloudException, InternalException;

    /**
     * Removes a user's login access to the cloud provider's console.
     * @param providerUserId the user whose access should be removed
     * @throws CloudException an error occurred within the cloud provider while removing the access
     * @throws InternalException an error occurred within the Dasein Cloud implementation while removing the console access
     */
    void removeConsoleAccess(@Nonnull String providerUserId) throws CloudException, InternalException;

    /**
     * Removes the specified group from the system. Some clouds may require the group to be empty prior to allowing
     * the deletion, while others may not. It's probably a good idea to delete only empty groups anyways.
     * @param providerGroupId the group to be removed
     * @throws CloudException an error occurred in the cloud provider (did you empty the group?) removing the group
     * @throws InternalException an error occurred within the Dasein Cloud implementation removing the group
     */
    void removeGroup(@Nonnull String providerGroupId) throws CloudException, InternalException;

    /**
     * Removes the specified user from the cloud provider.
     * @param providerUserId the user to be removed
     * @throws CloudException an error occurred in the cloud provider removing the user
     * @throws InternalException an error occurred in the Dasein Cloud implementation removing the group
     */
    void removeUser(@Nonnull String providerUserId) throws CloudException, InternalException;

    /**
     * Removes the specified user's membership in the specified group.
     * @param providerUserId the user to be removed
     * @param providerGroupId the group from which the user is to be removed
     * @throws CloudException an error occurred in the cloud provider (did the user belong to the group?) removing the user from the group
     * @throws InternalException an error occurred within the Dasein Cloud implementation removing the user
     */
    void removeUserFromGroup(@Nonnull String providerUserId, @Nonnull String providerGroupId) throws CloudException, InternalException;

    /**
     * Updates the specified group with new path or name values. If <code>null</code> is specified for any value, it
     * will remain unchanged.
     * @param providerGroupId the provider ID for the group to be modified
     * @param newGroupName the new name for the group (or <code>null</code> to remain unchanged)
     * @param newPath the new path for the group (or <code>null</code> to remain unchanged)
     * @throws CloudException an error occurred with the cloud provider (does the group exist?) updating the group
     * @throws InternalException an error occurred within the Dasein Cloud implementation updating the group
     */
    void modifyGroup(@Nonnull String providerGroupId, @Nullable String newGroupName, @Nullable String newPath) throws CloudException, InternalException;

    /**
     * Create a policy according to the supplied options
     * @param options description of the policy
     * @return a unique policy identifier
     * @throws CloudException an error occurred with the cloud provider while creating policy
     * @throws InternalException an error occurred within the Dasein Cloud implementation
     */
    @Nonnull String createPolicy(@Nonnull CloudPolicyOptions options) throws CloudException, InternalException;

    /**
     * Modify specified policy according to the supplied options
     * @param providerPolicyId target policy that is being modified
     * @param options Description of the policy changes. User/group ID which may be passed in are used for inline policy association. For managed policy attachment use {@link #attachPolicyToUser(String, String) attachPolicyToUser} and {@link #attachPolicyToGroup(String, String) attachPolicyToGroup}.
     * @throws CloudException an error occurred with the cloud provider while modifying policy
     * @throws InternalException an error occurred within the Dasein Cloud implementation
     */
    void modifyPolicy(@Nonnull String providerPolicyId, @Nonnull CloudPolicyOptions options) throws CloudException, InternalException;

    /**
     * Remove a cloud policy with the specified policy identifier and optionally with an options object which may
     * be used to address a specific inline user or group policy
     * @param providerPolicyId unique policy ID
     * @param options policy is assumed to be managed if {@code null}, otherwise may contain a user ID or group ID for
     *                an inline policy
     * @throws CloudException an error occurred with the cloud provider while removing policy
     * @throws InternalException an error occurred within the Dasein Cloud implementation
     */
    void removePolicy(@Nonnull String providerPolicyId, @Nullable CloudPolicyFilterOptions options) throws CloudException, InternalException;

    /**
     * Associate specified policy with the specified cloud user
     * @param providerPolicyId unique policy identifier
     * @param providerUserId unique cloud user identifier
     * @throws CloudException an error occurred with the cloud provider while associating policy with the user
     * @throws InternalException an error occurred within the Dasein Cloud implementation
     * @see IdentityAndAccessCapabilities#getMaximumPoliciesPerUser()
     */
    void attachPolicyToUser(@Nonnull String providerPolicyId, @Nonnull String providerUserId) throws CloudException, InternalException;

    /**
     * De-associate specified policy from the specified cloud user
     * @param providerPolicyId unique policy identifier
     * @param providerUserId unique cloud user identifier
     * @throws CloudException an error occurred with the cloud provider while de-associating policy from the user
     * @throws InternalException an error occurred within the Dasein Cloud implementation
     * @see IdentityAndAccessCapabilities#getMaximumPoliciesPerUser()
     */
    void detachPolicyFromUser(@Nonnull String providerPolicyId, @Nonnull String providerUserId) throws CloudException, InternalException;

    /**
     * Associate specified policy with the specified cloud group
     * @param providerPolicyId unique policy identifier
     * @param providerGroupId unique cloud group identifier
     * @throws CloudException an error occurred with the cloud provider while associating policy with the group
     * @throws InternalException an error occurred within the Dasein Cloud implementation
     * @see IdentityAndAccessCapabilities#getMaximumPoliciesPerGroup()
     */
    void attachPolicyToGroup(@Nonnull String providerPolicyId, @Nonnull String providerGroupId) throws CloudException, InternalException;

    /**
     * De-associate specified policy from the specified cloud group
     * @param providerPolicyId unique policy identifier
     * @param providerGroupId unique cloud group identifier
     * @throws CloudException an error occurred with the cloud provider while de-associating policy from the group
     * @throws InternalException an error occurred within the Dasein Cloud implementation
     * @see IdentityAndAccessCapabilities#getMaximumPoliciesPerGroup()
     */
    void detachPolicyFromGroup(@Nonnull String providerPolicyId, @Nonnull String providerGroupId) throws CloudException, InternalException;

    /**
     * Enable the specifed API access key either for a specified user or for a current user, which may include root credentials
     * @param sharedKeyPart the access key's shared part
     * @param providerUserId the user ID for which the access key needs to be enabled, if specified
     * @throws CloudException an error occurred with the cloud provider while enabling the access key
     * @throws InternalException an error occurred  within the Dasein Cloud implementation while processing the request
     */
    void enableAccessKey(@Nonnull String sharedKeyPart, @Nullable String providerUserId) throws CloudException, InternalException;

    /**
     * Disable the specifed API access key either for a specified user or for a current user, which may include root credentials
     * @param sharedKeyPart the access key's shared part
     * @param providerUserId the user ID for which the access key needs to be disabled, if specified
     * @throws CloudException an error occurred with the cloud provider while disabling the access key
     * @throws InternalException an error occurred  within the Dasein Cloud implementation while processing the request
     */
    void disableAccessKey(@Nonnull String sharedKeyPart, @Nullable String providerUserId) throws CloudException, InternalException;

    /**
     * Updates the specified user with new path or user name values. If <code>null</code> is specified for any value,
     * it will remain unchanged.
     * @param providerUserId the provider ID for the user to be modified
     * @param newUserName the new name for the user (or <code>null</code> to remain unchanged)
     * @param newPath the new path for the user (or <code>null</code> to remain unchanged)
     * @throws CloudException an error occurred with the cloud provider (does the user exist?) updating the user
     * @throws InternalException an error occurred within the Dasein Cloud implementation updating the user
     */
    
    void modifyUser(@Nonnull String providerUserId, @Nullable String newUserName, @Nullable String newPath) throws CloudException, InternalException;

    /**
     * List all unique ids for all services available in the cloud, as addressable by IAM policies
     * @return list of all service ids
     * @throws CloudException an error occurred with the cloud provider while listing services
     * @throws InternalException an error occurred within the Dasein Cloud implementation
     */
    @Nonnull Iterable<String> listServices() throws CloudException, InternalException;

    /**
     * List all available actions per service, optionally selected for one individual service
     * @param forService optional service id for which to list actions
     * @return list of all available actions per given service, or if {@code forService} is {@code null} - all actions for all services
     * @throws CloudException an error occurred with the cloud provider while listing service action
     * @throws InternalException an error occurred within the Dasein Cloud implementation
     */
    @Nonnull Iterable<ServiceAction> listServiceActions(@Nullable String forService) throws CloudException, InternalException;
}
