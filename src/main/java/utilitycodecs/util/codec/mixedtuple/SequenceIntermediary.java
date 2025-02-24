package utilitycodecs.util.codec.mixedtuple;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class SequenceIntermediary<A, Q, R> implements Applicative<R, Function<List<Q>, A>> {
    private final Applicative<R, A> other;
    private final Codec<List<Q>> codec;
    private final Function<R, List<Q>> getter;
    private final int size;

    public SequenceIntermediary(Applicative<R, A> other, Codec<List<Q>> codec, Function<R, List<Q>> getter, int size) {
        this.other = other;
        this.codec = codec.validate(list -> list.size() == size
                                            ? DataResult.success(list)
                                            : DataResult.error(
                                                    () -> "Expected " + size + " elements, got " + list.size()
                                            )
        );
        this.getter = getter;
        this.size = size;
    }

    public static <Q, R> SequenceIntermediary<R, Q, R> terminalSequence(Codec<List<Q>> codec, Function<R, List<Q>> getter, int size) {
        return new SequenceIntermediary<>(new Terminal<>(), codec, getter, size);
    }

    public static <Q, R, A> SequenceIntermediary<A, Q, R> sequence(Applicative<R, A> other, Codec<List<Q>> codec, Function<R, List<Q>> getter, int size) {
        return new SequenceIntermediary<>(other, codec, getter, size);
    }

    @Override
    public <T> DataResult<R> apply(DynamicOps<T> ops, List<T> inputs, Function<List<Q>, A> applicator) {
        return isOfSizeOrGreater(inputs, size)
                .map(list -> list.subList(0, size))
                .map(List::stream)
                .map(ops::createList)
                .flatMap(list -> codec.decode(ops, list))
                .map(Pair::getFirst)
                .map(applicator)
                .flatMap(a -> other.apply(ops, inputs.subList(size + 1, inputs.size()), a));
    }

    @Override
    public <T> DataResult<List<T>> unapply(DynamicOps<T> ops, R value) {
        return other.unapply(ops, value)
                .flatMap(list -> codec.encodeStart(ops, getter.apply(value))
                        .flatMap(ops::getStream)
                        .map(Stream::toList)
                        .ifSuccess(l -> list.addAll(0, l))
                        .map(l -> list)
                );
    }

    private static <Q> DataResult<List<Q>> isOfSizeOrGreater(List<Q> list, int size) {
        return list.size() < size
               ? DataResult.error(() -> "expected element, got nothing")
               : DataResult.success(list);
    }
}
