/*
 * Copyright 2020 Hampus Ram <hampus.ram@educ.goteborg.se>.
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
package se.yrgo.kprog.fortune.handlers;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import org.eclipse.jetty.util.StringUtil;
import se.yrgo.kprog.fortune.services.FortuneService;

/**
 * Web service handler for fortune API.
 */
public class FortuneHandler {
    private final FortuneService fortuneService;

    public FortuneHandler(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }
    
    public void get(Context ctxt) {
        ctxt.json(fortuneService.getRandom());
    }
    
    public void getShort(Context ctxt) {
        ctxt.json(fortuneService.getRandomShort());
    }
    
    public void getShowerThought(Context ctxt) {
        ctxt.json(fortuneService.getShowerThought());
    }
    
    public void getTraditional(Context ctxt) {
        ctxt.json(fortuneService.getTraditional());
    }
    
    public void query(Context ctxt) {
        String query = ctxt.queryParam("q");
        if (StringUtil.isBlank(query) || query.length() < 3) {
            throw new BadRequestResponse("Query must be at least 3 characters.");
        }
        var matches = fortuneService.query(query);
        ctxt.json(matches);
    }
}
