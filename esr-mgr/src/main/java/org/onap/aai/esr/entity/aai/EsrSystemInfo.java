/**
 * Copyright 2017 ZTE Corporation.
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
package org.onap.aai.esr.entity.aai;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class EsrSystemInfo {
  
  @SerializedName("esr-system-info-id")
  @JsonProperty("esr-system-info-id")
  private String esrSystemInfoId;
  
  @SerializedName("system-name")
  @JsonProperty("system-name")
  private String systemName;
  
  @SerializedName("type")
  @JsonProperty("type")
  private String type;
  
  @SerializedName("vendor")
  @JsonProperty("vendor")
  private String vendor;
  
  @SerializedName("version")
  @JsonProperty("version")
  private String version;
  
  @SerializedName("service-url")
  @JsonProperty("service-url")
  private String serviceUrl;
  
  @SerializedName("user-name")
  @JsonProperty("user-name")
  private String userName;
  
  @SerializedName("password")
  @JsonProperty("password")
  private String password;
  
  @SerializedName("system-type")
  @JsonProperty("system-type")
  private String systemType;
  
  @SerializedName("protocol")
  @JsonProperty("protocol")
  private String protocol;
  
  @SerializedName("ssl-cassert")
  @JsonProperty("ssl-cassert")
  private String sslCassert;
  
  @SerializedName("ssl-insecure")
  @JsonProperty("ssl-insecure")
  private Boolean sslInsecure;
  
  @SerializedName("ip-address")
  @JsonProperty("ip-address")
  private String ipAddress;
  
  @SerializedName("port")
  @JsonProperty("port")
  private String port;
  
  @SerializedName("cloud-domain")
  @JsonProperty("cloud-domain")
  private String cloudDomain;
  
  @SerializedName("default-tenant")
  @JsonProperty("default-tenant")
  private String defaultTenant;
  
  @SerializedName("passive")
  @JsonProperty("passive")
  private Boolean passive;
  
  @SerializedName("remote-path")
  @JsonProperty("remote-path")
  private String remotePath;
  
  @SerializedName("system-status")
  @JsonProperty("system-status")
  private String systemStatus;
  
  @JsonProperty("resouce-version")
  private String resouceVersion;

  public String getEsrSystemInfoId() {
    return esrSystemInfoId;
  }

  public void setEsrSystemInfoId(String esrSystemInfoId) {
    this.esrSystemInfoId = esrSystemInfoId;
  }

  public String getSystemName() {
    return systemName;
  }

  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getVendor() {
    return vendor;
  }

  public void setVendor(String vendor) {
    this.vendor = vendor;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getServiceUrl() {
    return serviceUrl;
  }

  public void setServiceUrl(String serviceUrl) {
    this.serviceUrl = serviceUrl;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSystemType() {
    return systemType;
  }

  public void setSystemType(String systemType) {
    this.systemType = systemType;
  }

  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  public String getSslCassert() {
    return sslCassert;
  }

  public void setSslCassert(String sslCassert) {
    this.sslCassert = sslCassert;
  }

  public Boolean getSslInsecure() {
    return sslInsecure;
  }

  public void setSslInsecure(Boolean sslInsecure) {
    this.sslInsecure = sslInsecure;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  public String getPort() {
    return port;
  }

  public void setPort(String port) {
    this.port = port;
  }

  public String getCloudDomain() {
    return cloudDomain;
  }

  public void setCloudDomain(String cloudDomain) {
    this.cloudDomain = cloudDomain;
  }

  public String getDefaultTenant() {
    return defaultTenant;
  }

  public void setDefaultTenant(String defaultTenant) {
    this.defaultTenant = defaultTenant;
  }

  public Boolean getPassive() {
    return passive;
  }

  public void setPassive(Boolean passive) {
    this.passive = passive;
  }

  public String getRemotePath() {
    return remotePath;
  }

  public void setRemotePath(String remotePath) {
    this.remotePath = remotePath;
  }

  public String getSystemStatus() {
    return systemStatus;
  }

  public void setSystemStatus(String systemStatus) {
    this.systemStatus = systemStatus;
  }

  public String getResouceVersion() {
    return resouceVersion;
  }

  public void setResouceVersion(String resouceVersion) {
    this.resouceVersion = resouceVersion;
  }

}
