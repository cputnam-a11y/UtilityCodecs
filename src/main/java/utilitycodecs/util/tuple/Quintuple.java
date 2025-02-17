package utilitycodecs.util.tuple;

import java.util.Optional;

public record Quintuple<A, B, C, D, E>(A a, B b, C c, D d, E e) implements Tuple {
    @Override
    public int size() {
        return 5;
    }

    @Override
    public Optional<?> get(int index) {
        return switch (index) {
            case 0 -> Optional.of(a);
            case 1 -> Optional.of(b);
            case 2 -> Optional.of(c);
            case 3 -> Optional.of(d);
            case 4 -> Optional.of(e);
            default -> Optional.empty();
        };
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Tuple tuple
                && tuple.size() == this.size()
                && tuple.get(0).equals(this.get(0))
                && tuple.get(1).equals(this.get(1))
                && tuple.get(2).equals(this.get(2))
                && tuple.get(3).equals(this.get(3))
                && tuple.get(4).equals(this.get(4));
    }
}
