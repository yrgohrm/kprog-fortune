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
package se.yrgo.kprog.fortune.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import se.yrgo.kprog.fortune.api.Fortune;

/**
 * Service for retrieving fortunes.
 *
 */
public class FortuneService {
    private final List<Fortune> showerthoughts = Collections.unmodifiableList(readFortuneResource("/showerthoughts.txt"));
    private final List<Fortune> fortunes = Collections.unmodifiableList(readFortuneResource("/fortunes.txt"));
    
    // we use regular random in this instance since it is easy, but it could
    // have a performance impact if this service was used heavily from 
    // multiple threads
    private final Random random = new Random();
    
    /**
     * Get any random fortune.
     * 
     * @return a random fortune.
     */
    public Fortune getRandom() {
        if (random.nextInt(2) == 0) {
            return getRand(showerthoughts);
        }
        else {
            return getRand(fortunes);
        }
    }
    
    /**
     * Get any random short fortune.
     * 
     * @return a random short fortune.
     */
    public Fortune getRandomShort() {
        // this is a simplistic implementation and can take a while
        // if the number of long fortunes outweighs the short by a lot
        Fortune fortune;
        do {
            fortune = getRandom();
        } while (!fortune.isShort());
        
        return fortune;
    }
    
    /**
     * Get a shower thought fortune.
     * 
     * @return a random shower thought.
     */
    public Fortune getShowerThought() {
        return getRand(showerthoughts);
    }
    
    /**
     * Get a traditional fortune.
     * 
     * @return a random traditional fortune.
     */
    public Fortune getTraditional() {
        return getRand(fortunes);
    }
    
    /**
     * Get all fortunes that contain the query phrase.
     * 
     * @param query the query
     * @return list of matching fortunes
     */
    public List<Fortune> query(String query) {
        var matching = Stream.concat(fortunes.stream(), showerthoughts.stream())
                .filter((f) -> f.getFortune().contains(query))
                .collect(Collectors.toList());
        return matching;
    }
    
    /**
     * Returns a random element from the given list.
     * @param list the list
     * @return a random object from the list
     */
    private <T> T getRand(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }
    
    /**
     * Read the given fortune resource into a list of fortunes.
     * 
     * @param resource the fortune file to be read.
     * @return a list of fortunes
     */
    private static List<Fortune> readFortuneResource(final String resource) {
        var fortuneList = new ArrayList<Fortune>();
        try (var resStream = FortuneService.class.getResourceAsStream(resource)) {
            if (resStream == null) {
                throw new IOException("resource '" + resource + "' not found");
            }
            var reader = new BufferedReader(
                    new InputStreamReader(resStream, StandardCharsets.UTF_8));
            var fortune = readFortune(reader);
            while (StringUtils.isNotBlank(fortune)) {
                fortuneList.add(new Fortune(fortune));
                fortune = readFortune(reader);
            }
        } catch (IOException ex) {
            throw new IllegalArgumentException("cannot read from " + resource, ex);
        }
        
        return fortuneList;
    }
    
    /**
     * Read a single fortune from the given reader.
     * 
     * @param reader the reader to read the fortune from
     * @return a fortune (possibly the empty string)
     * @throws IOException 
     */
    private static String readFortune(BufferedReader reader) throws IOException {
        var fortune = new StringBuilder();
        var line = reader.readLine();
        while (line != null && !line.startsWith("%")) {
            fortune.append(line);
            fortune.append('\n');
            line = reader.readLine();
        }
        return fortune.toString().trim();
    }
}
