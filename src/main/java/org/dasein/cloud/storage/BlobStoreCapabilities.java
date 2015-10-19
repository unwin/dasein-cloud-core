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

package org.dasein.cloud.storage;

import org.dasein.cloud.Capabilities;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.util.NamingConstraints;
import org.dasein.util.uom.storage.*;

import javax.annotation.Nonnull;
import java.util.Locale;

/**
 * Describes the capabilities of a region within a cloud for a specific account.
 * Created by stas on 02/06/2015.
 * @since 2015.05
 */
public interface BlobStoreCapabilities extends Capabilities {

    boolean allowsNestedBuckets() throws CloudException, InternalException;

    boolean allowsRootObjects() throws CloudException, InternalException;

    boolean allowsPublicSharing() throws CloudException, InternalException;

    int getMaxBuckets() throws CloudException, InternalException;

    @Nonnull Storage<org.dasein.util.uom.storage.Byte> getMaxObjectSize() throws InternalException, CloudException;

    int getMaxObjectsPerBucket() throws CloudException, InternalException;

    @Nonnull NamingConstraints getBucketNamingConstraints() throws CloudException, InternalException;

    @Nonnull NamingConstraints getObjectNamingConstraints() throws CloudException, InternalException;

    @Nonnull String getProviderTermForBucket(@Nonnull Locale locale);

    @Nonnull String getProviderTermForObject(@Nonnull Locale locale);

}
