/*
 * Copyright 2015 Codice Foundation
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.codice.testify.testparsers;

import org.codice.testify.objects.AllObjects;
import org.codice.testify.objects.TestProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.io.File;

@RunWith(JUnit4.class)
public class DirectoryMarkerParserTest {

    //Set objects
    private final String currentDir = System.getProperty("user.dir");
    private final DirectoryMarkerParser directoryMarkerParser = new DirectoryMarkerParser();

    @Test
    public void testMarkerPresence() {
        File file = new File(currentDir + "/src/test/resources/mockBundle/sub2/.marker");

        TestProperties testProperties = new TestProperties();

        AllObjects.setObject("testProperties", testProperties);

        directoryMarkerParser.parseTest(file);

        String markerPath = testProperties.getFirstValue("marker.sub2");

        System.out.println(markerPath);
        assert(markerPath.equals(currentDir + "/src/test/resources/mockBundle/sub2"));
    }
}
