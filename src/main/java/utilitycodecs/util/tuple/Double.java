package utilitycodecs.util.tuple;

import java.util.Optional;

public record Double<A, B>(A a, B b) implements Tuple {
    @Override
    public int size() {
        return 2;
    }

    @Override
    public Optional<?> get(int index) {
        return switch (index) {
            case 0 -> Optional.of(a);
            case 1 -> Optional.of(b);
            default -> Optional.empty();
        };
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Tuple tuple
                && tuple.size() == this.size()
                && tuple.get(0).equals(this.get(0))
                && tuple.get(1).equals(this.get(1));
    }
}
