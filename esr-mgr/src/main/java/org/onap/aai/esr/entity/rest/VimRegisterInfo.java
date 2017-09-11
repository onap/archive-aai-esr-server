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
package org.onap.aai.esr.entity.rest;

public class VimRegisterInfo {

  private String cloudOwner;

  private String cloudRegionId;

  private String cloudType;

  private String cloudRegionVersion;

  private String ownerDefinedType;

  private String cloudZone;
  
  private String complexName;
  
  private String cloudExtraInfo;
  
  private VimAuthInfo vimAuthInfo;

  public String getCloudOwner() {
    return cloudOwner;
  }

  public void setCloudOwner(String cloudOwner) {
    this.cloudOwner = cloudOwner;
  }

  public String getCloudRegionId() {
    return cloudRegionId;
  }

  public void setCloudRegionId(String cloudRegionId) {
    this.cloudRegionId = cloudRegionId;
  }

  public String getCloudType() {
    return cloudType;
  }

  public void setCloudType(String cloudType) {
    this.cloudType = cloudType;
  }

  public String getCloudRegionVersion() {
    return cloudRegionVersion;
  }

  public void setCloudRegionVersion(String cloudRegionVersion) {
    this.cloudRegionVersion = cloudRegionVersion;
  }

  public String getOwnerDefinedType() {
    return ownerDefinedType;
  }

  public void setOwnerDefinedType(String ownerDefinedType) {
    this.ownerDefinedType = ownerDefinedType;
  }

  public String getCloudZone() {
    return cloudZone;
  }

  public void setCloudZone(String cloudZone) {
    this.cloudZone = cloudZone;
  }

  public String getComplexName() {
    return complexName;
  }

  public void setComplexName(String complexName) {
    this.complexName = complexName;
  }

  public String getCloudExtraInfo() {
    return cloudExtraInfo;
  }

  public void setCloudExtraInfo(String cloudExtraInfo) {
    this.cloudExtraInfo = cloudExtraInfo;
  }

  public VimAuthInfo getVimAuthInfo() {
    return vimAuthInfo;
  }

  public void setVimAuthInfo(VimAuthInfo vimAuthInfo) {
    this.vimAuthInfo = vimAuthInfo;
  }


}
