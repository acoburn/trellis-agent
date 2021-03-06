/*
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
package org.trellisldp.agent;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.trellisldp.api.AgentService;

/**
 * @author acoburn
 */
@RunWith(JUnitPlatform.class)
public class SimpleAgentTest {

    @Test
    public void testAgent() {
        final AgentService service = new SimpleAgent();

        assertEquals("user:acoburn", service.asAgent("user:acoburn").getIRIString());
        assertEquals("user:foo/bar", service.asAgent("user:foo/bar").getIRIString());
    }
}
