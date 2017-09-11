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

public class EsrVnfm {
  
  private String vnfmId;
  
  private String vimId;
  
  private String certificateUrl;
  
  private EsrSystemInfoList esrSystemInfoList;

  public String getVnfmId() {
    return vnfmId;
  }

  public void setVnfmId(String vnfmId) {
    this.vnfmId = vnfmId;
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

  public EsrSystemInfoList getEsrSystemInfoList() {
    return esrSystemInfoList;
  }

  public void setEsrSystemInfoList(EsrSystemInfoList esrSystemInfoList) {
    this.esrSystemInfoList = esrSystemInfoList;
  }

}
