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

import org.junit.Test;

public class CloudRegionTest {

  @Test
  public void getterAndSetter4CloudOwner(){
      final String cloudOwner = "cloud-owner-test";
      CloudRegion cloudRegion = new CloudRegion();
      cloudRegion.setCloudOwner(cloudOwner);
      assertEquals(cloudRegion.getCloudOwner(), cloudOwner);
  }
  
  @Test
  public void getterAndSetter4CloudRegionId(){
      final String cloudRegionId = "cloud-region-id-test";
      CloudRegion cloudRegion = new CloudRegion();
      cloudRegion.setCloudRegionId(cloudRegionId);
      assertEquals(cloudRegion.getCloudRegionId(), cloudRegionId);
  }
  
  @Test
  public void getterAndSetter4CloudType(){
      final String cloudType = "cloud-type-test";
      CloudRegion cloudRegion = new CloudRegion();
      cloudRegion.setCloudType(cloudType);
      assertEquals(cloudRegion.getCloudType(), cloudType);
  }
  
  @Test
  public void getterAndSetter4cloudRegionVersion(){
      final String cloudRegionVersion = "cloud-region-version-test";
      CloudRegion cloudRegion = new CloudRegion();
      cloudRegion.setCloudRegionVersion(cloudRegionVersion);
      assertEquals(cloudRegion.getCloudRegionVersion(), cloudRegionVersion);
  }
  
  @Test
  public void getterAndSetter4ownerDefinedType(){
      final String ownerDefinedType = "owner-defined-type-test";
      CloudRegion cloudRegion = new CloudRegion();
      cloudRegion.setOwnerDefinedType(ownerDefinedType);
      assertEquals(cloudRegion.getOwnerDefinedType(), ownerDefinedType);
  }
  
  @Test
  public void getterAndSetter4cloudZone(){
      final String cloudZone = "cloudZone-test";
      CloudRegion cloudRegion = new CloudRegion();
      cloudRegion.setCloudZone(cloudZone);
      assertEquals(cloudRegion.getCloudZone(), cloudZone);
  }
  
  @Test
  public void getterAndSetter4complexName(){
      final String complexName = "complexName-test";
      CloudRegion cloudRegion = new CloudRegion();
      cloudRegion.setComplexName(complexName);
      assertEquals(cloudRegion.getComplexName(), complexName);
  }
  
  @Test
  public void getterAndSetter4cloudExtraInfo(){
      final String cloudExtraInfo = "cloudExtraInfo-test";
      CloudRegion cloudRegion = new CloudRegion();
      cloudRegion.setCloudExtraInfo(cloudExtraInfo);
      assertEquals(cloudRegion.getCloudExtraInfo(), cloudExtraInfo);
  }
}
