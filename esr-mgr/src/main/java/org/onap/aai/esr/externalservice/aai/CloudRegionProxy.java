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
import org.onap.aai.esr.entity.aai.CloudRegionDetail;
import org.onap.aai.esr.exception.ExtsysException;
import com.eclipsesource.jaxrs.consumer.ConsumerFactory;

public class CloudRegionProxy {

    private static ICloudRegion adapterServiceproxy;

    private static String transactionId = "9999";
    private static String fromAppId = "esr-server";
    private static String authorization = AaiCommon.getAuthenticationCredentials();
    static {
        ClientConfig config = new ClientConfig();
        adapterServiceproxy =
                ConsumerFactory.createConsumer(MsbConfig.getCloudInfrastructureAddr(), config, ICloudRegion.class);
    }

    public void registerVim(String cloudOwner, String cloudRegionId, CloudRegionDetail cloudRegion)
            throws ExtsysException {
        ClientConfig config = new ClientConfig(new VimRegisterProvider());
        ICloudRegion registerVimServiceproxy =
                ConsumerFactory.createConsumer(MsbConfig.getCloudInfrastructureAddr(), config, ICloudRegion.class);
        try {
            registerVimServiceproxy.registerVIMService(transactionId, fromAppId, authorization, cloudOwner,
                    cloudRegionId, cloudRegion);
        } catch (Exception e) {
            throw new ExtsysException("PUT cloud region to A&AI failed.", e);
        }
    }

    public String queryVimDetail(String cloud_owner, String cloud_region_id) throws ExtsysException {
        try {
            return adapterServiceproxy.queryVIMDetail(transactionId, fromAppId, authorization, cloud_owner,
                    cloud_region_id);
        } catch (Exception e) {
            throw new ExtsysException("Query cloud region detail from A&AI failed.", e);
        }
    }

    public String qureyVimList() throws ExtsysException {
        try {
            return adapterServiceproxy.queryVIMList(transactionId, fromAppId, authorization);
        } catch (Exception e) {
            throw new ExtsysException("Query cloud region list from A&AI failed.", e);
        }
    }

    public void deleteVim(String cloud_owner, String cloud_region_id, String resourceVersion) throws ExtsysException {
        try {
            adapterServiceproxy.deleteVim(transactionId, fromAppId, authorization, cloud_owner, cloud_region_id,
                    resourceVersion);
        } catch (Exception e) {
            throw new ExtsysException("Delete cloud region from A&AI failed.", e);
        }
    }
}
