package utilitycodecs.util.codec;

import com.google.common.collect.Streams;
import com.mojang.serialization.*;
import org.apache.commons.lang3.mutable.MutableObject;
import utilitycodecs.util.accessor.AbstractBuilderAccessor;

import java.util.stream.Stream;

public class FallbackMapCodec<A> extends MapCodec<A> {
    private final MapCodec<A> try1;
    private final MapCodec<A> try2;

    public FallbackMapCodec(MapCodec<A> try1, MapCodec<A> try2) {
        this.try1 = try1;
        this.try2 = try2;
    }

    @Override
    public <T> Stream<T> keys(DynamicOps<T> ops) {
        return Streams.concat(
                try1.keys(ops),
                try2.keys(ops)
        );
    }

    @Override
    public <T> DataResult<A> decode(DynamicOps<T> ops, MapLike<T> input) {
        return try1.decode(ops, input).mapOrElse(
                DataResult::success,
                error -> try2.decode(ops, input).mapOrElse(
                        DataResult::success,
                        error2 -> DataResult.error(() -> "Failed to decode")
                )
        );
    }

    @Override
    public <T> RecordBuilder<T> encode(A input, DynamicOps<T> ops, RecordBuilder<T> prefix) {
        return asResult(
                try1.encode(input, ops, ops.mapBuilder())
        ).mapOrElse(
                        DataResult::success,
                        error -> asResult(
                                try2.encode(input, ops, ops.mapBuilder())
                        ).mapOrElse(
                                DataResult::success,
                                error2 -> DataResult.<RecordBuilder<T>>error(
                                        () -> String.format("Failed to encode %s: %s", input, error)
                                )
                        )
                )
                .flatMap(b -> b.build(ops.empty()))
                .flatMap(ops::getMap)
                .ifSuccess(
                        innerMap -> innerMap.entries().forEach(
                                pair -> prefix.add(
                                        pair.getFirst(),
                                        pair.getSecond()
                                )
                        )
                )
                .ifError(
                        prefix::withErrorsFrom
                ).mapOrElse(
                        (v) -> prefix,
                        (e) -> prefix
                );
    }

    private static <V> DataResult<RecordBuilder<V>> asResult(RecordBuilder<V> builder) {
        MutableObject<String> errorHolder = new MutableObject<>();
        if (!(builder instanceof RecordBuilder.AbstractBuilder<V, ?> abstractBuilder))
            throw new IllegalStateException("RecordBuilder is not an instance of RecordBuilder.AbstractBuilder");
        AbstractBuilderAccessor.builder(abstractBuilder)
                .ifError(
                        error -> errorHolder.setValue(error.message())
                );
        return errorHolder.getValue() != null
               ? DataResult.error(errorHolder::getValue)
               : DataResult.success(builder);
    }
}
