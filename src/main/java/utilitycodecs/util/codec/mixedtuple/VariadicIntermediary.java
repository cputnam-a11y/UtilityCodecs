package utilitycodecs.util.codec.mixedtuple;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class VariadicIntermediary<Q, R> implements Applicative<R, Function<List<Q>, R>> {
    private final Applicative<R, R> other;
    private final Codec<List<Q>> codec;
    private final Function<R, List<Q>> getter;

    public VariadicIntermediary(Terminal<R> other, Codec<List<Q>> codec, Function<R, List<Q>> getter) {
        this.other = other;
        this.codec = codec;
        this.getter = getter;
    }

    public static <Q, R> VariadicIntermediary<Q, R> terminalVariadic(Codec<List<Q>> codec, Function<R, List<Q>> getter) {
        return new VariadicIntermediary<>(new Terminal<>(), codec, getter);
    }

    public static <Q, R> VariadicIntermediary<Q, R> variadic(Terminal<R> other, Codec<List<Q>> codec, Function<R, List<Q>> getter) {
        return new VariadicIntermediary<>(other, codec, getter);
    }

    public <T> DataResult<R> apply(DynamicOps<T> ops, List<T> inputs, Function<List<Q>, R> function) {
        return DataResult.success(inputs)
                .map(List::stream)
                .map(ops::createList)
                .flatMap(list -> codec.decode(ops, list))
                .map(Pair::getFirst)
                .map(function)
                .flatMap(a -> other.apply(ops, new ArrayList<>(), a));
    }

    public <T> DataResult<List<T>> unapply(DynamicOps<T> ops, R value) {
        return other.unapply(ops, value)
                .flatMap(list -> codec.encodeStart(ops, getter.apply(value))
                        .flatMap(ops::getStream)
                        .map(Stream::toList)
                        .ifSuccess(l -> list.addAll(0, l))
                        .map(l -> list)
                );
    }
}
