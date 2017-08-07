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
import org.onap.aai.esr.entity.db.VimData;
import org.onap.aai.esr.exception.ExtsysException;
import org.onap.aai.esr.util.HqlFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class VimHandler extends BaseHandler<VimData> {
  private static final Logger logger = LoggerFactory.getLogger(VimHandler.class);

  @Override
  public boolean validity(VimData data) throws ExtsysException {
    return true;
  }

  public List<VimData> getAll() throws ExtsysException {
    Map<String, String> query = new HashMap<String, String>();
    return query(query, ExtSysResuorceType.VIM.name());
  }

  /**
   * query vim list by id.
   */
  public List<VimData> getVimById(String id) throws ExtsysException {
    Map<String, String> query = new HashMap<String, String>();
    query.put(Parameters.id.name(), id);
    return query(query, ExtSysResuorceType.VIM.name());
  }
  
  /**
   * update vim list by id.
   */
  public VimData update(VimData vimData, String id) throws ExtsysException {
    update(vimData, HqlFactory.getOidFilter(Parameters.id.name(), id),
        ExtSysResuorceType.VIM.name());
    List<VimData> list = getVimById(id);
    if (list.size() <= 0) {
      logger.error("update vim info error.");
      throw new ExtsysException("0000", "update vim info error");
    }
    return list.get(0);
  }

  public VimData add(VimData vimData) throws ExtsysException {
    return create(vimData, ExtSysResuorceType.VIM.name());
  }
  
  /**
   * delete vim list by id.
   */
  public void delete(String id) throws ExtsysException {
    VimData vim = new VimData();
    vim.setId(id);
    delete(vim, ExtSysResuorceType.VIM.name());
  }
}
