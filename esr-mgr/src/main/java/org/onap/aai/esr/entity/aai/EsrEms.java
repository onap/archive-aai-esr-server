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
package org.onap.aai.esr.entity.aai;


public class EsrEms {

  private String emsId;
  
  private Boolean passive;
  
  private String remotePath;
  
  private EsrSystemInfoList esrSystemInfoList;

  public String getEmsId() {
    return emsId;
  }

  public void setEmsId(String emsId) {
    this.emsId = emsId;
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

  public EsrSystemInfoList getEsrSystemInfoList() {
    return esrSystemInfoList;
  }

  public void setEsrSystemInfoList(EsrSystemInfoList esrSystemInfoList) {
    this.esrSystemInfoList = esrSystemInfoList;
  }
}
