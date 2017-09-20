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
package org.onap.aai.esr.entity.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VnfmRegisterInfoTest {
  
  @Test
  public void getterAndSetter4vnfmId(){
      final String vnfmId = "dafadf";
      VnfmRegisterInfo vnfmRegisterInfo = new VnfmRegisterInfo();
      vnfmRegisterInfo.setVnfmId(vnfmId);
      assertEquals(vnfmRegisterInfo.getVnfmId(), vnfmId);
  }
  
  @Test
  public void getterAndSetter4name(){
      final String name = "name-test";
      VnfmRegisterInfo vnfmRegisterInfo = new VnfmRegisterInfo();
      vnfmRegisterInfo.setName(name);
      assertEquals(vnfmRegisterInfo.getName(), name);
  }
  
  @Test
  public void getterAndSetter4type(){
      final String type = "vnfm-type";
      VnfmRegisterInfo vnfmRegisterInfo = new VnfmRegisterInfo();
      vnfmRegisterInfo.setType(type);
      assertEquals(vnfmRegisterInfo.getType(), type);
  }
  
  @Test
  public void getterAndSetter4vimId(){
      final String vimId = "dafadf";
      VnfmRegisterInfo vnfmRegisterInfo = new VnfmRegisterInfo();
      vnfmRegisterInfo.setVimId(vimId);
      assertEquals(vnfmRegisterInfo.getVimId(), vimId);
  }
  
  @Test
  public void getterAndSetter4vendor(){
      final String vendor = "zte";
      VnfmRegisterInfo vnfmRegisterInfo = new VnfmRegisterInfo();
      vnfmRegisterInfo.setVendor(vendor);
      assertEquals(vnfmRegisterInfo.getVendor(), vendor);
  }
  
  @Test
  public void getterAndSetter4version(){
      final String version = "v1.0";
      VnfmRegisterInfo vnfmRegisterInfo = new VnfmRegisterInfo();
      vnfmRegisterInfo.setVersion(version);
      assertEquals(vnfmRegisterInfo.getVersion(), version);
  }
  
  @Test
  public void getterAndSetter4certificateUrl(){
      final String certificateUrl = "/test";
      VnfmRegisterInfo vnfmRegisterInfo = new VnfmRegisterInfo();
      vnfmRegisterInfo.setCertificateUrl(certificateUrl);
      assertEquals(vnfmRegisterInfo.getCertificateUrl(), certificateUrl);
  }
  
  @Test
  public void getterAndSetter4url(){
      final String url = "/home";
      VnfmRegisterInfo vnfmRegisterInfo = new VnfmRegisterInfo();
      vnfmRegisterInfo.setUrl(url);
      assertEquals(vnfmRegisterInfo.getUrl(), url);
  }
  
  @Test
  public void getterAndSetter4userName(){
      final String userName = "li";
      VnfmRegisterInfo vnfmRegisterInfo = new VnfmRegisterInfo();
      vnfmRegisterInfo.setUserName(userName);
      assertEquals(vnfmRegisterInfo.getUserName(), userName);
  }
  
  @Test
  public void getterAndSetter4password(){
      final String password = "dfaaf";
      VnfmRegisterInfo vnfmRegisterInfo = new VnfmRegisterInfo();
      vnfmRegisterInfo.setPassword(password);
      assertEquals(vnfmRegisterInfo.getPassword(), password);
  }
}
