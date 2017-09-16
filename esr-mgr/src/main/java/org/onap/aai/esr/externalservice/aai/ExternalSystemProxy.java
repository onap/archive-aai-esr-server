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
    ClientConfig config = new ClientConfig(new RegisterVnfmProvider());
    IExternalSystem registerVnfmServiceproxy = ConsumerFactory
        .createConsumer(AaiAdapterConfig.getExternalSystemAddr(), config, IExternalSystem.class);
    registerVnfmServiceproxy.registerVNFM(transactionId, fromAppId, authorization, vnfmId,
        esrVnfmDetail);
  }
  
  public static String queryVnfmDetail(String vnfmId) throws Exception {
    return externalSystemproxy.queryVNFMDetail(transactionId, fromAppId, authorization, vnfmId);
  }
}
