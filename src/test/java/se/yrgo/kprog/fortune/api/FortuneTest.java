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
package se.yrgo.kprog.fortune.api;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test of the Fortune class.
 * 
 */
public class FortuneTest {

    /**
     * Test of getFortune method, of class Fortune.
     */
    @Test
    public void testGetFortune() {
        String expResult = "some fortune\nis here";
        Fortune instance = new Fortune(expResult);
        String result = instance.getFortune();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFortune method, of class Fortune.
     */
    @Test
    public void testSetFortune() {
        String fortune = "some kind of fortune";
        Fortune instance = new Fortune("");
        instance.setFortune(fortune);
        assertEquals(fortune, instance.getFortune());
    }

    /**
     * Test of isShort method, of class Fortune.
     */
    @Test
    public void testIsShort() {
        var shortInstance1 = new Fortune(null);
        var shortInstance2 = new Fortune("a".repeat(160));
        var longInstance   = new Fortune("a".repeat(161));
        
        assertTrue(shortInstance1.isShort());
        assertTrue(shortInstance2.isShort());
        assertFalse(longInstance.isShort());
    }

    /**
     * Test of equals method, of class Fortune.
     */
    @Test
    public void testEquals() {
        Fortune instance1 = null;
        Fortune instance2 = new Fortune(null);
        Fortune instance3 = new Fortune("apan");
        Fortune instance4 = new Fortune("apan");
        
        assertFalse(instance2.equals(instance1));
        assertFalse(instance2.equals(instance3));
        assertTrue(instance3.equals(instance3));
        assertTrue(instance3.equals(instance4));
    }
    
    /**
     * Test of hashCode method, of class Fortune.
     */
    @Test
    public void testHashCode() {
        Fortune instance1 = new Fortune("this");
        Fortune instance2 = new Fortune("that");
        assertNotEquals(instance1.hashCode(), instance2.hashCode());
    }
}
