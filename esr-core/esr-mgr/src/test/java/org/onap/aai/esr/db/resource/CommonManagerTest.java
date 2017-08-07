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
package org.onap.aai.esr.db.resource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.onap.aai.esr.dao.DaoManager;
import org.onap.aai.esr.db.util.H2DbServer;
import org.onap.aai.esr.db.util.HibernateSession;
import org.onap.aai.esr.entity.db.BaseData;
import org.onap.aai.esr.entity.db.EmsData;
import org.onap.aai.esr.entity.db.VnfmData;
import org.onap.aai.esr.exception.ExtsysException;
import org.onap.aai.esr.handle.CommonHandler;
import org.onap.aai.esr.handle.EmsHandler;
import org.onap.aai.esr.handle.VnfmHandler;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest({EmsHandler.class, VnfmHandler.class})
public class CommonManagerTest {
  private CommonHandler handler = new CommonHandler();
  private EmsHandler emshandler;
  private VnfmHandler vnfHandler;
  private HashMap<String, String> idMap = new HashMap<String, String>();

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    H2DbServer.startUp();

  }

  /**
   * shut down db.
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    try {
      HibernateSession.destory();
      H2DbServer.shutDown();
    } catch (Exception error) {
      Assert.fail("Exception" + error.getMessage());
    }
  }

  /**
   * init db data.
   */
  @Before
  public void setUp() throws Exception {
    DaoManager.getInstance().setSessionFactory(HibernateSession.init());
    try {
      EmsData data = new EmsData();
      data.setName("ems");
      emshandler = PowerMockito.spy(new EmsHandler());
      PowerMockito.doReturn(true).when(emshandler, "validity", data);
      idMap.put("ems", emshandler.add(data).getId());
      VnfmData vnfm = new VnfmData();
      vnfm.setName("VNFM");
      vnfHandler = PowerMockito.spy(new VnfmHandler());
      PowerMockito.doReturn(true).when(vnfHandler, "validity", vnfm);
      idMap.put("vnfm", vnfHandler.add(vnfm).getId());
    } catch (ExtsysException error) {
      Assert.fail("Exception" + error.getMessage());
    }
  }

  /**
   * clear db data.
   */
  @After
  public void tearDown() {
    try {
      java.util.Iterator<String> it = idMap.keySet().iterator();
      while (it.hasNext()) {
        String key = it.next();
        if ("ems".equals(key)) {
          emshandler.delete(idMap.get(key));
        } else {
          vnfHandler.delete(idMap.get(key));
        }
      }

    } catch (ExtsysException error) {
      Assert.fail("Exception" + error.getMessage());
    }
  }

  @Test
  public void testQueryEmsById_exist() {
    List<BaseData> emslist = null;
    List<BaseData> vnfmlist = null;
    try {
      emslist = handler.getInstanceById(idMap.get("ems"));
      vnfmlist = handler.getInstanceById(idMap.get("vnfm"));
    } catch (ExtsysException error) {
      Assert.fail("Exception" + error.getMessage());
      return;
    }
    Assert.assertTrue(emslist.size() > 0 && vnfmlist.size() > 0);
  }

  @Test
  public void testQueryEmsById_not_exist() {
    List<BaseData> list = null;
    try {
      list = handler.getInstanceById("123456");
    } catch (ExtsysException error) {
      Assert.fail("Exception" + error.getMessage());
      return;
    }
    Assert.assertTrue(list.size() == 0);
  }

}
