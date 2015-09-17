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
 * Represents a policy tied to a user or group in the cloud.
 * @author George Reese (george.reese@imaginary.com)
 * @since 2012.02
 * @version 2012.02
 */
public class CloudPolicy {
    /**
     * Construct a new inline or managed cloud policy object
     * @param providerPolicyId Provider policy ID
     * @param name Policy name
     * @param description Policy description, optional
     * @return policy object
     */
    static public CloudPolicy getInstance(@Nonnull String providerPolicyId,
                                          @Nonnull String name,
                                          @Nullable String description,
                                          @Nonnull CloudPolicyRule[] rules,
                                          boolean managed) {
        CloudPolicy policy = new CloudPolicy();

        policy.providerPolicyId = providerPolicyId;
        policy.name = name;
        policy.description = description;
        policy.rules = rules;
        policy.managed = managed;
        return policy;
    }

    private CloudPolicyRule [] rules;
    private String          name;
    private String          description;
    private String          providerPolicyId;
    private boolean         managed;

    private CloudPolicy() { }

    public @Nonnull CloudPolicyRule[] getRules() {
        return rules;
    }

    public @Nonnull String getName() {
        return name;
    }

    public @Nullable String getDescription() {
        return description;
    }

    public @Nonnull String getProviderPolicyId() {
        return providerPolicyId;
    }

    public boolean isManaged() {
        return managed;
    }

    @Override
    public @Nonnull String toString() {
        return name + ":" + Arrays.toString(rules) + " [#" + providerPolicyId + "]";
    }
}
