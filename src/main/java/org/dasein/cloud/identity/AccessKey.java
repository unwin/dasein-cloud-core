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
 * API access keys for a cloud account that may or may not be associated with a specific user.
 * @author George Reese (george.reese@imaginary.com)
 * @since 2012.02
 * @version 2012.02
 */
@SuppressWarnings("unused")
public class AccessKey {
    private String providerOwnerId;
    private String providerUserId;
    private byte[] secretPart;
    private String sharedPart;
    private boolean enabled;

    public static AccessKey getInstance(@Nonnull String sharedPart, @Nonnull byte[] secretPart, boolean enabled) {
        return new AccessKey(sharedPart, secretPart, enabled);
    }

    private AccessKey(String sharedPart, byte[] secretPart, boolean enabled) {
        this.sharedPart = sharedPart;
        this.secretPart = Arrays.copyOf(secretPart, secretPart.length);
        this.enabled = enabled;
    }

    @Override
    public boolean equals(@Nullable Object ob) {
        if( ob == null ) {
            return false;
        }
        if( ob == this ) {
            return true;
        }
        if( !getClass().getName().equals(ob.getClass().getName()) ) {
            return false;
        }
        return sharedPart.equals(((AccessKey)ob).sharedPart);
    }
    
    public @Nullable String getProviderOwnerId() {
        return providerOwnerId;
    }

    public @Nonnull AccessKey withProviderOwnerId(@Nullable String providerOwnerId) {
        this.providerOwnerId = providerOwnerId;
        return this;
    }
    
    public @Nullable String getProviderUserId() {
        return providerUserId;
    }
    
    public @Nonnull AccessKey withProviderUserId(@Nullable String uid) {
        this.providerUserId = uid;
        return this;
    }

    public @Nonnull byte[] getSecretPart() {
        return secretPart;
    }

    public @Nonnull String getSharedPart() {
        return sharedPart;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public @Nonnull String toString() {
        return sharedPart;
    }
}
