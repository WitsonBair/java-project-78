package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {

    @Test
    void stringSchemaContainsTest() {
        Validator v = new Validator();
        StringSchema sSchema1 = v.string();

        assertTrue(sSchema1.isValid(null));
        sSchema1.contains("123");
        assertFalse(sSchema1.isValid(null));
        sSchema1.required();
        sSchema1.contains(null);
        assertFalse(sSchema1.isValid("str"));
        assertTrue(sSchema1.contains("wh").isValid("what does the fox say"));
    }

    @Test
    void stringSchemaMinLengthTest() {
        Validator v = new Validator();
        StringSchema sSchema2 = v.string();
        sSchema2.required();
        assertFalse(sSchema2.isValid(""));
        assertTrue(sSchema2.minLength(5).isValid("123456"));
        assertFalse(sSchema2.minLength(7).isValid("123456"));
    }

    @Test
    void stringSchemaRequiredTest() {
        Validator v = new Validator();
        StringSchema sSchema3 = v.string();

        assertTrue(sSchema3.isValid(null));
        sSchema3.minLength(5);
        assertFalse(sSchema3.isValid(null));
        assertFalse(sSchema3.required().isValid(""));
    }

}
