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

package org.dasein.cloud.ci;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.dasein.cloud.AccessControlledService;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.ResourceStatus;
import org.dasein.cloud.Tag;

public interface ConvergedHttpLoadBalancerSupport extends AccessControlledService {

    public @Nonnull String getProviderTermForConvergedHttpLoadBalancer(@Nonnull Locale locale);

    public @Nullable ConvergedHttpLoadBalancer getConvergedHttpLoadBalancer(@Nonnull String convergedHttpLoadbalancerId) throws CloudException, InternalException;

    public boolean isSubscribed() throws CloudException, InternalException;

    public @Nonnull Iterable<String> listConvergedHttpLoadBalancers() throws CloudException, InternalException;

    public @Nonnull Iterable<ResourceStatus> listConvergedHttpLoadBalancerStatus() throws InternalException, CloudException;

    public void updateTags(@Nonnull String convergedHttpLoadbalancerId, @Nonnull Tag... tags) throws CloudException, InternalException;

    public void updateTags(@Nonnull String[] convergedHttpLoadbalancerIds, @Nonnull Tag... tags) throws CloudException, InternalException;

    public void removeTags(@Nonnull String convergedHttpLoadbalancerId, @Nonnull Tag... tags) throws CloudException, InternalException;

    public void removeTags(@Nonnull String[] convergedHttpLoadbalancerIds, @Nonnull Tag... tags) throws CloudException, InternalException;

    public String createConvergedHttpLoadBalancer(@Nonnull ConvergedHttpLoadBalancer withConvergedHttpLoadbalancerOptions) throws CloudException, InternalException;

    public void removeConvergedHttpLoadBalancers(@Nonnull String globalForwardingRuleName) throws CloudException, InternalException;

    public HttpLoadBalancerCapabilities getCapabilities();
}
