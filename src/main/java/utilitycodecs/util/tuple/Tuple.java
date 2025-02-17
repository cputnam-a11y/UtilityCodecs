package utilitycodecs.util.tuple;

import java.util.Optional;

@SuppressWarnings("unused")
public interface Tuple {
    int size();

    Optional<?> get(int index);

    default <T> Optional<T> get(int index, Class<T> type) throws ClassCastException {
        return get(index).map(type::cast);
    }

    default Optional<Class<?>> getType(int index) {
        return get(index).map(Object::getClass);
    }

    default Tuple and(Tuple t1, Tuple t2) {
        if (t1 instanceof Empty) {
            return t2;
        }
        if (t2 instanceof Empty) {
            return t1;
        }
        return switch (t1.size() + t2.size()) {
            case 2 -> of(t1.get(0).orElseThrow(), t2.get(0).orElseThrow());
            case 3 -> of(t1.get(0).orElseThrow(), t1.get(1).orElseThrow(), t2.get(0).orElseThrow());
            case 4 ->
                    of(t1.get(0).orElseThrow(), t1.get(1).orElseThrow(), t1.get(2).orElseThrow(), t2.get(0).orElseThrow());
            case 5 ->
                    of(t1.get(0).orElseThrow(), t1.get(1).orElseThrow(), t1.get(2).orElseThrow(), t1.get(3).orElseThrow(), t2.get(0).orElseThrow());
            case 6 ->
                    of(t1.get(0).orElseThrow(), t1.get(1).orElseThrow(), t1.get(2).orElseThrow(), t1.get(3).orElseThrow(), t1.get(4).orElseThrow(), t2.get(0).orElseThrow());
            case 7 ->
                    of(t1.get(0).orElseThrow(), t1.get(1).orElseThrow(), t1.get(2).orElseThrow(), t1.get(3).orElseThrow(), t1.get(4).orElseThrow(), t1.get(5).orElseThrow(), t2.get(0).orElseThrow());
            case 8 ->
                    of(t1.get(0).orElseThrow(), t1.get(1).orElseThrow(), t1.get(2).orElseThrow(), t1.get(3).orElseThrow(), t1.get(4).orElseThrow(), t1.get(5).orElseThrow(), t1.get(6).orElseThrow(), t2.get(0).orElseThrow());
            case 9 ->
                    of(t1.get(0).orElseThrow(), t1.get(1).orElseThrow(), t1.get(2).orElseThrow(), t1.get(3).orElseThrow(), t1.get(4).orElseThrow(), t1.get(5).orElseThrow(), t1.get(6).orElseThrow(), t1.get(7).orElseThrow(), t2.get(0).orElseThrow());
            case 10 ->
                    of(t1.get(0).orElseThrow(), t1.get(1).orElseThrow(), t1.get(2).orElseThrow(), t1.get(3).orElseThrow(), t1.get(4).orElseThrow(), t1.get(5).orElseThrow(), t1.get(6).orElseThrow(), t1.get(7).orElseThrow(), t1.get(8).orElseThrow(), t2.get(0).orElseThrow());
            case 11 ->
                    of(t1.get(0).orElseThrow(), t1.get(1).orElseThrow(), t1.get(2).orElseThrow(), t1.get(3).orElseThrow(), t1.get(4).orElseThrow(), t1.get(5).orElseThrow(), t1.get(6).orElseThrow(), t1.get(7).orElseThrow(), t1.get(8).orElseThrow(), t1.get(9).orElseThrow(), t2.get(0).orElseThrow());
            case 12 ->
                    of(t1.get(0).orElseThrow(), t1.get(1).orElseThrow(), t1.get(2).orElseThrow(), t1.get(3).orElseThrow(), t1.get(4).orElseThrow(), t1.get(5).orElseThrow(), t1.get(6).orElseThrow(), t1.get(7).orElseThrow(), t1.get(8).orElseThrow(), t1.get(9).orElseThrow(), t1.get(10).orElseThrow(), t2.get(0).orElseThrow());
            case 13 ->
                    of(t1.get(0).orElseThrow(), t1.get(1).orElseThrow(), t1.get(2).orElseThrow(), t1.get(3).orElseThrow(), t1.get(4).orElseThrow(), t1.get(5).orElseThrow(), t1.get(6).orElseThrow(), t1.get(7).orElseThrow(), t1.get(8).orElseThrow(), t1.get(9).orElseThrow(), t1.get(10).orElseThrow(), t1.get(11).orElseThrow(), t2.get(0).orElseThrow());
            case 14 ->
                    of(t1.get(0).orElseThrow(), t1.get(1).orElseThrow(), t1.get(2).orElseThrow(), t1.get(3).orElseThrow(), t1.get(4).orElseThrow(), t1.get(5).orElseThrow(), t1.get(6).orElseThrow(), t1.get(7).orElseThrow(), t1.get(8).orElseThrow(), t1.get(9).orElseThrow(), t1.get(10).orElseThrow(), t1.get(11).orElseThrow(), t1.get(12).orElseThrow(), t2.get(0).orElseThrow());
            case 15 ->
                    of(t1.get(0).orElseThrow(), t1.get(1).orElseThrow(), t1.get(2).orElseThrow(), t1.get(3).orElseThrow(), t1.get(4).orElseThrow(), t1.get(5).orElseThrow(), t1.get(6).orElseThrow(), t1.get(7).orElseThrow(), t1.get(8).orElseThrow(), t1.get(9).orElseThrow(), t1.get(10).orElseThrow(), t1.get(11).orElseThrow(), t1.get(12).orElseThrow(), t1.get(13).orElseThrow(), t2.get(0).orElseThrow());
            case 16 ->
                    of(t1.get(0).orElseThrow(), t1.get(1).orElseThrow(), t1.get(2).orElseThrow(), t1.get(3).orElseThrow(), t1.get(4).orElseThrow(), t1.get(5).orElseThrow(), t1.get(6).orElseThrow(), t1.get(7).orElseThrow(), t1.get(8).orElseThrow(), t1.get(9).orElseThrow(), t1.get(10).orElseThrow(), t1.get(11).orElseThrow(), t1.get(12).orElseThrow(), t1.get(13).orElseThrow(), t1.get(14).orElseThrow(), t2.get(0).orElseThrow());
            default -> new AndTuple(t1, t2);
        };
    }

    static Tuple of() {
        return Empty.INSTANCE;
    }

    static <A> Tuple of(A a) {
        return new Single<>(a);
    }

    static <A, B> Tuple of(A a, B b) {
        return new Double<>(a, b);
    }

    static <A, B, C> Tuple of(A a, B b, C c) {
        return new Triple<>(a, b, c);
    }

    static <A, B, C, D> Tuple of(A a, B b, C c, D d) {
        return new Quadruple<>(a, b, c, d);
    }

    static <A, B, C, D, E> Tuple of(A a, B b, C c, D d, E e) {
        return new Quintuple<>(a, b, c, d, e);
    }

    static <A, B, C, D, E, F> Tuple of(A a, B b, C c, D d, E e, F f) {
        return new Sextuple<>(a, b, c, d, e, f);
    }

    static <A, B, C, D, E, F, G> Tuple of(A a, B b, C c, D d, E e, F f, G g) {
        return new Septuple<>(a, b, c, d, e, f, g);
    }

    static <A, B, C, D, E, F, G, H> Tuple of(A a, B b, C c, D d, E e, F f, G g, H h) {
        return new Octuple<>(a, b, c, d, e, f, g, h);
    }

    static <A, B, C, D, E, F, G, H, I> Tuple of(A a, B b, C c, D d, E e, F f, G g, H h, I i) {
        return new Nonuple<>(a, b, c, d, e, f, g, h, i);
    }

    static <A, B, C, D, E, F, G, H, I, J> Tuple of(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j) {
        return new Decuple<>(a, b, c, d, e, f, g, h, i, j);
    }

    static <A, B, C, D, E, F, G, H, I, J, K> Tuple of(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j, K k) {
        return new Undecuple<>(a, b, c, d, e, f, g, h, i, j, k);
    }

    static <A, B, C, D, E, F, G, H, I, J, K, L> Tuple of(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j, K k, L l) {
        return new Duodecuple<>(a, b, c, d, e, f, g, h, i, j, k, l);
    }

    static <A, B, C, D, E, F, G, H, I, J, K, L, M> Tuple of(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j, K k, L l, M m) {
        return new Tredecuple<>(a, b, c, d, e, f, g, h, i, j, k, l, m);
    }

    static <A, B, C, D, E, F, G, H, I, J, K, L, M, N> Tuple of(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j, K k, L l, M m, N n) {
        return new Quattuordecuple<>(a, b, c, d, e, f, g, h, i, j, k, l, m, n);
    }

    static <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O> Tuple of(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j, K k, L l, M m, N n, O o) {
        return new Quindecuple<>(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o);
    }

    static <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P> Tuple of(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j, K k, L l, M m, N n, O o, P p) {
        return new Sexdecuple<>(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p);
    }

    static Tuple of(Object... args) {
        return switch (args.length) {
            case 0 -> of();
            case 1 -> of(args[0]);
            case 2 -> of(args[0], args[1]);
            case 3 -> of(args[0], args[1], args[2]);
            case 4 -> of(args[0], args[1], args[2], args[3]);
            case 5 -> of(args[0], args[1], args[2], args[3], args[4]);
            case 6 -> of(args[0], args[1], args[2], args[3], args[4], args[5]);
            case 7 -> of(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
            case 8 -> of(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
            case 9 -> of(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8]);
            case 10 -> of(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9]);
            case 11 ->
                    of(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9], args[10]);
            case 12 ->
                    of(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9], args[10], args[11]);
            case 13 ->
                    of(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9], args[10], args[11], args[12]);
            case 14 ->
                    of(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9], args[10], args[11], args[12], args[13]);
            case 15 ->
                    of(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9], args[10], args[11], args[12], args[13], args[14]);
            case 16 ->
                    of(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9], args[10], args[11], args[12], args[13], args[14], args[15]);
            default -> new VarTuple(args);
        };
    }
}
