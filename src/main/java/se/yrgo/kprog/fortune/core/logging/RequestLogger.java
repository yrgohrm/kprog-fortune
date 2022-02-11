/*
 * Copyright 2020 Hampus.
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
package se.yrgo.kprog.fortune.core.logging;

import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Very simple logging of requests.
 */
public class RequestLogger {
    static final Logger logger = LoggerFactory.getLogger(RequestLogger.class);
    
    private RequestLogger() {}

    public static void logRequest(Context ctxt, float executionTimeMs) {
        if (logger.isInfoEnabled()) {
            logger.info("{}, {}", ctxt.url(), executionTimeMs);
        }
    }
}
