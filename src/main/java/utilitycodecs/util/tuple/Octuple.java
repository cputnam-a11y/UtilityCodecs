package utilitycodecs.util.tuple;

import java.util.Optional;

public record Octuple<A, B, C, D, E, F, G, H>(A a, B b, C c, D d, E e, F f, G g, H h) implements Tuple {
    @Override
    public int size() {
        return 8;
    }

    @Override
    public Optional<?> get(int index) {
        return switch (index) {
            case 0 -> Optional.of(a);
            case 1 -> Optional.of(b);
            case 2 -> Optional.of(c);
            case 3 -> Optional.of(d);
            case 4 -> Optional.of(e);
            case 5 -> Optional.of(f);
            case 6 -> Optional.of(g);
            case 7 -> Optional.of(h);
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
                && tuple.get(4).equals(this.get(4))
                && tuple.get(5).equals(this.get(5))
                && tuple.get(6).equals(this.get(6))
                && tuple.get(7).equals(this.get(7));
    }
}
