/*
 * Copyright (c) 2013, Danilo Reinert <daniloreinert@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the Lesser GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * Lesser GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.reinert.jjschema;

import java.io.IOException;
import java.io.InputStream;
import junit.framework.TestCase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.reinert.jjschema.SchemaFactory;
import com.github.reinert.jjschema.SchemaProperty;
import com.github.reinert.jjschema.exception.UnavailableVersion;

/**
 *
 * @author reinert
 */
public class SimpleExampleTest extends TestCase {

	static ObjectMapper MAPPER = new ObjectMapper();
	
    public SimpleExampleTest(String testName) {
        super(testName);
    }

    /**
     * Test the scheme generate following a scheme source, avaliable at
     * http://json-schema.org/examples.html the output should match the example.
     * @throws IOException 
     * @throws JsonProcessingException 
     */
    public void testGenerateSchema() throws UnavailableVersion, JsonProcessingException, IOException {
        
    	final InputStream in = SimpleExampleTest.class.getResourceAsStream("/res/simple_example.json");
    	if (in == null)
            throw new IOException("resource not found");
    	JsonNode fromResource = MAPPER.readTree(in);
    	JsonNode fromJavaType = SchemaFactory.v4SchemaFrom(SimpleExample.class);
    	
    	assertEquals(fromResource, fromJavaType);
    }
    
    @SchemaProperty(title = "Example Schema")
    static class SimpleExample {
    	@SchemaProperty(required = true)
        private String firstName;
        @SchemaProperty(required = true)
        private String lastName;
        @SchemaProperty(description="Age in years", minimum=0)
        private int age;
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
    }
}
