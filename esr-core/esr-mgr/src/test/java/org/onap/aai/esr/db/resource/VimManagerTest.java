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
package org.onap.aai.esr.db.resource;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.onap.aai.esr.dao.DaoManager;
import org.onap.aai.esr.db.util.H2DbServer;
import org.onap.aai.esr.db.util.HibernateSession;
import org.onap.aai.esr.entity.aai.VimData;
import org.onap.aai.esr.exception.ExtsysException;
import org.onap.aai.esr.handle.VimHandler;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest({VimHandler.class})
public class VimManagerTest {
  private VimHandler handler;
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
    VimData data = new VimData();
    handler = PowerMockito.spy(new VimHandler());
    PowerMockito.doReturn(true).when(handler, "validity", data);
    data.setId("10000");
    data.setName("vim");
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

  @Ignore
  @Test
  public void testAddVimInstance_validity_false() throws Exception {
    VimData data = new VimData();
    data.setName("Vim");
    PowerMockito.doReturn(false).when(handler, "validity", data);
    try {
      handler.add(data);
    } catch (ExtsysException error) {
      Assert.assertTrue(true);
      return;
    }
    Assert.fail("not Exception");
  }

  @Ignore
  @Test
  public void testAddVimInstance_validity_throw_ExtsysException() throws Exception {
    VimData data = new VimData();
    data.setName("vim2");
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

  @Ignore
  @Test
  public void testQueryVimById_exist() {
    List<VimData> list = null;
    try {
      list = handler.getVimById(id);
    } catch (ExtsysException error) {
      Assert.fail("Exception" + error.getMessage());
      return;
    }
    Assert.assertTrue(list.size() > 0);
  }

  @Ignore
  @Test
  public void testQueryVimById_not_exist() {
    List<VimData> list = null;
    try {
      list = handler.getVimById("100001");
    } catch (ExtsysException error) {
      Assert.fail("Exception" + error.getMessage());
      return;
    }
    Assert.assertTrue(list.size() == 0);
  }

  @Ignore
  @Test
  public void testUpdateVim() {
    VimData data = new VimData();
    data.setName("vim_new");
    try {
      handler.update(data, id);
    } catch (ExtsysException error1) {
      Assert.fail("Exception" + error1.getMessage());
      return;
    }
    List<VimData> list = null;
    try {
      list = handler.getVimById(id);
    } catch (ExtsysException error) {
      Assert.fail("Exception" + error.getMessage());
      return;
    }
    assertTrue(list.size() > 0 && list.get(0).getName().equals("vim_new"));
  }

}
