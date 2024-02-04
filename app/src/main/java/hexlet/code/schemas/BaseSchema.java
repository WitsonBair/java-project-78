package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema<T> {
    protected boolean required;
    protected List<Predicate<Object>> checkouts = new ArrayList<>();

    public final boolean isValid(Object t) {
        boolean result = true;
        for (Predicate<Object> checkout : checkouts) {
            if (!checkout.test(t)) {
                result = false;
                break;
            }
        }
        return result;
    }
}
