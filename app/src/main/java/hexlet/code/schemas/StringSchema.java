package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    private int minLength;
    private String contains = null;

    public StringSchema() {
        super.checkouts.add((s) -> {
            if (required) {
                return s != null && !s.equals("");
            }
            return true;
        });
    }

    public StringSchema required() {
        super.required = true;
        return this;
    }

    public StringSchema minLength(int strMinLength) {
        this.minLength = strMinLength;
        super.checkouts.add((s) -> {
            if (s instanceof String) {
                return s.toString().length() >= minLength;
            }
            return false;
        });
        return this;
    }

    public StringSchema contains(String includeString) {
        this.contains = includeString;

        super.checkouts.add((s) -> {
            if (!(s instanceof String) || contains == null) {
                return false;
            }
            return s.toString().contains(contains);
        });
        return this;
    }
}
