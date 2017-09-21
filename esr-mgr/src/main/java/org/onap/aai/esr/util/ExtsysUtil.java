/**
 * Copyright 2016-2017 ZTE Corporation.
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

import com.google.gson.Gson;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.UUID;

import org.onap.aai.esr.entity.aai.EsrSystemInfo;
import org.onap.aai.esr.entity.aai.EsrSystemInfoList;

public class ExtsysUtil {

  public static String generateId() {
    return UUID.randomUUID().toString();
  }

  /**
   * change object to str.
   */
  public static String objectToString(Object obj) {
    Gson gson = new Gson();
    if (obj != null) {
      return gson.toJson(obj);
    } else {
      return null;
    }
  }

  public EsrSystemInfoList getEsrSystemInfoListFromAuthInfo(EsrSystemInfo esrSystemInfoObj) {
    EsrSystemInfoList esrSystemInfoList = new EsrSystemInfoList();
    ArrayList<EsrSystemInfo> esrSystemInfo = new ArrayList<EsrSystemInfo>();
    esrSystemInfo.add(esrSystemInfoObj);
    esrSystemInfoList.setEsrSystemInfo(esrSystemInfo);
    return esrSystemInfoList;
  }
  
  public EsrSystemInfoList getEsrSystemInfoListFromAuthInfoList(ArrayList<EsrSystemInfo> esrSystemInfo) {
    EsrSystemInfoList esrSystemInfoList = new EsrSystemInfoList();
    esrSystemInfoList.setEsrSystemInfo(esrSystemInfo);;
    return esrSystemInfoList;
  }
}
