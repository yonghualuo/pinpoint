/*
 * Copyright 2014 NAVER Corp.
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

package com.navercorp.pinpoint.web.view;

import java.util.HashSet;

import com.navercorp.pinpoint.web.applicationmap.ServerInstanceListTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.navercorp.pinpoint.common.bo.AgentInfoBo;
import com.navercorp.pinpoint.web.applicationmap.ServerBuilder;
import com.navercorp.pinpoint.web.applicationmap.ServerInstanceList;

/**
 * @author emeroad
 */
public class ServerInstanceListSerializerTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testSerialize() throws Exception {
        AgentInfoBo agentInfoBo = ServerInstanceListTest.createAgentInfo("agentId1", "testHost");

        HashSet<AgentInfoBo> agentInfoBoSet = new HashSet<AgentInfoBo>();
        agentInfoBoSet.add(agentInfoBo);


        ServerBuilder builder = new ServerBuilder(null);
        builder.addAgentInfo(agentInfoBoSet);
        
        ServerInstanceList serverInstanceList = builder.build();
        ObjectWriter objectWriter = mapper.writerWithDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(serverInstanceList);
        logger.debug(json);
    }
}
