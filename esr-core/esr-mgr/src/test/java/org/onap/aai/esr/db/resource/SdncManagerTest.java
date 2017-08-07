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

import static org.junit.Assert.assertTrue;

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
import org.onap.aai.esr.entity.db.SdncData;
import org.onap.aai.esr.exception.ExtsysException;
import org.onap.aai.esr.handle.SdncHandler;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SdncHandler.class})
public class SdncManagerTest {
  private SdncHandler handler;
  private String id = "0000000000000000";

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
    SdncData data = new SdncData();
    data.setName("sdnc");
    handler = PowerMockito.spy(new SdncHandler());
    PowerMockito.doReturn(true).when(handler, "validity", data);
    try {
      id = handler.add(data).getId();
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
      handler.delete(id);
    } catch (ExtsysException error) {
      Assert.fail("Exception" + error.getMessage());
    }
  }

  @Test
  public void testAddSdncInstance_validity_false() throws Exception {
    SdncData data = new SdncData();
    data.setName("sdnc");
    PowerMockito.doReturn(false).when(handler, "validity", data);
    try {
      handler.add(data);
    } catch (ExtsysException error) {
      Assert.assertTrue(true);
      return;
    }
    Assert.fail("not Exception");
  }

  @Test
  public void testAddSdncInstance_validity_throw_ExtsysException() throws Exception {
    SdncData data = new SdncData();
    data.setName("ems2");
    PowerMockito.doReturn(false).when(handler, "validity", data);
    PowerMockito.doThrow(new ExtsysException()).when(handler, "validity", data);
    try {
      handler.add(data);
    } catch (ExtsysException error) {
      Assert.assertTrue(true);
      return;
    }
    Assert.fail("not Exception");
  }

  @Test
  public void testQuerySdncById_exist() {
    List<SdncData> list = null;
    try {
      list = handler.getSdncById(id);
    } catch (ExtsysException error) {
      Assert.fail("Exception" + error.getMessage());
      return;
    }
    Assert.assertTrue(list.size() > 0);
  }

  @Test
  public void testQuerySdncById_not_exist() {
    List<SdncData> list = null;
    try {
      list = handler.getSdncById("100001");
    } catch (ExtsysException error) {
      Assert.fail("Exception" + error.getMessage());
      return;
    }
    Assert.assertTrue(list.size() == 0);
  }

  @Test
  public void testUpdateSdnc() {
    SdncData data = new SdncData();
    data.setName("Sdnc_new");
    try {
      handler.update(data, id);
    } catch (ExtsysException error1) {
      Assert.fail("Exception" + error1.getMessage());
      return;
    }
    List<SdncData> list = null;
    try {
      list = handler.getSdncById(id);
    } catch (ExtsysException error) {
      Assert.fail("Exception" + error.getMessage());
      return;
    }
    assertTrue(list.size() > 0 && list.get(0).getName().equals("Sdnc_new"));
  }

}
