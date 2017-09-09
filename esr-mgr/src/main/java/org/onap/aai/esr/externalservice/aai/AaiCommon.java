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
package org.onap.aai.esr.externalservice.aai;

import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.glassfish.jersey.client.ClientResponse;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class AaiCommon {

//  private static String AAI_AUTHENTICATION_USER = "AAI";
//  private static String AAI_AUTHENTICATION_PAASWORD = "AAI";
  private static String RESOURCE_VERSION_PARAM = "resource-version";
  
//  public String getAuthenticationCredentials() {
//    String usernameAndPassword = AAI_AUTHENTICATION_USER + ":"
//        + AAI_AUTHENTICATION_PAASWORD;
//    return "Basic " + java.util.Base64.getEncoder().encodeToString("AAI:AAI".getBytes());
//  }
  
  public String getResourceVersion(ClientResponse response)
      throws ParserConfigurationException, SAXException, IOException {
    String respData = response.readEntity(String.class);

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    InputSource is = new InputSource(new StringReader(respData));
    Document doc = builder.parse(is);

    NodeList nodeList = doc.getDocumentElement().getChildNodes();
    for (int i = 0; i < nodeList.getLength(); i++) {
      Node currentNode = nodeList.item(i);
      if (currentNode.getNodeName().equals(RESOURCE_VERSION_PARAM)) {
        return currentNode.getTextContent();
      }
    }

    return null;
  }
}
