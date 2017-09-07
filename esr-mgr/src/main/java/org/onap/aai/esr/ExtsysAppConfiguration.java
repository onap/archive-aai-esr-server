/**
 * Copyright 2016-2017 ZTE Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onap.aai.esr;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;



public class ExtsysAppConfiguration extends Configuration {
  @NotEmpty
  private String template;

  @NotEmpty
  private String defaultName = "ONAP-A&AI-ESR";

  @NotEmpty
  private String msbIp;
  
  @NotEmpty
  private int msbPort;
  
  @NotEmpty
  private String msbServerAddr;
  
  @Valid
  private String serviceIp;
  
  @JsonProperty
  public String getTemplate() {
    return template;
  }

  @JsonProperty
  public void setTemplate(String template) {
    this.template = template;
  }

  @JsonProperty
  public String getDefaultName() {
    return defaultName;
  }

  @JsonProperty
  public void setDefaultName(String name) {
    this.defaultName = name;
  }
  
  @JsonProperty
  public String getMsbIp() {
    return msbIp;
  }
  
  @JsonProperty
  public String setMsbIp() {
    return msbIp;
  }
  
  @JsonProperty
  public int getMsbPort() {
    return msbPort;
  }
  
  @JsonProperty
  public int setMsbPort() {
    return msbPort;
  }

  @JsonProperty
  public String getMsbServerAddr() {
    return msbServerAddr;
  }

  @JsonProperty
  public void setMsbServerAddr(String msbServerAddr) {
    this.msbServerAddr = msbServerAddr;
  }
  @JsonProperty
  public String getServiceIp() {
    return serviceIp;
  }

  @JsonProperty
  public void setServiceIp(String serviceIp) {
    this.serviceIp = serviceIp;
  }
}
