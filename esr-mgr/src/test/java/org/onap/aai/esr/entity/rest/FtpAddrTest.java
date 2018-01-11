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
package org.onap.aai.esr.entity.rest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FtpAddrTest {

    @Test
    public void getterAndSetter4ip() {
        final String ip = "127.0.0.1";
        FtpAddr ftpAddr = new FtpAddr();
        ftpAddr.setIp(ip);
        assertEquals(ftpAddr.getIp(), ip);
    }

    @Test
    public void getterAndSetter4port() {
        final String port = "8080";
        FtpAddr ftpAddr = new FtpAddr();
        ftpAddr.setPort(port);
        assertEquals(ftpAddr.getPort(), port);
    }

    @Test
    public void getterAndSetter4user() {
        final String user = "root";
        FtpAddr ftpAddr = new FtpAddr();
        ftpAddr.setUser(user);
        assertEquals(ftpAddr.getUser(), user);
    }

    @Test
    public void getterAndSetter4password() {
        final String password = "dfasfa";
        FtpAddr ftpAddr = new FtpAddr();
        ftpAddr.setPassword(password);
        assertEquals(ftpAddr.getPassword(), password);
    }

    @Test
    public void getterAndSetter4ftptype() {
        final String ftptype = "sftp";
        FtpAddr ftpAddr = new FtpAddr();
        ftpAddr.setFtptype(ftptype);
        assertEquals(ftpAddr.getFtptype(), ftptype);
    }

    @Test
    public void getterAndSetter4remotepath() {
        final String remotepath = "/home/opt/";
        FtpAddr ftpAddr = new FtpAddr();
        ftpAddr.setRemotepath(remotepath);
        assertEquals(ftpAddr.getRemotepath(), remotepath);
    }

    @Test
    public void getterAndSetter4passive() {
        final Boolean passive = true;
        FtpAddr ftpAddr = new FtpAddr();
        ftpAddr.setPassive(passive);
        assertEquals(ftpAddr.getPassive(), passive);
    }
}
