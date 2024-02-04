package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema{
    private Integer sizeof = null;
    private Map<String, BaseSchema> schemas;

    public MapSchema() {
        super.checkouts.add((m) -> m instanceof Map<?,?> || !required);
    }

    public MapSchema required() {
        super.required = true;
        return this;
    }

    public MapSchema sizeof(Integer size) {
        this.sizeof = size;
        super.checkouts.add((m) -> ((Map<?, ?>) m).size() == sizeof);
        return this;
    }

    public void shape(Map<String, BaseSchema> newSchema) {
        super.checkouts.add((m) -> {
            if (newSchema == null) {
                return false;
            }
            for (Map.Entry<String, BaseSchema> entry : newSchema.entrySet()) {
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
