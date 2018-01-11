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

import java.net.InetAddress;
import java.util.HashSet;
import java.util.Set;
import org.onap.msb.sdk.discovery.entity.MicroServiceInfo;
import org.onap.msb.sdk.httpclient.msb.MSBServiceClient;
import org.onap.msb.sdk.discovery.entity.Node;

public class MsbHelper {

    private MSBServiceClient msbClient;

    public MsbHelper(MSBServiceClient msbClient) {
        super();
        this.msbClient = msbClient;
    }



    public void registerMsb() throws Exception {


        MicroServiceInfo msinfo = new MicroServiceInfo();

        msinfo.setServiceName("aai-esr-server");
        msinfo.setVersion("v1");
        msinfo.setUrl("/api/aai-esr-server/v1");
        msinfo.setProtocol("REST");
        msinfo.setVisualRange("0|1");

        Set<Node> nodes = new HashSet<>();
        Node node1 = new Node();
        node1.setIp(InetAddress.getLocalHost().getHostAddress());
        node1.setPort("9518");
        nodes.add(node1);
        msinfo.setNodes(nodes);
        msbClient.registerMicroServiceInfo(msinfo, false);
    }
}
