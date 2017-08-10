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
package org.onap.aai.esr.handle;

import org.onap.aai.esr.common.ExtSysResuorceType;
import org.onap.aai.esr.common.Parameters;
import org.onap.aai.esr.entity.db.EmsData;
import org.onap.aai.esr.exception.ExtsysException;
import org.onap.aai.esr.util.HqlFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EmsHandler extends BaseHandler<EmsData> {
  private static final Logger logger = LoggerFactory.getLogger(EmsHandler.class);

  @Override
  public boolean validity(EmsData data) throws ExtsysException {
    return true;
  }

  public List<EmsData> getAll() throws ExtsysException {
    Map<String, String> query = new HashMap<String, String>();
    return query(query, ExtSysResuorceType.EMS.name());
  }

  /**
   * get ems list by id.
   */
  public List<EmsData> getEmsById(String id) throws ExtsysException {
    Map<String, String> query = new HashMap<String, String>();
    query.put(Parameters.id.name(), id);
    return query(query, ExtSysResuorceType.EMS.name());
  }
  
  /**
   * update ems list by id.
   */  
  public EmsData update(EmsData emsData, String id) throws ExtsysException {
    update(emsData, HqlFactory.getOidFilter(Parameters.id.name(), id),
        ExtSysResuorceType.EMS.name());
    List<EmsData> list = getEmsById(id);
    if (list.size() <= 0) {
      logger.error("update ems info error.");
      throw new ExtsysException("0000", "update ems info error");
    }
    return list.get(0);
  }

  public EmsData add(EmsData emsData) throws ExtsysException {

    return create(emsData, ExtSysResuorceType.EMS.name());
  }
  
  /**
   * delete ems list by id.
   */  
  public void delete(String id) throws ExtsysException {
    EmsData ems = new EmsData();
    ems.setId(id);
    delete(ems, ExtSysResuorceType.EMS.name());
  }
}
