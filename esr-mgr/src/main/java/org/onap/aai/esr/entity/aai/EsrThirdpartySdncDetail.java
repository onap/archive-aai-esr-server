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

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class EsrThirdpartySdncDetail implements Serializable {

  public static final long serialVersionUID = 1L;
  
  @SerializedName("thirdparty-sdnc-id")
  private String thirdpartySdncId;
  
  @SerializedName("location")
  private String location;
  
  @SerializedName("product-name")
  private String productName;
  
  @SerializedName("resource-version")
  private String resourceVersion;
  
  @SerializedName("esr-system-info-list")
  private EsrSystemInfoList esrSystemInfoList;

  public String getThirdpartySdncId() {
    return thirdpartySdncId;
  }

  public void setThirdpartySdncId(String thirdpartySdncId) {
    this.thirdpartySdncId = thirdpartySdncId;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public EsrSystemInfoList getEsrSystemInfoList() {
    return esrSystemInfoList;
  }

  public void setEsrSystemInfoList(EsrSystemInfoList esrSystemInfoList) {
    this.esrSystemInfoList = esrSystemInfoList;
  }

  public String getResourceVersion() {
    return resourceVersion;
  }

  public void setResourceVersion(String resourceVersion) {
    this.resourceVersion = resourceVersion;
  }
}
