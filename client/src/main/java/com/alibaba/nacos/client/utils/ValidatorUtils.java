/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.client.utils;

import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.env.NacosClientProperties;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * All parameter validation tools.
 *
 * @author <a href="mailto:liaochuntao@live.com">liaochuntao</a>
 */
public final class ValidatorUtils {
    
    private static final String HTTP_PROTOCOL = "http";
    
    private static final String HTTPS_PROTOCOL = "https";
    
    private static final Pattern CONTEXT_PATH_MATCH = Pattern.compile("(/)\\1+");
    
    public static void checkInitParam(NacosClientProperties properties) throws NacosException {
        checkContextPath(properties.getProperty(PropertyKeyConst.CONTEXT_PATH));
    }
    
    /**
     * Check context path.
     *
     * @param contextPath context path
     */
    public static void checkContextPath(String contextPath) {
        if (contextPath == null) {
            return;
        }
        Matcher matcher = CONTEXT_PATH_MATCH.matcher(contextPath);
        if (matcher.find()) {
            throw new IllegalArgumentException("Illegal url path expression");
        }
    }
    
    /**
     * Check whether the http/https url is valid.
     *
     * @param urlString url string
     * @return null if not valid, otherwise return the url string
     */
    public static String checkValidUrl(String urlString) {
        if (urlString == null) {
            return null;
        }
        URI url;
        try {
            url = new URI(urlString);
        } catch (URISyntaxException e) {
            return null;
        }
        if (url.getHost() == null) {
            return null;
        }
        if (url.getScheme().equalsIgnoreCase(HTTP_PROTOCOL) || url.getScheme().equalsIgnoreCase(HTTPS_PROTOCOL)) {
            return url.toString();
        }
        return null;
    }
    
}
