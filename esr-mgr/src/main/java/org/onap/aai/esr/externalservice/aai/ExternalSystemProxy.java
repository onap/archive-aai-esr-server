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

    private static IExternalSystem externalSystem;
    private static String transactionId = "9999";
    private static String fromAppId = "esr-server";
    private static String authorization = AaiCommon.getAuthenticationCredentials();
    static {
        ClientConfig config = new ClientConfig();
        externalSystem =
                ConsumerFactory.createConsumer(MsbConfig.getExternalSystemAddr(), config, IExternalSystem.class);
    }
    
    public void registerVnfm(String vnfmId, EsrVnfmDetail esrVnfmDetail) throws ExtsysException {
        ClientConfig config = new ClientConfig(new VnfmRegisterProvider());
        IExternalSystem registerVnfmServiceproxy =
                ConsumerFactory.createConsumer(MsbConfig.getExternalSystemAddr(), config, IExternalSystem.class);
        try {
            registerVnfmServiceproxy.registerVNFM(transactionId, fromAppId, authorization, vnfmId, esrVnfmDetail);
        } catch (Exception e) {
            throw new ExtsysException("PUT VNFM to A&AI failed.", e);
        }
    }

    public String queryVnfmDetail(String vnfmId) throws ExtsysException {
        try {
            return externalSystem.queryVNFMDetail(transactionId, fromAppId, authorization, vnfmId);
        } catch (Exception e) {
            throw new ExtsysException("Query VNFM detail from A&AI failed.", e);
        }
    }

    public String queryVnfmList() throws ExtsysException {
        try {
            return externalSystem.queryVNFMList(transactionId, fromAppId, authorization);
        } catch (Exception e) {
            throw new ExtsysException("Query VNFM list from A&AI failed.", e);
        }
    }

    public void deleteVnfm(String vnfmId, String resourceVersion) throws ExtsysException {
        try {
            externalSystem.deleteVNFM(transactionId, fromAppId, authorization, vnfmId, resourceVersion);
        } catch (Exception e) {
            throw new ExtsysException("Delete VNFM from A&AI failed.", e);
        }
    }

    public void registerSdnc(String thirdpartySdncId, EsrThirdpartySdncDetail esrSdncDetail) throws ExtsysException {
        ClientConfig config = new ClientConfig(new ThirdpartySdncRegisterProvider());
        IExternalSystem registerSdncServiceproxy =
                ConsumerFactory.createConsumer(MsbConfig.getExternalSystemAddr(), config, IExternalSystem.class);
        try {
            registerSdncServiceproxy.registerThirdpartySdnc(transactionId, fromAppId, authorization, thirdpartySdncId,
                    esrSdncDetail);
        } catch (Exception e) {
            throw new ExtsysException("PUT thirdparty SDNC to A&AI failed.", e);
        }
    }

    public String queryThirdpartySdncDetail(String thirdpartySdncId) throws ExtsysException {
        try {
            return externalSystem.queryThirdpartySdncDetail(transactionId, fromAppId, authorization,
                    thirdpartySdncId);
        } catch (Exception e) {
            throw new ExtsysException("Query thirdparty SDNC detail from A&AI failed.", e);
        }
    }

    public String querySdncList() throws ExtsysException {
        try {
            return externalSystem.queryThirdpartySdncList(transactionId, fromAppId, authorization);
        } catch (Exception e) {
            throw new ExtsysException("Query thirdparty SDNC list from A&AI failed.", e);
        }
    }

    public void deleteThirdpartySdnc(String sdncId, String resourceVersion) throws ExtsysException {
        try {
            externalSystem.deleteThirdpartySdnc(transactionId, fromAppId, authorization, sdncId, resourceVersion);
        } catch (Exception e) {
            throw new ExtsysException("Delete thirdparty SDNC from A&AI failed.", e);
        }
    }

    public void registerEms(String emsId, EsrEmsDetail emsDetail) throws ExtsysException {
        ClientConfig config = new ClientConfig(new EmsRegisterProvider());
        IExternalSystem registerEmsServiceproxy =
                ConsumerFactory.createConsumer(MsbConfig.getExternalSystemAddr(), config, IExternalSystem.class);
        try {
            registerEmsServiceproxy.registerEMS(transactionId, fromAppId, authorization, emsId, emsDetail);
        } catch (Exception e) {
            throw new ExtsysException("PUT EMS to A&AI failed.", e);
        }
    }

    public String queryEmsDetail(String emsId) throws ExtsysException {
        try {
            return externalSystem.queryEMSDetail(transactionId, fromAppId, authorization, emsId);
        } catch (Exception e) {
            throw new ExtsysException("Query EMS detail from A&AI failed.", e);
        }
    }

    public String queryEmsList() throws ExtsysException {
        try {
            return externalSystem.queryEMSList(transactionId, fromAppId, authorization);
        } catch (Exception e) {
            throw new ExtsysException("Query EMS list from A&AI failed.", e);
        }
    }

    public void deleteEms(String emsId, String resourceVersion) throws ExtsysException {
        try {
            externalSystem.deleteEMS(transactionId, fromAppId, authorization, emsId, resourceVersion);
        } catch (Exception e) {
            throw new ExtsysException("Delete EMS from A&AI failed.", e);
        }
    }
}
