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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class VimRegisterInfoTest {
  @Test
  public void getterAndSetter4cloudOwner(){
      final String cloudOwner = "zte";
      VimRegisterInfo vimRegisterInfo = new VimRegisterInfo();
      vimRegisterInfo.setCloudOwner(cloudOwner);
      assertEquals(vimRegisterInfo.getCloudOwner(), cloudOwner);
  }
  
  @Test
  public void getterAndSetter4cloudRegionId(){
      final String cloudRegionId = "region-one";
      VimRegisterInfo vimRegisterInfo = new VimRegisterInfo();
      vimRegisterInfo.setCloudRegionId(cloudRegionId);
      assertEquals(vimRegisterInfo.getCloudRegionId(), cloudRegionId);
  }
  
  @Test
  public void getterAndSetter4cloudType(){
      final String cloudType = "openstack";
      VimRegisterInfo vimRegisterInfo = new VimRegisterInfo();
      vimRegisterInfo.setCloudType(cloudType);
      assertEquals(vimRegisterInfo.getCloudType(), cloudType);
  }
  
  @Test
  public void getterAndSetter4cloudRegionVersion(){
      final String cloudRegionVersion = "v1.0";
      VimRegisterInfo vimRegisterInfo = new VimRegisterInfo();
      vimRegisterInfo.setCloudRegionVersion(cloudRegionVersion);
      assertEquals(vimRegisterInfo.getCloudRegionVersion(), cloudRegionVersion);
  }
  
  @Test
  public void getterAndSetter4ownerDefinedType(){
      final String ownerDefinedType = "test";
      VimRegisterInfo vimRegisterInfo = new VimRegisterInfo();
      vimRegisterInfo.setOwnerDefinedType(ownerDefinedType);
      assertEquals(vimRegisterInfo.getOwnerDefinedType(), ownerDefinedType);
  }
  
  @Test
  public void getterAndSetter4cloudZone(){
      final String cloudZone = "zone1";
      VimRegisterInfo vimRegisterInfo = new VimRegisterInfo();
      vimRegisterInfo.setCloudZone(cloudZone);
      assertEquals(vimRegisterInfo.getCloudZone(), cloudZone);
  }
  
  @Test
  public void getterAndSetter4complexName(){
      final String complexName = "test";
      VimRegisterInfo vimRegisterInfo = new VimRegisterInfo();
      vimRegisterInfo.setComplexName(complexName);
      assertEquals(vimRegisterInfo.getComplexName(), complexName);
  }
  
  @Test
  public void getterAndSetter4cloudExtraInfo(){
      final String cloudExtraInfo = "test";
      VimRegisterInfo vimRegisterInfo = new VimRegisterInfo();
      vimRegisterInfo.setCloudExtraInfo(cloudExtraInfo);
      assertEquals(vimRegisterInfo.getCloudExtraInfo(), cloudExtraInfo);
  }
  
  @Test
  public void getterAndSetter4status(){
      final String status = "normal";
      VimRegisterInfo vimRegisterInfo = new VimRegisterInfo();
      vimRegisterInfo.setStatus(status);
      assertEquals(vimRegisterInfo.getStatus(), status);
  }
  
  @Test
  public void getterAndSetter4vimAuthInfos(){
      final List<VimAuthInfo> vimAuthInfos = new ArrayList<>();
      VimRegisterInfo vimRegisterInfo = new VimRegisterInfo();
      vimRegisterInfo.setVimAuthInfos(vimAuthInfos);
      assertEquals(vimRegisterInfo.getVimAuthInfos(), vimAuthInfos);
  }
  
}
