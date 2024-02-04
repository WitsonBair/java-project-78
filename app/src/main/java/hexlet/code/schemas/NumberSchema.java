package hexlet.code.schemas;

public class NumberSchema<T> extends BaseSchema<T> {
    private boolean positive;
    private int minRange = Integer.MIN_VALUE;
    private int maxRange = Integer.MAX_VALUE;

    public NumberSchema() {
        super.checkouts.add((n) -> n instanceof Number || !required);
    }

    public NumberSchema<T> required() {
        required = true;
        return this;
    }

    public NumberSchema<T> positive() {
        this.positive = true;
        super.checkouts.add((n) -> {
            return n instanceof Number ? (Integer) n > 0 : !required;
        });
        return this;
    }

    public NumberSchema<T> range(int min, int max) {
        this.minRange = min;
        this.maxRange = max;
        super.checkouts.add((n) -> minRange <= (Integer) n && (Integer) n <=maxRange);
        return this;
    }
}
