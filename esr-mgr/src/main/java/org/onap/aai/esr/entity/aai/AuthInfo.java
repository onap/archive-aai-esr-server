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

public class AuthInfo {
  
  private String esrSystemInfoId;
  
  private String systemName;
  
  private String type;
  
  private String vendor;
  
  private String version;
  
  private String serviceUrl;
  
  private String userName;
  
  private String password;
  
  private String systemType;
  
  private String protocal;
  
  private String sslCassert;
  
  private Boolean sslInsecure;
  
  private String ipAddress;
  
  private String port;
  
  private String cloudDomain;
  
  private String defaultTenant;
  
  private String systemStatus;
  
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

  public String getProtocal() {
    return protocal;
  }

  public void setProtocal(String protocal) {
    this.protocal = protocal;
  }

  public String getSslCassert() {
    return sslCassert;
  }

  public void setSslCassert(String sslCassert) {
    this.sslCassert = sslCassert;
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

  public String getSystemStatus() {
    return systemStatus;
  }

  public void setSystemStatus(String systemStatus) {
    this.systemStatus = systemStatus;
  }

  public Boolean getSslInsecure() {
    return sslInsecure;
  }

  public void setSslInsecure(Boolean sslInsecure) {
    this.sslInsecure = sslInsecure;
  }

  public String getResouceVersion() {
    return resouceVersion;
  }

  public void setResouceVersion(String resouceVersion) {
    this.resouceVersion = resouceVersion;
  }

}
