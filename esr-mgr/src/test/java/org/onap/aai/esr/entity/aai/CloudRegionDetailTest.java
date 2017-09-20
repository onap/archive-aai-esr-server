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

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class CloudRegionDetailTest {
  @Test
  public void getterAndSetter4CloudOwner(){
      final String cloudOwner = "cloud-owner-test";
      CloudRegionDetail cloudRegion = new CloudRegionDetail();
      cloudRegion.setCloudOwner(cloudOwner);
      assertEquals(cloudRegion.getCloudOwner(), cloudOwner);
  }
  
  
  @Test
  public void getterAndSetter4CloudRegionId(){
      final String cloudRegionId = "cloud-region-id-test";
      CloudRegionDetail cloudRegion = new CloudRegionDetail();
      cloudRegion.setCloudRegionId(cloudRegionId);
      assertEquals(cloudRegion.getCloudRegionId(), cloudRegionId);
  }
  
  @Test
  public void getterAndSetter4CloudType(){
      final String cloudType = "cloud-type-test";
      CloudRegionDetail cloudRegion = new CloudRegionDetail();
      cloudRegion.setCloudType(cloudType);
      assertEquals(cloudRegion.getCloudType(), cloudType);
  }
  
  @Test
  public void getterAndSetter4cloudRegionVersion(){
      final String cloudRegionVersion = "cloud-region-version-test";
      CloudRegionDetail cloudRegion = new CloudRegionDetail();
      cloudRegion.setCloudRegionVersion(cloudRegionVersion);
      assertEquals(cloudRegion.getCloudRegionVersion(), cloudRegionVersion);
  }
  
  @Test
  public void getterAndSetter4ownerDefinedType(){
      final String ownerDefinedType = "owner-defined-type-test";
      CloudRegionDetail cloudRegion = new CloudRegionDetail();
      cloudRegion.setOwnerDefinedType(ownerDefinedType);
      assertEquals(cloudRegion.getOwnerDefinedType(), ownerDefinedType);
  }
  
  @Test
  public void getterAndSetter4cloudZone(){
      final String cloudZone = "cloudZone-test";
      CloudRegionDetail cloudRegion = new CloudRegionDetail();
      cloudRegion.setCloudZone(cloudZone);
      assertEquals(cloudRegion.getCloudZone(), cloudZone);
  }
  
  @Test
  public void getterAndSetter4complexName(){
      final String complexName = "complexName-test";
      CloudRegionDetail cloudRegion = new CloudRegionDetail();
      cloudRegion.setComplexName(complexName);
      assertEquals(cloudRegion.getComplexName(), complexName);
  }
  
  @Test
  public void getterAndSetter4cloudExtraInfo(){
      final String cloudExtraInfo = "cloudExtraInfo-test";
      CloudRegionDetail cloudRegion = new CloudRegionDetail();
      cloudRegion.setCloudExtraInfo(cloudExtraInfo);
      assertEquals(cloudRegion.getCloudExtraInfo(), cloudExtraInfo);
  }
  
  @Test
  public void getterAndSetter4esrSystemInfoList(){
      final EsrSystemInfoList esrSystemInfoList = new EsrSystemInfoList();
      ArrayList<EsrSystemInfo> esrSystemInfo = new ArrayList<EsrSystemInfo>();
      EsrSystemInfo esrSystemInfoObj = new EsrSystemInfo();
      esrSystemInfoObj.setEsrSystemInfoId("123");
      esrSystemInfo.add(esrSystemInfoObj );
      esrSystemInfoList.setEsrSystemInfo(esrSystemInfo);
      CloudRegionDetail cloudRegion = new CloudRegionDetail();
      cloudRegion.setEsrSystemInfoList(esrSystemInfoList);
      assertEquals(cloudRegion.getEsrSystemInfoList(), esrSystemInfoList);
  }
}
