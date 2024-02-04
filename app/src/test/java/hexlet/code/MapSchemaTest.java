package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MapSchemaTest {

    @Test
    void mapSchemaRequiredTest() {
        Validator v = new Validator();
        MapSchema mSchema = v.map();

        assertTrue(mSchema.isValid(null));
        assertFalse(mSchema.required().isValid(null));
    }

    @Test
    void mapSchemaSizeofTest() {
        Validator v = new Validator();
        MapSchema mSchema = v.map();

        Map<String, String> testMap = new HashMap<>();
        testMap.put("key1", "value1");
        testMap.put("key2", "value2");
        assertTrue(mSchema.isValid(testMap));
        assertFalse(mSchema.sizeof(3).isValid(testMap));
        assertTrue(mSchema.sizeof(2).isValid(testMap));
    }

    @Test
    void mapSchemaNestedMapTest() {

        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertTrue(schema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertTrue(schema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(schema.isValid(human3));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertFalse(schema.isValid(human4));
    }

}