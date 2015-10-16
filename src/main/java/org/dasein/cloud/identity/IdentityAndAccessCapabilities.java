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

import org.dasein.cloud.Capabilities;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.Requirement;
import org.dasein.cloud.util.NamingConstraints;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;

/**
 * Describes the capabilities of the identity and access service within a cloud for a specific account.
 * Created by Stas Maksimov: 18/06/2015 11:52
 * @author Stas Maksimov
 * @since 2015.09
 * @version 2015.09
 */
public interface IdentityAndAccessCapabilities extends Capabilities {

    /**
     * @return true if the cloud API supports the management of access control through its IdM APIs
     * @throws CloudException an error occurred within the cloud provider determining access control support
     * @throws InternalException an error occurred within the Dasein Cloud implementation determining access control support
     */
    @SuppressWarnings("unused")
    boolean supportsAccessControls() throws CloudException, InternalException;

    /**
     * @return true if the cloud API supports managing access to the cloud console (also false if the cloud has no console)
     * @throws CloudException an error occurred within the cloud provider determining console access support
     * @throws InternalException an error occurred within the Dasein Cloud implementation determining console access support
     */
    @SuppressWarnings("unused")
    boolean supportsConsoleAccess() throws CloudException, InternalException;

    /**
     * @return true if the cloud API supports managing API access
     * @throws CloudException an error occurred within the cloud provider determining API access management support
     * @throws InternalException an error occurred within the Dasein Cloud implementation determining API access management support
     */
    @SuppressWarnings("unused")
    boolean supportsApiAccess() throws CloudException, InternalException;

    /**
     * @return true if the cloud API supports policies
     * @throws CloudException an error occurred within the cloud provider determining policy support
     * @throws InternalException an error occurred within the Dasein Cloud implementation determining policy support
     */
    @SuppressWarnings("unused")
    boolean supportsPolicies() throws CloudException, InternalException;

    /**
     * Provides the cloud console URL where cloud user may sign-in to using their credentials.
     * @see IdentityAndAccessSupport#enableConsoleAccess(String, byte[])
     * @see IdentityAndAccessCapabilities#supportsConsoleAccess()
     * @return the URL of the cloud console
     * @throws CloudException an error occurred with the cloud provider in determining support
     * @throws InternalException a local error occurred while determining support
     */
    @SuppressWarnings("unused")
    @Nullable String getConsoleUrl() throws CloudException, InternalException;

    /**
     * Provides the provider term for a user entity.
     * @param locale the locale into which the term should be translated
     * @return the provider term for the user entity, ideally translated for the specified locale
     */
    @SuppressWarnings("unused")
    @Nonnull String getProviderTermForUser(@Nonnull Locale locale);

    /**
     * Provides the provider term for a group entity.
     * @param locale the locale into which the term should be translated
     * @return the provider term for the group entity, ideally translated for the specified locale
     */
    @SuppressWarnings("unused")
    @Nonnull String getProviderTermForGroup(@Nonnull Locale locale);

    /**
     * Provides the provider term for a policy entity.
     * @param locale the locale into which the term should be translated
     * @return the provider term for the policy entity, ideally translated for the specified locale
     */
    @SuppressWarnings("unused")
    @Nonnull String getProviderTermForPolicy(@Nonnull Locale locale);

    /**
     * Provides the password constraints set up for the cloud account
     * @return password policy constraints
     * @throws CloudException an error occurred while fetching the password constraints
     * @throws InternalException an error occurred within Dasein Cloud implementation
     */
    @SuppressWarnings("unused")
    @Nonnull NamingConstraints getPasswordConstraints() throws CloudException, InternalException;

    /**
     * Identifies what cloud policy types are supported in this cloud.
     * @return a list of supported cloud policy types
     * @throws InternalException an error occurred within the Dasein Cloud implementation
     * @throws CloudException an error occurred fetching the list of supported cloud policy types from the cloud
     */
    @SuppressWarnings("unused")
    @Nonnull Iterable<CloudPolicyType> listSupportedPolicyTypes() throws CloudException, InternalException;

    /**
     * Provides the maximum number of policies that may be assigned to a user.
     * @return the maximum number of policies per user or {@link Capabilities#LIMIT_UNLIMITED} for unlimited or {@link Capabilities#LIMIT_UNKNOWN}for unknown
     * @throws CloudException an error occurred fetching the limits from the cloud provider
     * @throws InternalException an error occurred within the Dasein Cloud implementation determining the limits
     */
    @SuppressWarnings("unused")
    int getMaximumPoliciesPerUser() throws CloudException, InternalException;

    /**
     * Provides the maximum number of policies that may be assigned to a group.
     * @return the maximum number of policies per group or {@link Capabilities#LIMIT_UNLIMITED} for unlimited or {@link Capabilities#LIMIT_UNKNOWN}for unknown
     * @throws CloudException an error occurred fetching the limits from the cloud provider
     * @throws InternalException an error occurred within the Dasein Cloud implementation determining the limits
     */
    @SuppressWarnings("unused")
    int getMaximumPoliciesPerGroup() throws CloudException, InternalException;

    /**
     * Provides the maximum number of groups that user may be assigned to.
     * @return the maximum number of groups per user or {@link Capabilities#LIMIT_UNLIMITED} for unlimited or {@link Capabilities#LIMIT_UNKNOWN}for unknown
     * @throws CloudException an error occurred fetching the limits from the cloud provider
     * @throws InternalException an error occurred within the Dasein Cloud implementation determining the limits
     */
    @SuppressWarnings("unused")
    int getMaximumGroupsPerUser() throws CloudException, InternalException;

    /**
     * Provides the maximum access keys that may be assigned to a user
     * @return the maximum number of groups per user or {@link Capabilities#LIMIT_UNLIMITED} for unlimited or {@link Capabilities#LIMIT_UNKNOWN}for unknown
     * @throws CloudException an error occurred fetching the limits from the cloud provider
     * @throws InternalException an error occurred within the Dasein Cloud implementation determining the limits
     */
    @SuppressWarnings("unused")
    int getMaximumAccessKeysPerUser() throws CloudException, InternalException;

    /**
     * Provides the maximum number of users that may be created.
     * @return the maximum number of users or {@link Capabilities#LIMIT_UNLIMITED} for unlimited or {@link Capabilities#LIMIT_UNKNOWN}for unknown
     * @throws CloudException an error occurred fetching the limits from the cloud provider
     * @throws InternalException an error occurred within the Dasein Cloud implementation determining the limits
     */
    @SuppressWarnings("unused")
    int getMaximumUsers() throws CloudException, InternalException;

    /**
     * Provides the maximum number of groups that may be created.
     * @return the maximum number of groups or {@link Capabilities#LIMIT_UNLIMITED} for unlimited or {@link Capabilities#LIMIT_UNKNOWN}for unknown
     * @throws CloudException an error occurred fetching the limits from the cloud provider
     * @throws InternalException an error occurred within the Dasein Cloud implementation determining the limits
     */
    @SuppressWarnings("unused")
    int getMaximumGroups() throws CloudException, InternalException;
}