/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.bmsantos.core.cola.utils;

import static java.io.File.separator;
import static java.net.URLEncoder.encode;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

public final class ColaUtils {

    public static final String RESOURCE_SEPARATOR = "/";
    public static final String CLASS_EXT = ".class";

    private ColaUtils() throws InstantiationException {
        throw new InstantiationException("This class is not for instantiation");
    }

    public static boolean isSet(final String value) {
        return value != null && !value.isEmpty();
    }

    public static boolean isSet(final List<?> value) {
        return value != null && !value.isEmpty();
    }

    public static boolean isSet(final Object value) {
        return value != null;
    }

    public static boolean isSet(final Object[] value) {
        return value != null && value.length > 0;
    }

    public static boolean isClassFile(final String name) {
        return name.endsWith(CLASS_EXT);
    }

    public static String osToBinary(final String path) {
        return path.replace(separator, ".");
    }

    public static String classToBinary(final String path) {
        return osToBinary(path.replace(CLASS_EXT, ""));
    }

    public static String binaryToOS(final String binaryFormat) {
        return binaryFormat.replace(".", separator);
    }

    public static String binaryToOsClass(final String binaryFormat) {
        StringBuilder result = new StringBuilder(binaryToOS(binaryFormat));
        if (!isClassFile(result.toString())) {
            result.append(CLASS_EXT);
        }
        return result.toString();
    }

    public static String binaryToResource(final String binaryFormat) {
        return binaryFormat.replace(".", RESOURCE_SEPARATOR);
    }

    public static String binaryToResourceClass(final String binaryFormat) {
        StringBuilder result = new StringBuilder(binaryToResource(binaryFormat));
        if (!isClassFile(result.toString())) {
            result.append(CLASS_EXT);
        }
        return result.toString();
    }

    public static String resourceClassToResource(final String resourceClass) {
        return resourceClass.replace(CLASS_EXT, "");
    }

    public static boolean binaryFileExists(final String dir, final String clazz) {
        return isSet(dir) && isSet(clazz) && new File(dir + separator + binaryToOsClass(clazz)).exists();
    }

    public static String toOSPath(final String path) {
        return path.replace(RESOURCE_SEPARATOR, separator).replace("\\", separator);
    }

    public static String paramEncoding(final String original) throws UnsupportedEncodingException {
        return encode(original, UTF_8.name()).replaceAll("\\+", "%20");
    }

    public static String join(final String separator, final List<String> list) {
        final Iterator<String> it = list.iterator();
        StringBuilder result = new StringBuilder();
        while (it.hasNext()) {
            result.append(it.next()).append( it.hasNext() ? separator : "" );
        }
        return result.toString();
    }

    public static String joinStrings(final String separator, final String... entries) {
        StringBuilder result = new StringBuilder();
        for (final String entry : entries) {
            if (entry != null && !entry.isEmpty()) {
                result.append(result.toString().isEmpty() ? entry : separator + entry);
            }
        }
        return result.toString();
    }
}
