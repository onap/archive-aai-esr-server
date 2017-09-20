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

public class ThirdpartySdncRegisterInfoTest {
  @Test
  public void getterAndSetter4cloudDomain(){
      final String thirdpartySdncId = "312erqdfa";
      ThirdpartySdncRegisterInfo thirdpartySdncRegisterInfo = new ThirdpartySdncRegisterInfo();
      thirdpartySdncRegisterInfo.setThirdpartySdncId(thirdpartySdncId);
      assertEquals(thirdpartySdncRegisterInfo.getThirdpartySdncId(), thirdpartySdncId);
  }
  
  @Test
  public void getterAndSetter4name(){
      final String name = "sdnc-test";
      ThirdpartySdncRegisterInfo thirdpartySdncRegisterInfo = new ThirdpartySdncRegisterInfo();
      thirdpartySdncRegisterInfo.setName(name);
      assertEquals(thirdpartySdncRegisterInfo.getName(), name);
  }
  
  @Test
  public void getterAndSetter4vendor(){
      final String vendor = "zte";
      ThirdpartySdncRegisterInfo thirdpartySdncRegisterInfo = new ThirdpartySdncRegisterInfo();
      thirdpartySdncRegisterInfo.setVendor(vendor);
      assertEquals(thirdpartySdncRegisterInfo.getVendor(), vendor);
  }
  
  @Test
  public void getterAndSetter4version(){
      final String version = "v1.0";
      ThirdpartySdncRegisterInfo thirdpartySdncRegisterInfo = new ThirdpartySdncRegisterInfo();
      thirdpartySdncRegisterInfo.setVersion(version);
      assertEquals(thirdpartySdncRegisterInfo.getVersion(), version);
  }
  
  @Test
  public void getterAndSetter4type(){
      final String type = "type-test";
      ThirdpartySdncRegisterInfo thirdpartySdncRegisterInfo = new ThirdpartySdncRegisterInfo();
      thirdpartySdncRegisterInfo.setType(type);
      assertEquals(thirdpartySdncRegisterInfo.getType(), type);
  }
  
  @Test
  public void getterAndSetter4location(){
      final String location = "312erqdfa";
      ThirdpartySdncRegisterInfo thirdpartySdncRegisterInfo = new ThirdpartySdncRegisterInfo();
      thirdpartySdncRegisterInfo.setLocation(location);
      assertEquals(thirdpartySdncRegisterInfo.getLocation(), location);
  }
  
  @Test
  public void getterAndSetter4url(){
      final String url = "http://13.44.22.11:5000";
      ThirdpartySdncRegisterInfo thirdpartySdncRegisterInfo = new ThirdpartySdncRegisterInfo();
      thirdpartySdncRegisterInfo.setUrl(url);
      assertEquals(thirdpartySdncRegisterInfo.getUrl(), url);
  }
  
  @Test
  public void getterAndSetter4userName(){
      final String userName = "root";
      ThirdpartySdncRegisterInfo thirdpartySdncRegisterInfo = new ThirdpartySdncRegisterInfo();
      thirdpartySdncRegisterInfo.setUserName(userName);
      assertEquals(thirdpartySdncRegisterInfo.getUserName(), userName);
  }
  
  @Test
  public void getterAndSetter4password(){
      final String password = "root";
      ThirdpartySdncRegisterInfo thirdpartySdncRegisterInfo = new ThirdpartySdncRegisterInfo();
      thirdpartySdncRegisterInfo.setPassword(password);
      assertEquals(thirdpartySdncRegisterInfo.getPassword(), password);
  }
  
  @Test
  public void getterAndSetter4productName(){
      final String productName = "sdnctest";
      ThirdpartySdncRegisterInfo thirdpartySdncRegisterInfo = new ThirdpartySdncRegisterInfo();
      thirdpartySdncRegisterInfo.setProductName(productName);
      assertEquals(thirdpartySdncRegisterInfo.getProductName(), productName);
  }
  
  @Test
  public void getterAndSetter4protocol(){
      final String protocol = "root";
      ThirdpartySdncRegisterInfo thirdpartySdncRegisterInfo = new ThirdpartySdncRegisterInfo();
      thirdpartySdncRegisterInfo.setProtocol(protocol);
      assertEquals(thirdpartySdncRegisterInfo.getProtocol(), protocol);
  }
}
