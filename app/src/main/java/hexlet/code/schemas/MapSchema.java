package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema<T> extends BaseSchema<T> {
    private Integer sizeof = null;
    private Map<String, BaseSchema<T>> schemas;

    public MapSchema() {
        super.checkouts.add((m) -> m instanceof Map<?, ?> || !required);
    }

    public MapSchema<T> required() {
        super.required = true;
        return this;
    }

    public MapSchema<T> sizeof(Integer size) {
        this.sizeof = size;
        super.checkouts.add((m) -> ((Map<?, ?>) m).size() == sizeof);
        return this;
    }

    public void shape(Map<String, BaseSchema<T>> newSchema) {
        super.checkouts.add((m) -> {
            if (newSchema == null) {
                return false;
            }
            for (Map.Entry<String, BaseSchema<T>> entry : newSchema.entrySet()) {
                if (((Map<?, ?>) m).containsKey(entry.getKey())) {
                    if (!entry.getValue().isValid(((Map<?, ?>) m).get(entry.getKey()))) {
                        return false;
                    }
                }
            }
            return true;
        });
    }
}
