/**
 * Copyright 2016 ZTE Corporation.
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

import org.onap.aai.esr.entity.db.VnfmData;

public class VnfmRestData extends BaseRestData {

  private String vnfmId;
  private String url;

  private String userName;
  private String password;
  private String vimId;
  private String certificateUrl;

  public String getVnfmId() {
    return vnfmId;
  }

  public void setVnfmId(String vnfmId) {
    this.vnfmId = vnfmId;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
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

  public String getVimId() {
    return vimId;
  }

  public void setVimId(String vimId) {
    this.vimId = vimId;
  }

  public String getCertificateUrl() {
    return certificateUrl;
  }

  public void setCertificateUrl(String certificateUrl) {
    this.certificateUrl = certificateUrl;
  }

  /**
   * vnfm rest result.
   */
  public VnfmRestData(VnfmData data) {
    super(data);
    this.certificateUrl = data.getCertificateUrl();
    this.password = data.getPassword();
    this.url = data.getUrl();
    this.userName = data.getUserName();
    this.vimId = data.getVimId();
    this.vnfmId = data.getId();
    this.setInstanceId(null);
    this.setCategory(null);
  }

  public VnfmRestData() {

  }
}
