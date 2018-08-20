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
package org.onap.aai.esr.wrapper;

import javax.ws.rs.core.Response;
import org.onap.aai.esr.entity.aai.Pnf;
import org.onap.aai.esr.entity.rest.PnfRegisterInfo;
import org.onap.aai.esr.exception.ExceptionUtil;
import org.onap.aai.esr.exception.ExtsysException;
import org.onap.aai.esr.externalservice.aai.NetworkProxy;
import org.onap.aai.esr.util.PnfManagerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PnfManagerWrapper {
    private static PnfManagerWrapper pnfManagerWrapper;
    private static final Logger LOG = LoggerFactory.getLogger(PnfManagerWrapperTest.class);

//    private static PnfManagerUtil pnfManagerUtil = new PnfManagerUtil();
    private static NetworkProxy networkProxy = new NetworkProxy();

    /**
     * get PnfManagerWrapper instance.
     * 
     * @return pnf manager wrapper instance
     */
    public static PnfManagerWrapper getInstance() {
        if (pnfManagerWrapper == null) {
            pnfManagerWrapper = new PnfManagerWrapper(networkProxy);
        }
        return pnfManagerWrapper;
    }
    
    public PnfManagerWrapper(NetworkProxy networkProxy){
        PnfManagerWrapper.networkProxy = networkProxy;
    }

    /**
     * @return
     */
    public Response queryPnfList() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param pnfId
     * @return
     */
    public Response queryPnfById(String pnfId) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param pnfId
     * @return
     */
    public Response delPnf(String pnfId) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param pnf
     * @param pnfId
     * @return
     */
    public Response updatePnf(PnfRegisterInfo pnf, String pnfId) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param pnf
     * @return
     */
    public Response registerPnf(PnfRegisterInfo pnfRegisterInfo) {
        Pnf pnf = PnfManagerUtil.pnfRegisterInfo2pnf(pnfRegisterInfo);
        String pnfName = pnf.getPnfName();
        try {
            networkProxy.registerPnf(pnfName, pnf);
            return Response.ok().build();
        } catch (ExtsysException e) {
            LOG.error("Register PNF failed !", e);
            throw ExceptionUtil.buildExceptionResponse(e.getMessage());
        }
    }
}
