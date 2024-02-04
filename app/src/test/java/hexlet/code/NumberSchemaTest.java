package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {

    @Test
    void numberSchemaPositiveTest() {
        Validator v = new Validator();
        NumberSchema nSchema = v.number();

        assertTrue(nSchema.isValid(-10));
        assertTrue(nSchema.isValid(10));
        assertTrue(nSchema.positive().isValid(null));
        assertFalse(nSchema.positive().isValid(-5));
    }

    @Test
    void numberSchemaTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        // Пока не вызван метод required(), null считается валидным
        assertTrue(schema.isValid(null));

        assertFalse(schema.required().isValid(null));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid("5"));
        assertTrue(schema.isValid(10));
    }

    @Test
    void numberSchemaRangeTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertTrue(schema.range(5, 10).isValid(5));

        assertTrue(schema.isValid(9));
        assertTrue(schema.isValid(10));
    }

}
