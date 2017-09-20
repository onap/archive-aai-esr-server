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

public class VimAuthInfoTest {

  @Test
  public void getterAndSetter4cloudDomain(){
      final String cloudDomain = "chengdu";
      VimAuthInfo vimAuthInfo = new VimAuthInfo();
      vimAuthInfo.setCloudDomain(cloudDomain);
      assertEquals(vimAuthInfo.getCloudDomain(), cloudDomain);
  }
  
  @Test
  public void getterAndSetter4userName(){
      final String userName = "nancy";
      VimAuthInfo vimAuthInfo = new VimAuthInfo();
      vimAuthInfo.setUserName(userName);
      assertEquals(vimAuthInfo.getUserName(), userName);
  }
  
  @Test
  public void getterAndSetter4password(){
      final String password = "dasffa";
      VimAuthInfo vimAuthInfo = new VimAuthInfo();
      vimAuthInfo.setPassword(password);
      assertEquals(vimAuthInfo.getPassword(), password);
  }
  
  @Test
  public void getterAndSetter4authUrl(){
      final String authUrl = "http://127.0.0.1:5000/auth/";
      VimAuthInfo vimAuthInfo = new VimAuthInfo();
      vimAuthInfo.setAuthUrl(authUrl);
      assertEquals(vimAuthInfo.getAuthUrl(), authUrl);
  }
  
  @Test
  public void getterAndSetter4sslCacert(){
      final String sslCacert = "fdfafda";
      VimAuthInfo vimAuthInfo = new VimAuthInfo();
      vimAuthInfo.setSslCacert(sslCacert);
      assertEquals(vimAuthInfo.getSslCacert(), sslCacert);
  }
  
  @Test
  public void getterAndSetter4sslInsecure(){
      final Boolean sslInsecure = true;
      VimAuthInfo vimAuthInfo = new VimAuthInfo();
      vimAuthInfo.setSslInsecure(sslInsecure);
      assertEquals(vimAuthInfo.getSslInsecure(), sslInsecure);
  }
}
