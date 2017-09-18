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
package org.onap.aai.esr.externalservice.aai;

import org.glassfish.jersey.client.ClientConfig;
import org.onap.aai.esr.entity.aai.EsrEmsDetail;
import org.onap.aai.esr.entity.aai.EsrThirdpartySdncDetail;
import org.onap.aai.esr.entity.aai.EsrVnfmDetail;

import com.eclipsesource.jaxrs.consumer.ConsumerFactory;

public class ExternalSystemProxy {

  private static IExternalSystem externalSystemproxy;

  private static String transactionId = "9999";
  private static String fromAppId = "esr-server";
  private static String authorization = AaiCommon.getAuthenticationCredentials();
  static {
    ClientConfig config = new ClientConfig();
    externalSystemproxy = ConsumerFactory.createConsumer(AaiAdapterConfig.getExternalSystemAddr(),
        config, IExternalSystem.class);
  }

  public static void registerVnfm(String vnfmId, EsrVnfmDetail esrVnfmDetail) throws Exception {
    ClientConfig config = new ClientConfig(new VnfmRegisterProvider());
    IExternalSystem registerVnfmServiceproxy = ConsumerFactory
        .createConsumer(AaiAdapterConfig.getExternalSystemAddr(), config, IExternalSystem.class);
    registerVnfmServiceproxy.registerVNFM(transactionId, fromAppId, authorization, vnfmId,
        esrVnfmDetail);
  }
  
  public static String queryVnfmDetail(String vnfmId) throws Exception {
    return externalSystemproxy.queryVNFMDetail(transactionId, fromAppId, authorization, vnfmId);
  }
  
  public static String queryVnfmList() throws Exception {
    return externalSystemproxy.queryVNFMList(transactionId, fromAppId, authorization);
  }
  
  public static void deleteVnfm(String vnfmId, String resourceVersion) throws Exception {
    externalSystemproxy.deleteVNFM(transactionId, fromAppId, authorization, vnfmId, resourceVersion);
  }
  
  public static void registerSdnc(String thirdpartySdncId, EsrThirdpartySdncDetail esrSdncDetail) throws Exception {
    ClientConfig config = new ClientConfig(new ThirdpartySdncRegisterProvider());
    IExternalSystem registerSdncServiceproxy = ConsumerFactory
        .createConsumer(AaiAdapterConfig.getExternalSystemAddr(), config, IExternalSystem.class);
    registerSdncServiceproxy.registerThirdpartySdnc(transactionId, fromAppId, authorization, thirdpartySdncId,
        esrSdncDetail);
  }
  
  public static String queryThirdpartySdncDetail(String thirdpartySdncId) throws Exception{
    return externalSystemproxy.queryThirdpartySdncDetail(transactionId, fromAppId, authorization, thirdpartySdncId);
  }
  
  public static String querySdncList() throws Exception {
    return externalSystemproxy.queryThirdpartySdncList(transactionId, fromAppId, authorization);
  }
  
  public static void deleteThirdpartySdnc(String sdncId, String resourceVersion) throws Exception {
    externalSystemproxy.deleteThirdpartySdnc(transactionId, fromAppId, authorization, sdncId, resourceVersion);
  }
  
  public static void registerEms(String emsId, EsrEmsDetail emsDetail) throws Exception {
    ClientConfig config = new ClientConfig(new EmsRegisterProvider());
    IExternalSystem registerEmsServiceproxy = ConsumerFactory
        .createConsumer(AaiAdapterConfig.getExternalSystemAddr(), config, IExternalSystem.class);
    registerEmsServiceproxy.registerEMS(transactionId, fromAppId, authorization, emsId,
        emsDetail);
  }
  
  public static String queryEmsDetail(String emsId) throws Exception {
    return externalSystemproxy.queryEMSDetail(transactionId, fromAppId, authorization, emsId);
  }
  
  public static String queryEmsList() throws Exception {
    return externalSystemproxy.queryEMSList(transactionId, fromAppId, authorization);
  }
}
