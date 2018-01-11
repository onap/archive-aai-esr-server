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
package org.onap.aai.esr.common;


public class MsbConfig {

    protected static String msbServerAddr;

    protected static String cloudInfrastructureAddr;

    protected static String externalSystemAddr;

    protected static String multiCloudAddr;

    protected static String msbDiscoveryIp;

    protected static String msbDiscoveryPort;

    public static void setCloudInfrastructureAddr(String address) {
        cloudInfrastructureAddr = address;
    }

    public static String getCloudInfrastructureAddr() {
        return msbServerAddr + "/api/aai-cloudInfrastructure/v11";
    }

    public static String getExternalSystemAddr() {
        return msbServerAddr + "/api/aai-externalSystem/v11";
    }

    public static void setExternalSystemAddr(String Addr) {
        externalSystemAddr = Addr;
    }

    public static void setMultiCloudAddr(String address) {
        multiCloudAddr = address;
    }

    public static String getMultiCloudAddr() {
        return msbServerAddr + "/api/multicloud/v0";
    }

    public static String getMsbDiscoveryIp() {
        return msbDiscoveryIp;
    }

    public static void setMsbDiscoveryIp(String discoveryIp) {
        msbDiscoveryIp = discoveryIp;
    }

    public static String getMsbDiscoveryPort() {
        return msbDiscoveryPort;
    }

    public static void setMsbDiscoveryPort(String discoveryPort) {
        msbDiscoveryPort = discoveryPort;
    }

    public static String getMsbServerAddr() {
        return msbServerAddr;
    }

    public static void setMsbServerAddr(String msbServerAddr) {
        MsbConfig.msbServerAddr = msbServerAddr;
    }
}
