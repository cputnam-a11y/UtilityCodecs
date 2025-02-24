package utilitycodecs.util.codec.mixedtuple;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;

import java.util.ArrayList;

public class Primary<R, A> implements Codec<R> {
    private final Applicative<R, A> other;
    private final A builder;

    public Primary(Applicative<R, A> other, A builder) {
        this.other = other;
        this.builder = builder;
    }

    public static <R, A> Primary<R, A> primary(Applicative<R, A> other, A builder) {
        return new Primary<>(other, builder);
    }

    @Override
    public <T> DataResult<Pair<R, T>> decode(DynamicOps<T> ops, T input) {
        return DataResult.success(new ArrayList<T>())
                .flatMap(
                        list -> ops.getList(input)
                                .map(consumerConsumer -> {
                                    consumerConsumer.accept(list::add);
                                    return list;
                                })
                ).flatMap(list -> other.apply(
                                        ops,
                                        list,
                                        builder
                                )
                                .map(
                                        r -> Pair.of(r, input)
                                )
                );
    }

    @Override
    public <T> DataResult<T> encode(R input, DynamicOps<T> ops, T prefix) {
        return DataResult.success(ops.listBuilder()).flatMap(builder ->
                other.unapply(ops, input).flatMap(
                        list -> {
                            list.forEach(builder::add);
                            return builder.build(prefix);
                        })
        );
    }
}
