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
package se.yrgo.kprog.fortune.handlers;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import se.yrgo.kprog.fortune.api.Fortune;
import se.yrgo.kprog.fortune.services.FortuneService;

/**
 * Test of FortuneHandler class.
 * 
 */
public class FortuneHandlerTest {
 
    /**
     * Test of get method, of class FortuneHandler.
     * @throws java.lang.Exception
     */
    @Test
    public void testGet() throws Exception {
        Context ctxt = mock(Context.class);
        FortuneService service = mock(FortuneService.class);
        FortuneHandler instance = new FortuneHandler(service);
        Fortune f = new Fortune("fortune");

        when(service.getRandom()).thenReturn(f);

        instance.get(ctxt);
        
        verify(service, times(1)).getRandom();
        verify(ctxt, times(1)).json(f);
    }

    /**
     * Test of getShort method, of class FortuneHandler.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetShort() throws Exception {
        Context ctxt = mock(Context.class);
        FortuneService service = mock(FortuneService.class);
        FortuneHandler instance = new FortuneHandler(service);
        Fortune f = new Fortune("fortune");

        when(service.getRandomShort()).thenReturn(f);
        
        instance.getShort(ctxt);
        
        verify(service, times(1)).getRandomShort();
        verify(ctxt, times(1)).json(f);
    }

    /**
     * Test of getShowerThought method, of class FortuneHandler.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetShowerThought() throws Exception {
        Context ctxt = mock(Context.class);
        FortuneService service = mock(FortuneService.class);
        FortuneHandler instance = new FortuneHandler(service);
        Fortune f = new Fortune("fortune");
        
        when(service.getShowerThought()).thenReturn(f);
        
        instance.getShowerThought(ctxt);
        
        verify(service, times(1)).getShowerThought();
        verify(ctxt, times(1)).json(f);
    }

    /**
     * Test of getTraditional method, of class FortuneHandler.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetTraditional() throws Exception {
        Context ctxt = mock(Context.class);
        FortuneService service = mock(FortuneService.class);
        FortuneHandler instance = new FortuneHandler(service);
        Fortune f = new Fortune("fortune");
        
        when(service.getTraditional()).thenReturn(f);
        
        instance.getTraditional(ctxt);
        
        verify(service, times(1)).getTraditional();
        verify(ctxt, times(1)).json(f);
    }
    
    /**
     * Test of query method, of class FortuneHandler.
     * @throws java.lang.Exception
     */
    @Test
    public void testQueryGood() throws Exception {
        Context ctxt = mock(Context.class);
        FortuneService service = mock(FortuneService.class);
        FortuneHandler instance = new FortuneHandler(service);
        String queryStr = "fort";
        Fortune f = new Fortune("fortune");
        var fList = Collections.singletonList(f);
        
        when(ctxt.queryParam("q")).thenReturn(queryStr);
        when(service.query(queryStr)).thenReturn(fList);
        
        instance.query(ctxt);
        
        verify(service, times(1)).query(queryStr);
        verify(ctxt, times(1)).json(fList);
    }
    
    /**
     * Test of query method, of class FortuneHandler.
     * @throws java.lang.Exception
     */
    @Test
    public void testQueryBad() throws Exception {
        Context ctxt = mock(Context.class);
        FortuneService service = mock(FortuneService.class);
        FortuneHandler instance = new FortuneHandler(service);

        when(ctxt.queryParam("q")).thenReturn("f");
        
        assertThrows(BadRequestResponse.class,
                     () -> instance.query(ctxt));
        
        when(ctxt.queryParam("q")).thenReturn(null);
        
        assertThrows(BadRequestResponse.class,
                     () -> instance.query(ctxt));
    }
}
