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

public class AlarmAddrTest {

    @Test
    public void getterAndSetter4ip() {
        final String ip = "127.0.0.1";
        AlarmAddr alarmAddr = new AlarmAddr();
        alarmAddr.setIp(ip);
        assertEquals(alarmAddr.getIp(), ip);
    }

    @Test
    public void getterAndSetter4port() {
        final String port = "8080";
        AlarmAddr alarmAddr = new AlarmAddr();
        alarmAddr.setPort(port);
        assertEquals(alarmAddr.getPort(), port);
    }

    @Test
    public void getterAndSetter4user() {
        final String user = "root";
        AlarmAddr alarmAddr = new AlarmAddr();
        alarmAddr.setUser(user);
        assertEquals(alarmAddr.getUser(), user);
    }

    @Test
    public void getterAndSetter4password() {
        final String password = "dfasfa";
        AlarmAddr alarmAddr = new AlarmAddr();
        alarmAddr.setPassword(password);
        assertEquals(alarmAddr.getPassword(), password);
    }
}
