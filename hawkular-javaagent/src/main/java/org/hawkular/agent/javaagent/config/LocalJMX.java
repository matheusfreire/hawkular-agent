/*
 * Copyright 2015-2017 Red Hat, Inc. and/or its affiliates
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
package org.hawkular.agent.javaagent.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.management.ObjectName;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect( //
        fieldVisibility = Visibility.NONE, //
        getterVisibility = Visibility.NONE, //
        setterVisibility = Visibility.NONE, //
        isGetterVisibility = Visibility.NONE)
public class LocalJMX implements Validatable {

    @JsonProperty(required = true)
    private String name;

    @JsonProperty
    private BooleanExpression enabled = new BooleanExpression(Boolean.TRUE);

    @JsonProperty("resource-type-sets")
    private String[] resourceTypeSets;

    @JsonProperty("metric-labels")
    private Map<String, String> metricLabels;

    @JsonProperty("mbean-server-name")
    private String mbeanServerName;

    @JsonProperty("wait-for")
    private WaitFor[] waitFor;

    public LocalJMX() {
    }

    public LocalJMX(LocalJMX original) {
        this.name = original.name;
        this.enabled = original.enabled == null ? null : new BooleanExpression(original.enabled);
        this.resourceTypeSets = original.resourceTypeSets == null ? null
                : Arrays.copyOf(original.resourceTypeSets, original.resourceTypeSets.length);
        this.metricLabels = original.metricLabels == null ? null : new HashMap<>(original.metricLabels);
        this.mbeanServerName = original.mbeanServerName;
        this.waitFor = original.waitFor == null ? null : Arrays.copyOf(original.waitFor, original.waitFor.length);
    }

    @Override
    public void validate() throws Exception {
        if (name == null || name.trim().isEmpty()) {
            throw new Exception("local-jmx name must be specified");
        }

        if (waitFor != null) {
            for (WaitFor wf : waitFor) {
                wf.validate();

                // throw exception if the resource is not a valid ObjectName
                try {
                    new ObjectName(wf.getName());
                } catch (Exception e) {
                    throw new Exception(
                            "local-jmx [" + name + "] has invalid wait-for resource: " + wf.getName(), e);
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnabled() {
        return enabled == null ? null : enabled.get();
    }

    public void setEnabled(Boolean enabled) {
        if (this.enabled != null) {
            this.enabled.set(enabled);
        } else {
            this.enabled = new BooleanExpression(enabled);
        }
    }

    public String[] getResourceTypeSets() {
        return resourceTypeSets;
    }

    public void setResourceTypeSets(String[] resourceTypeSets) {
        this.resourceTypeSets = resourceTypeSets;
    }

    public Map<String, String> getMetricLabels() {
        return metricLabels;
    }

    public void setMetricLabels(Map<String, String> metricLabels) {
        this.metricLabels = metricLabels;
    }

    public String getMbeanServerName() {
        return mbeanServerName;
    }

    public void setMbeanServerName(String mbeanServerName) {
        this.mbeanServerName = mbeanServerName;
    }

    public WaitFor[] getWaitFor() {
        return waitFor;
    }

    public void setWaitFor(WaitFor[] waitFor) {
        this.waitFor = waitFor;
    }
}
