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

import org.codice.testify.objects.*;
import org.codice.testify.testParsers.TestParser;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import java.io.File;
import java.util.Hashtable;

/**
 * The DirectoryMarkerTestParser class is a Testify TestParser service for .marker files
 * .marker files can be used to mark a directory for easy absolute path reference via a property
 * @see org.codice.testify.testParsers.TestParser ;
 */
public class DirectoryMarkerTestParser implements BundleActivator, TestParser {

    @Override
    public ParsedData parseTest(File file) {

        TestifyLogger.debug(this.getClass().getSimpleName(), this.getClass().getSimpleName());

        File marker = file.getParentFile();
        String directoryName = marker.getName();

        TestifyLogger.debug("Found marker" + marker, this.getClass().getSimpleName());
        TestifyLogger.debug("Found directory: " + directoryName, this.getClass().getSimpleName());

        TestProperties properties = (TestProperties)AllObjects.getObject("testProperties");

        TestifyLogger.debug("Adding property: marker." + directoryName, this.getClass().getSimpleName());
        properties.addProperty("marker." + directoryName, marker.getAbsolutePath());

        return null;
    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {

        //Register the TestParser service for extension xml
        Hashtable<String, String> extension = new Hashtable<>();
        extension.put("extension", "marker");
        bundleContext.registerService(TestParser.class.getName(), new DirectoryMarkerTestParser(), extension);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {

    }
}
