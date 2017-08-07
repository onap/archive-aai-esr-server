/**
 * Copyright 2016 ZTE Corporation.
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
package org.onap.aai.esr.handle;

import org.onap.aai.esr.common.ExtSysResuorceType;
import org.onap.aai.esr.common.Parameters;
import org.onap.aai.esr.entity.db.SdncData;
import org.onap.aai.esr.exception.ExtsysException;
import org.onap.aai.esr.util.HqlFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SdncHandler extends BaseHandler<SdncData> {
  private static final Logger logger = LoggerFactory.getLogger(SdncHandler.class);

  @Override
  public boolean validity(SdncData data) throws ExtsysException {
    return true;
  }

  public List<SdncData> getAll() throws ExtsysException {
    Map<String, String> query = new HashMap<String, String>();
    return query(query, ExtSysResuorceType.SDNC.name());
  }

  /**
   * get sdnc list by id.
   */
  public List<SdncData> getSdncById(String id) throws ExtsysException {
    Map<String, String> query = new HashMap<String, String>();
    query.put(Parameters.id.name(), id);
    return query(query, ExtSysResuorceType.SDNC.name());
  }
  
  /**
   * update sdnc list by id.
   */
  public SdncData update(SdncData sdncData, String id) throws ExtsysException {
    update(sdncData, HqlFactory.getOidFilter(Parameters.id.name(), id),
        ExtSysResuorceType.SDNC.name());
    List<SdncData> list = getSdncById(id);
    if (list.size() <= 0) {
      logger.error("update sdn info error.");
      throw new ExtsysException("0000", "update sdn info error");
    }
    return list.get(0);
  }

  public SdncData add(SdncData sdncData) throws ExtsysException {
    return create(sdncData, ExtSysResuorceType.SDNC.name());
  }
  
  /**
   * delete sdnc list by id.
   */
  public void delete(String id) throws ExtsysException {
    SdncData sdnc = new SdncData();
    sdnc.setId(id);
    delete(sdnc, ExtSysResuorceType.SDNC.name());
  }
}
