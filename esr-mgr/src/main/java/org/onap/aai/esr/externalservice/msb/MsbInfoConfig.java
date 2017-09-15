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
package org.onap.aai.esr.externalservice.msb;

public class MsbInfoConfig {

  protected static String msbDiscoveryIp;
  
  protected static String msbDiscoveryPort;

  public static String getMsbDiscoveryIp() {
    return msbDiscoveryIp;
  }

  public static void setMsbDiscoveryIp(String msbDiscoveryIp) {
    MsbInfoConfig.msbDiscoveryIp = msbDiscoveryIp;
  }

  public static String getMsbDiscoveryPort() {
    return msbDiscoveryPort;
  }

  public static void setMsbDiscoveryPort(String msbDiscoveryPort) {
    MsbInfoConfig.msbDiscoveryPort = msbDiscoveryPort;
  }
}
