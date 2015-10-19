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
public class CloudPolicyOptions {
    private String name;
    private String description;
    private CloudPolicyRule[] rules;
    private String providerUserId;
    private String providerGroupId;

    public static CloudPolicyOptions getInstance(@Nonnull String name, @Nonnull CloudPolicyRule ... rules) {
        return new CloudPolicyOptions(name, rules);
    }

    private CloudPolicyOptions(@Nonnull String name, @Nonnull CloudPolicyRule[] rules) {
        this.name = name;
        this.rules = rules;
    }

    public CloudPolicyOptions withDescription(@Nullable String description) {
        this.description = description;
        return this;
    }

    public CloudPolicyOptions withProviderUserId(@Nullable String providerUserId) {
        this.providerUserId = providerUserId;
        return this;
    }

    public CloudPolicyOptions withProviderGroupId(@Nullable String providerGroupId) {
        this.providerGroupId = providerGroupId;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CloudPolicyRule[] getRules() {
        return rules;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public String getProviderGroupId() {
        return providerGroupId;
    }

    @Override
    public String toString() {
        return "CloudPolicyOptions{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rules=" + Arrays.toString(rules) +
                ", providerUserId='" + providerUserId + '\'' +
                ", providerGroupId='" + providerGroupId + '\'' +
                '}';
    }
}
