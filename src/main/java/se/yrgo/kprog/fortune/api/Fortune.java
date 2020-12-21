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
package se.yrgo.kprog.fortune.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Objects;

/**
 * Simple data object for a fortune.
 */
public class Fortune implements Serializable {
    private static final long serialVersionUID = 225353312323445L;
    private static final int SHORT_FORTUNE_MAX = 160;
    private String theFortune;

    public Fortune(String theFortune) {
        this.theFortune = theFortune;
    }
    
    public String getFortune() {
        return theFortune;
    }

    public void setFortune(String theFortune) {
        this.theFortune = theFortune;
    }

    @JsonIgnore
    public boolean isShort() {
        return this.theFortune == null || 
                this.theFortune.length() <= SHORT_FORTUNE_MAX;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(theFortune);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fortune other = (Fortune) obj;
        return Objects.equals(this.theFortune, other.theFortune);
    }

    @Override
    public String toString() {
        return "Fortune{" + "fortune=" + theFortune + '}';
    }
}
