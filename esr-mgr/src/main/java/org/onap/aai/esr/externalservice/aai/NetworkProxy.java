/**
 * Copyright 2018 ZTE Corporation.
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
import org.onap.aai.esr.entity.aai.Pnf;
import org.onap.aai.esr.exception.ExtsysException;
import com.eclipsesource.jaxrs.consumer.ConsumerFactory;

public class NetworkProxy {

    private static INetwork network;
    private static String transactionId = "9999";
    private static String fromAppId = "esr-server";
    private static String authorization = AaiCommon.getAuthenticationCredentials();
    static {
        ClientConfig config = new ClientConfig();
        network =
                ConsumerFactory.createConsumer(MsbConfig.getNetworkAddr(), config, INetwork.class);
    }

    public void registerPnf(String pnfName, Pnf pnf) throws ExtsysException {
        ClientConfig config = new ClientConfig(new PnfRegisterProvider());
        INetwork registerPnfServiceproxy =
                ConsumerFactory.createConsumer(MsbConfig.getNetworkAddr(), config, INetwork.class);
        try {
            registerPnfServiceproxy.registerPnfService(transactionId, fromAppId, authorization, pnfName, pnf);
        } catch (Exception e) {
            throw new ExtsysException("PUT PNF to A&AI failed.", e);
        }
    }
    
    public String queryPNF(String pnfId) throws ExtsysException {
        try {
            return network.queryPNF(transactionId, fromAppId, authorization, pnfId);
        } catch (Exception e) {
            throw new ExtsysException("Query PNF from A&AI failed.", e);
        }
    }
    
    public String queryPnfList() throws ExtsysException {
        try {
            return network.queryPNFList(transactionId, fromAppId, authorization);
        } catch (Exception e) {
            throw new ExtsysException("Query PNF List from A&AI failed.", e);
        }
    }
}
