/**
 * Copyright 2018 ZTE Corporation.
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
package org.onap.aai.esr.entity.aai;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class RelationshipTest {
    @Test
    public void getterAndSetter4relatedTo() {
        final String relatedTo = "relationship-key";
        Relationship relationship = new Relationship();
        relationship.setRelatedTo(relatedTo);
        assertEquals(relationship.getRelatedTo(), relatedTo);
    }
    
    @Test
    public void getterAndSetter4relatedLink() {
        final String relatedLink = "123";
        Relationship relationship = new Relationship();
        relationship.setRelatedLink(relatedLink);
        assertEquals(relationship.getRelatedLink(), relatedLink);
    }
    
    @Test
    public void getterAndSetter4relationshipData() {
        Relationship relationship = new Relationship();
        List<RelationshipData> relationshipDatas = new ArrayList<>();
        RelationshipData relationshipData = new RelationshipData();
        relationshipData.setRelationshipKey("relationship-key");
        relationshipData.setRelationshipValue("123");
        relationshipDatas.add(relationshipData);
        relationship.setRelationshipData(relationshipDatas);
        assertEquals(relationship.getRelationshipData(), relationshipDatas);
    }
}
