/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hawkular.agent.monitor.inventory.platform;

import org.hawkular.agent.monitor.inventory.ID;
import org.hawkular.agent.monitor.inventory.Name;
import org.hawkular.agent.monitor.inventory.Resource;
import org.hawkular.agent.monitor.scheduler.config.PlatformEndpoint;

public class PlatformResource extends Resource
        <PlatformResourceType,
        PlatformEndpoint,
        PlatformMetricInstance,
        PlatformAvailInstance,
        PlatformResourceConfigurationPropertyInstance> {

    public PlatformResource(ID id, Name name, PlatformEndpoint endpoint, PlatformResourceType type,
            PlatformResource parent) {
        super(id, name, endpoint, type, parent);
    }
}