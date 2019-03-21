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
package org.onap.aai.esr.util;

import org.onap.aai.esr.common.SystemType;
import org.onap.aai.esr.entity.aai.EsrSystemInfo;
import org.onap.aai.esr.entity.aai.EsrSystemInfoList;
import org.onap.aai.esr.entity.aai.EsrNfvoDetail;
import org.onap.aai.esr.entity.rest.NfvoRegisterInfo;

public class NfvoManagerUtil {
    private static ExtsysUtil extsysUtil = new ExtsysUtil();

    public EsrNfvoDetail nfvoRegisterInfo2EsrNfvo(NfvoRegisterInfo nfvoRegisterInfo) {
        EsrNfvoDetail esrNfvo = new EsrNfvoDetail();
        esrNfvo.setNfvoId(extsysUtil.generateId());
        esrNfvo.setApiroot(nfvoRegisterInfo.getApiroot());
        EsrSystemInfo authInfo = getAuthInfoFromNfvoRegisterInfo(nfvoRegisterInfo);
        EsrSystemInfoList esrSystemInfo = extsysUtil.getEsrSystemInfoListFromAuthInfo(authInfo);
        esrNfvo.setEsrSystemInfoList(esrSystemInfo);
        return esrNfvo;
    }

    /**
     * @param nfvoRegisterInfo nfvo register informantion from portal
     * @return
     */
    private EsrSystemInfo getAuthInfoFromNfvoRegisterInfo(NfvoRegisterInfo nfvoRegisterInfo) {
        EsrSystemInfo authInfo = new EsrSystemInfo();
        authInfo.setEsrSystemInfoId(extsysUtil.generateId());
        authInfo.setSystemName(nfvoRegisterInfo.getName());
        authInfo.setVendor(nfvoRegisterInfo.getVendor());
        authInfo.setVersion(nfvoRegisterInfo.getVersion());
        authInfo.setServiceUrl(nfvoRegisterInfo.getUrl());
        authInfo.setUserName(nfvoRegisterInfo.getUserName());
        authInfo.setPassword(nfvoRegisterInfo.getPassword());
        authInfo.setSystemType(SystemType.NFVO.toString());
        return authInfo;
    }

    public NfvoRegisterInfo esrNfvo2NfvoRegisterInfo(EsrNfvoDetail esrNfvo) {
        NfvoRegisterInfo nfvoRegisterInfo = new NfvoRegisterInfo();
        nfvoRegisterInfo.setNfvoId(esrNfvo.getNfvoId());
        nfvoRegisterInfo.setApiroot(esrNfvo.getApiroot());
        EsrSystemInfo authInfo = esrNfvo.getEsrSystemInfoList().getEsrSystemInfo().get(0);
        nfvoRegisterInfo.setName(authInfo.getSystemName());
        nfvoRegisterInfo.setPassword(authInfo.getPassword());
        nfvoRegisterInfo.setUrl(authInfo.getServiceUrl());
        nfvoRegisterInfo.setUserName(authInfo.getUserName());
        nfvoRegisterInfo.setVendor(authInfo.getVendor());
        nfvoRegisterInfo.setVersion(authInfo.getVersion());
        return nfvoRegisterInfo;
    }

}
