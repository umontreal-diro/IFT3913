/*
 *  Licensed to GraphHopper GmbH under one or more contributor
 *  license agreements. See the NOTICE file distributed with this work for
 *  additional information regarding copyright ownership.
 *
 *  GraphHopper GmbH licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except in
 *  compliance with the License. You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.graphhopper.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.graphhopper.json.Statement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StatementDeserializerTest {

    ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Statement.class, new StatementDeserializer());
        mapper = new ObjectMapper().registerModule(module);
    }

    @Test
    void conditionAsBoolean_expressionAsNumber() throws JsonProcessingException {
        // true instead of "true" or 100 instead of "100" also work because they are parsed to strings
        // We probably need to accept numbers instead of strings for legacy support, but maybe we should reject true/false
        Statement statement = mapper.readValue("{\"if\":true,\"limit_to\":100}", Statement.class);
        assertEquals(Statement.Keyword.IF, statement.keyword());
        assertEquals("true", statement.condition());
        assertEquals(Statement.Op.LIMIT, statement.operation());
        assertEquals("100", statement.value());
    }

    @Test
    void else_null() throws JsonProcessingException {
        // There is no error for `"else": null` currently, even though there is no real reason to support this.
        // The value will actually be null, but the way we use it at the moment this is not a problem.
        Statement statement = mapper.readValue("{\"else\":null,\"limit_to\":\"abc\"}", Statement.class);
        assertEquals(Statement.Keyword.ELSE, statement.keyword());
        assertTrue(statement.condition().isEmpty());
        assertEquals(Statement.Op.LIMIT, statement.operation());
        assertEquals("abc", statement.value());
    }

    @Test
    void block() throws JsonProcessingException {
        Statement statement = mapper.readValue("{\"if\":\"country == DEU\"," +
                "\"do\": [{ \"if\":\"road_class == PRIMARY\", \"limit_to\": \"123\" }] }", Statement.class);
        assertEquals(Statement.Keyword.IF, statement.keyword());
        assertEquals(Statement.Op.DO, statement.operation());
        assertEquals("country == DEU", statement.condition());
        assertTrue(statement.isBlock());
        assertEquals(1, statement.doBlock().size());
        assertEquals("road_class == PRIMARY", statement.doBlock().get(0).condition());
    }

    // Mes ajouts pour tester les erreurs
    @Test
    void error_whenNoKeywordPresent(){
        // ni "if" ni "else" le deserialiseur doit refuser 
        assertThrows(IllegalArgumentException.class, () -> 
            mapper.readValue("{\"limit_to\":\"5\"}", Statement.class));
        }

    @Test
    void error_whenIfWithoutOperation(){
        // "if" existe mais aucune operation fournie
        assertThrows(IllegalArgumentException.class, () -> 
            mapper.readValue("{\"if\":\"road_class == PRIMARY\"}", Statement.class));
        
        }

    @Test
    void error_whenUnknownOperation() {
        // opération inconnue : doit lever une erreur
        assertThrows(IllegalArgumentException.class, () ->
                mapper.readValue("{\"if\":\"country == DEU\",\"something_weird\":\"x\"}", Statement.class));
    }

    @Test
    void doBlock_multipleStatements() throws JsonProcessingException {
        String json = "{"
                + "\"if\":\"country == DEU\","
                + "\"do\":["
                + "  {\"if\":\"road_class == PRIMARY\",\"limit_to\":\"7\"},"
                + "  {\"else\":null,\"limit_to\":\"9\"}"
                + "]"
                + "}";

        Statement outer = mapper.readValue(json, Statement.class);
        assertEquals(Statement.Keyword.IF, outer.keyword());
        assertEquals(Statement.Op.DO, outer.operation());
        assertEquals("country == DEU", outer.condition());
        assertTrue(outer.isBlock());
        assertEquals(2, outer.doBlock().size());

        // 1er sous-statement
        Statement s0 = outer.doBlock().get(0);
        assertEquals(Statement.Keyword.IF, s0.keyword());
        assertEquals("road_class == PRIMARY", s0.condition());
        assertEquals(Statement.Op.LIMIT, s0.operation());
        assertEquals("7", s0.value());

        // 2e sous-statement (ELSE + null accepté)
        Statement s1 = outer.doBlock().get(1);
        assertEquals(Statement.Keyword.ELSE, s1.keyword());
        assertTrue(s1.condition().isEmpty());
        assertEquals(Statement.Op.LIMIT, s1.operation());
        assertEquals("9", s1.value());
}
    @Test
    void doBlock_elseIf_buildsElseIfWithList() throws Exception{
        String json = "{"
                + "\"else_if\":\"x > 0\","            
                + "\"do\":[ {\"if\":\"y < 3\",\"limit_to\":\"5\"} ]"
                + "}";
        Statement st = mapper.readValue(json, Statement.class);

        assertEquals(Statement.Keyword.ELSEIF, st.keyword());
        assertEquals("x > 0", st.condition());
        assertEquals(1, st.doBlock().size());
        Statement inner = st.doBlock().get(0);
        assertEquals(Statement.Keyword.IF, inner.keyword());
        assertEquals("y < 3", inner.condition());
        assertEquals(Statement.Op.LIMIT, inner.operation());
        assertEquals("5", inner.value());

    }
    @Test
    void doBlock_else_null_becomesElseList() throws Exception {
        String json = "{ \"else\":null, \"do\":[ {\"limit_to\":\"7\", \"if\":\"speed < 30\"} ] }";
        Statement st = mapper.readValue(json, Statement.class);

        assertEquals(Statement.Keyword.ELSE, st.keyword());
        assertEquals(1, st.doBlock().size());
}

    @Test
    void else_null_withOperation_buildsElseOpValue() throws Exception {
        String json = "{ \"else\":null, \"multiply_by\":\"3\" }";
        Statement st = mapper.readValue(json, Statement.class);

        assertEquals(Statement.Keyword.ELSE, st.keyword());
        assertEquals(Statement.Op.MULTIPLY, st.operation());
        assertEquals("3", st.value());
}
}
