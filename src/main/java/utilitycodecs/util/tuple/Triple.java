package utilitycodecs.util.tuple;

import java.util.Optional;

public record Triple<A, B, C>(A a, B b, C c) implements Tuple {
    @Override
    public int size() {
        return 3;
    }

    @Override
    public Optional<?> get(int index) {
        return switch (index) {
            case 0 -> Optional.of(a);
            case 1 -> Optional.of(b);
            case 2 -> Optional.of(c);
            default -> Optional.empty();
        };
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Tuple tuple
                && tuple.size() == this.size()
                && tuple.get(0).equals(this.get(0))
                && tuple.get(1).equals(this.get(1))
                && tuple.get(2).equals(this.get(2));
    }
}
