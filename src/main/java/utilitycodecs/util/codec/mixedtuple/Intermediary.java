package utilitycodecs.util.codec.mixedtuple;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;

import java.util.List;
import java.util.function.Function;

public class Intermediary<A, Q, R> implements Applicative<R, Function<Q, A>> {
    private final Applicative<R, A> other;
    private final Codec<Q> codec;
    private final Function<R, Q> getter;

    public Intermediary(Applicative<R, A> other, Codec<Q> codec, Function<R, Q> getter) {
        this.other = other;
        this.codec = codec;
        this.getter = getter;
    }

    public static <Q, R> Intermediary<R, Q, R> terminalIntermediary(Codec<Q> codec, Function<R, Q> getter) {
        return new Intermediary<>(new Terminal<>(), codec, getter);
    }

    public static <A, Q, R> Intermediary<A, Q, R> intermediary(Applicative<R, A> other, Codec<Q> codec, Function<R, Q> getter) {
        return new Intermediary<>(other, codec, getter);
    }

    public <T> DataResult<R> apply(DynamicOps<T> ops, List<T> inputs, Function<Q, A> function) {
        return isNonEmpty(inputs).flatMap(
                list -> codec.decode(ops, list.getFirst())
                        .map(Pair::getFirst)
                        .map(function)
                        .flatMap(
                                a -> other.apply(
                                        ops,
                                        list.subList(1, list.size()),
                                        a
                                )
                        )
        );
    }

    public <T> DataResult<List<T>> unapply(DynamicOps<T> ops, R value) {
        return other.unapply(ops, value).flatMap(list ->
                codec.encodeStart(ops, getter.apply(value))
                        .map(encoded -> {
                            list.addFirst(encoded);
                            return list;
                        })
        );
    }

    private static <Q> DataResult<List<Q>> isNonEmpty(List<Q> list) {
        return list.isEmpty()
               ? DataResult.error(() -> "expected element, got nothing")
               : DataResult.success(list);
    }
}
