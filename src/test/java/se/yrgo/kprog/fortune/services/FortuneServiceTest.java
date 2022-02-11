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
package se.yrgo.kprog.fortune.services;

import java.util.Collections;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.yrgo.kprog.fortune.api.Fortune;

/**
 * Test of FortuneService.
 */
class FortuneServiceTest {
    final Fortune TRAD_FORTUNE = new Fortune("traditional fortune " + "X".repeat(256));
    final Fortune SHOWER_THOUGHT = new Fortune("shower thought");

    /**
     * Test of getRandom method, of class FortuneService.
     */
    @Test
    void testGetRandom() {
        FortuneService instance = new FortuneService();
        Fortune result = instance.getRandom();
        if (!(result.equals(TRAD_FORTUNE) || result.equals(SHOWER_THOUGHT))) {
            fail("Unexcpected random fortune");
        }
    }

    /**
     * Test of getRandomShort method, of class FortuneService.
     */
    @Test
    void testGetRandomShort() {
        FortuneService instance = new FortuneService();
        Fortune expResult = SHOWER_THOUGHT;
        Fortune result = instance.getRandomShort();
        assertEquals(expResult, result);
        assertTrue(result.isShort());
    }

    /**
     * Test of getShowerThought method, of class FortuneService.
     */
    @Test
    void testGetShowerThought() {
        FortuneService instance = new FortuneService();
        Fortune expResult = SHOWER_THOUGHT;
        Fortune result = instance.getShowerThought();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTraditional method, of class FortuneService.
     */
    @Test
    void testGetTraditional() {
        FortuneService instance = new FortuneService();
        Fortune expResult = TRAD_FORTUNE;
        Fortune result = instance.getTraditional();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of query method, of class FortuneService.
     */
    @Test
    void testQuery() {
        FortuneService instance = new FortuneService();
        var expResult = Collections.singletonList(TRAD_FORTUNE);
        var result = instance.query("traditional");
        assertEquals(expResult, result);
    } 
}
