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

public class EmsRegisterInfoTest {
  @Test
  public void getterAndSetter4emsId(){
      final String emsId = "dafadf";
      EmsRegisterInfo emsRegisterInfo = new EmsRegisterInfo();
      emsRegisterInfo.setEmsId(emsId);
      assertEquals(emsRegisterInfo.getEmsId(), emsId);
  }
  
  @Test
  public void getterAndSetter4name() {
      final String name = "dafadf";
      EmsRegisterInfo emsRegisterInfo = new EmsRegisterInfo();
      emsRegisterInfo.setName(name);
      assertEquals(emsRegisterInfo.getName(), name);
  }
  
  @Test
  public void getterAndSetter4vendor(){
      final String vendor = "dafadf";
      EmsRegisterInfo emsRegisterInfo = new EmsRegisterInfo();
      emsRegisterInfo.setVendor(vendor);
      assertEquals(emsRegisterInfo.getVendor(), vendor);
  }
  
  @Test
  public void getterAndSetter4version(){
      final String version = "dafadf";
      EmsRegisterInfo emsRegisterInfo = new EmsRegisterInfo();
      emsRegisterInfo.setVersion(version);
      assertEquals(emsRegisterInfo.getVersion(), version);
  }
  
  @Test
  public void getterAndSetter4resouceAddr(){
    EmsRegisterInfo emsRegisterInfo = new EmsRegisterInfo();
      final FtpAddr resourceAddr = new FtpAddr();
      resourceAddr.setFtptype("sftp");
      emsRegisterInfo.setResourceAddr(resourceAddr);
      assertEquals(emsRegisterInfo.getResourceAddr(), resourceAddr);
  }
  
  @Test
  public void getterAndSetter4performanceAddr(){
    EmsRegisterInfo emsRegisterInfo = new EmsRegisterInfo();
      final FtpAddr performanceAddr = new FtpAddr();
      performanceAddr.setFtptype("sftp");
      emsRegisterInfo.setPerformanceAddr(performanceAddr);
      assertEquals(emsRegisterInfo.getPerformanceAddr(), performanceAddr);
  }
  
  @Test
  public void getterAndSetter4alarmAddr(){
    EmsRegisterInfo emsRegisterInfo = new EmsRegisterInfo();
      final AlarmAddr alarmAddr = new AlarmAddr();
      alarmAddr.setIp("127.0.0.1");
      emsRegisterInfo.setAlarmAddr(alarmAddr);
      assertEquals(emsRegisterInfo.getAlarmAddr(), alarmAddr);
  }
}
