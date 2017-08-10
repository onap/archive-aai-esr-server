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

import org.onap.aai.esr.entity.db.EmsData;

public class EmsRestData extends BaseRestData {

  private String productName;

  private String url;
  private String userName;
  private String password;
  private String emsId;

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
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

  public String getEmsId() {
    return emsId;
  }

  public void setEmsId(String emsId) {
    this.emsId = emsId;
  }

  /**
   * ems rest result.
   */
  public EmsRestData(EmsData data) {
    super(data);
    this.emsId = data.getId();
    this.password = data.getPassword();
    this.productName = data.getProductName();
    this.url = data.getUrl();
    this.userName = data.getUserName();
    this.setInstanceId(null);
    this.setCategory(null);
  }

  public EmsRestData() {}
}
