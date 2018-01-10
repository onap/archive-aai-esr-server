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
import org.onap.aai.esr.common.MsbConfig;
import org.onap.aai.esr.entity.aai.EsrEmsDetail;
import org.onap.aai.esr.entity.aai.EsrThirdpartySdncDetail;
import org.onap.aai.esr.entity.aai.EsrVnfmDetail;
import org.onap.aai.esr.exception.ExtsysException;

import com.eclipsesource.jaxrs.consumer.ConsumerFactory;

public class ExternalSystemProxy {

  public static boolean isTest = false;
  private static IExternalSystem externalSystemproxy;
  private static String transactionId = "9999";
  private static String fromAppId = "esr-server";
  private static String authorization = AaiCommon.getAuthenticationCredentials();
  static {
    ClientConfig config = new ClientConfig();
    externalSystemproxy = ConsumerFactory.createConsumer(MsbConfig.getExternalSystemAddr(),
        config, IExternalSystem.class);
  }

  public static void registerVnfm(String vnfmId, EsrVnfmDetail esrVnfmDetail)
      throws ExtsysException {
    if (isTest) {

    } else {
      ClientConfig config = new ClientConfig(new VnfmRegisterProvider());
      IExternalSystem registerVnfmServiceproxy = ConsumerFactory
          .createConsumer(MsbConfig.getExternalSystemAddr(), config, IExternalSystem.class);
      try {
        registerVnfmServiceproxy.registerVNFM(transactionId, fromAppId, authorization, vnfmId,
            esrVnfmDetail);
      } catch (Exception e) {
        throw new ExtsysException("PUT VNFM to A&AI failed.", e);
      }
    }
  }
  
  public static String queryVnfmDetail(String vnfmId) throws ExtsysException {
    if(isTest) {
      String esrVnfmDetailStr = "{\"vnfm-id\":\"123456\","
          + "\"vim-id\":\"987654\","
          + "\"certificate-url\":\"http://11.22.33.44:5000/v3\","
          + "\"esr-system-info-list\":{"
          + "\"esr-system-info\":[{"
          + "\"esr-system-info-id\":\"qwerty\","
          + "\"system-name\":\"ONAP VNFM\","
          + "\"type\":\"vnfm\","
          + "\"vendor\":\"zte\","
          + "\"version\":\"v1\","
          + "\"service-url\":\"http://10.11.22.33:8000\","
          + "\"user-name\":\"onap\","
          + "\"password\":\"987654\","
          + "\"system-type\":\"VNFM\"}]}}";
      return esrVnfmDetailStr;
    }
    try {
      return externalSystemproxy.queryVNFMDetail(transactionId, fromAppId, authorization, vnfmId);
    } catch (Exception e) {
      throw new ExtsysException("Query VNFM detail from A&AI failed.", e);
    }
  }
  
  public static String queryVnfmList() throws ExtsysException {
    if(isTest) {
      String vnfmListStr = "{\"esr-vnfm\": "
          + "[{\"vnfm-id\": \"123456\","
          + "\"vim-id\": \"987654\","
          + "\"certificate-url\": \"http://11.22.33.44:5000/v3\","
          + "\"resource-version\": \"1\"}]}";
      return vnfmListStr;
    }
    try {
      return externalSystemproxy.queryVNFMList(transactionId, fromAppId, authorization);
    } catch (Exception e) {
      throw new ExtsysException("Query VNFM list from A&AI failed.", e);
    }
  }
  
  public static void deleteVnfm(String vnfmId, String resourceVersion) throws ExtsysException {
    if(!isTest) {
      try {
        externalSystemproxy.deleteVNFM(transactionId, fromAppId, authorization, vnfmId, resourceVersion);
      } catch (Exception e) {
        throw new ExtsysException("Delete VNFM from A&AI failed.", e);
      }
    }
  }
  
  public static void registerSdnc(String thirdpartySdncId, EsrThirdpartySdncDetail esrSdncDetail) throws ExtsysException {
    if(!isTest) {
      ClientConfig config = new ClientConfig(new ThirdpartySdncRegisterProvider());
      IExternalSystem registerSdncServiceproxy = ConsumerFactory
          .createConsumer(MsbConfig.getExternalSystemAddr(), config, IExternalSystem.class);
      try {
        registerSdncServiceproxy.registerThirdpartySdnc(transactionId, fromAppId, authorization, thirdpartySdncId,
            esrSdncDetail);
      } catch (Exception e) {
        throw new ExtsysException("PUT thirdparty SDNC to A&AI failed.", e);
      }
    }
  }
  
  public static String queryThirdpartySdncDetail(String thirdpartySdncId) throws ExtsysException {
    if(isTest) {
      String sdncDetail = "{\"thirdparty-sdnc-id\":\"123456\","
        + "\"location\":\"edge\","
        + "\"product-name\":\"thirdparty SDNC\","
        + "\"esr-system-info-list\":{"
        + "\"esr-system-info\":"
        + "[{\"esr-system-info-id\":\"987654\","
        + "\"system-name\":\"SDNC_TEST\","
        + "\"type\":\"SDNC\","
        + "\"vendor\":\"zte\","
        + "\"version\":\"v1\","
        + "\"service-url\":\"http://127.0.0.1:8000\","
        + "\"user-name\":\"nancy\","
        + "\"password\":\"123987\","
        + "\"system-type\":\"thirdparty_SDNC\","
        + "\"protocol\":\"protocol\"}]}}";
      return sdncDetail;
    }
    try {
      return externalSystemproxy.queryThirdpartySdncDetail(transactionId, fromAppId, authorization, thirdpartySdncId);
    } catch (Exception e) {
      throw new ExtsysException("Query thirdparty SDNC detail from A&AI failed.", e);
    }
  }
  
  public static String querySdncList() throws ExtsysException {
    if(isTest) {
      String sdncList = "{\"esr-thirdparty-sdnc\": "
          + "[{\"thirdparty-sdnc-id\": \"123456\","
          + "\"location\": \"edge\","
          + "\"product-name\": \"thirdparty SDNC\","
          + "\"resource-version\": \"1\"}]}";
      return sdncList;
    }
    try {
      return externalSystemproxy.queryThirdpartySdncList(transactionId, fromAppId, authorization);
    } catch (Exception e) {
      throw new ExtsysException("Query thirdparty SDNC list from A&AI failed.", e);
    }
  }
  
  public static void deleteThirdpartySdnc(String sdncId, String resourceVersion) throws ExtsysException {
    if(!isTest) {
      try {
        externalSystemproxy.deleteThirdpartySdnc(transactionId, fromAppId, authorization, sdncId, resourceVersion);
      } catch (Exception e) {
        throw new ExtsysException("Delete thirdparty SDNC from A&AI failed.", e);
      }
    }
  }
  
  public static void registerEms(String emsId, EsrEmsDetail emsDetail) throws ExtsysException {
    ClientConfig config = new ClientConfig(new EmsRegisterProvider());
    IExternalSystem registerEmsServiceproxy = ConsumerFactory
        .createConsumer(MsbConfig.getExternalSystemAddr(), config, IExternalSystem.class);
    try {
      registerEmsServiceproxy.registerEMS(transactionId, fromAppId, authorization, emsId,
          emsDetail);
    } catch (Exception e) {
      throw new ExtsysException("PUT EMS to A&AI failed.", e);
    }
  }
  
  public static String queryEmsDetail(String emsId) throws ExtsysException {
    try {
      return externalSystemproxy.queryEMSDetail(transactionId, fromAppId, authorization, emsId);
    } catch (Exception e) {
      throw new ExtsysException("Query EMS detail from A&AI failed.", e);
    }
  }
  
  public static String queryEmsList() throws ExtsysException {
    try {
      return externalSystemproxy.queryEMSList(transactionId, fromAppId, authorization);
    } catch (Exception e) {
      throw new ExtsysException("Query EMS list from A&AI failed.", e);
    }
  }
  
  public static void deleteEms(String emsId, String resourceVersion) throws ExtsysException {
    try {
      externalSystemproxy.deleteEMS(transactionId, fromAppId, authorization, emsId, resourceVersion);
    } catch (Exception e) {
      throw new ExtsysException("Delete EMS from A&AI failed.", e);
    }
  }
}
