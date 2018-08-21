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

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import org.onap.aai.esr.entity.aai.EsrVnfmList;
import org.onap.aai.esr.entity.aai.Pnf;
import org.onap.aai.esr.entity.aai.PnfList;
import org.onap.aai.esr.entity.rest.PnfRegisterInfo;
import org.onap.aai.esr.entity.rest.VnfmRegisterInfo;
import org.onap.aai.esr.exception.ExceptionUtil;
import org.onap.aai.esr.exception.ExtsysException;
import org.onap.aai.esr.externalservice.aai.NetworkProxy;
import org.onap.aai.esr.util.PnfManagerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;

public class PnfManagerWrapper {
    private static PnfManagerWrapper pnfManagerWrapper;
    private static final Logger LOG = LoggerFactory.getLogger(PnfManagerWrapper.class);

    private static PnfManagerUtil pnfManagerUtil = new PnfManagerUtil();
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
        List<PnfRegisterInfo> esrPnfList = new ArrayList<>();
        PnfList pnfList = new PnfList();
        try {
            String pnflistStr = networkProxy.queryPnfList();
            pnfList = new Gson().fromJson(pnflistStr, PnfList.class);
            LOG.info("Response from AAI by query PNF list: " + pnflistStr);
            esrPnfList = getEsrPnfList(pnfList);
            return Response.ok(esrPnfList).build();
        } catch (ExtsysException e) {
            LOG.error("Query VNFM list failed !", e);
            return Response.ok(esrPnfList).build();
        }
    }

    /**
     * @param pnfList
     * @return
     */
    private List<PnfRegisterInfo> getEsrPnfList(PnfList pnfList) {
        List<PnfRegisterInfo> esrPnfList = new ArrayList<>();
        for (int i = 0; i < pnfList.getPnf().size(); i++) {
            Pnf pnf = pnfList.getPnf().get(i);
            PnfRegisterInfo pnfRegisterInfo = pnfManagerUtil.pnf2PnfRegisterInfo(pnf);
            esrPnfList.add(pnfRegisterInfo);
        }
        return esrPnfList;
    }

    /**
     * @param pnfId
     * @return
     */
    public Response queryPnfById(String pnfId) {
        PnfRegisterInfo pnfRegisterInfo = queryPnf(pnfId);
        return Response.ok(pnfRegisterInfo).build();
    }
    
    private PnfRegisterInfo queryPnf(String pnfId) {
        Pnf pnf = new Pnf();
        PnfRegisterInfo pnfRegisterInfo = new PnfRegisterInfo();
        try {
            String pnfStr = networkProxy.queryPNF(pnfId);
            LOG.info("Response from AAI by query PNF: " + pnfStr);
            pnf = new Gson().fromJson(pnfStr, Pnf.class);
            pnfRegisterInfo = pnfManagerUtil.pnf2PnfRegisterInfo(pnf);
        } catch (ExtsysException e) {
            LOG.error("Query PNF detail failed! PNF ID: " + pnfId, e);
        }
        return pnfRegisterInfo;
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
        Pnf pnf = pnfManagerUtil.pnfRegisterInfo2pnf(pnfRegisterInfo);
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
