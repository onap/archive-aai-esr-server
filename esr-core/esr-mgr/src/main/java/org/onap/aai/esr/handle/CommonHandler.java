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
import org.onap.aai.esr.entity.aai.BaseData;
import org.onap.aai.esr.exception.ExtsysException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CommonHandler extends BaseHandler<BaseData> {

  public List<BaseData> getAll() throws ExtsysException {
    Map<String, String> query = new HashMap<String, String>();
    return query(query, ExtSysResuorceType.BASE.name());
  }

  /**
   * query instance list by id.
   */
  public List<BaseData> getInstanceById(String id) throws ExtsysException {
    Map<String, String> query = new HashMap<String, String>();
    query.put(Parameters.id.name(), id);
    return query(query, ExtSysResuorceType.BASE.name());
  }

  @Override
  public boolean validity(BaseData data) throws ExtsysException {
    // TODO Auto-generated method stub
    return false;
  }

}
