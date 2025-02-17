package utilitycodecs.util.codec;

import com.mojang.serialization.*;

import java.util.function.BiFunction;
import java.util.stream.Stream;

public class MappedMapCodec<A, Q> extends MapCodec<A> {
    private final MapCodec<Q> delegate;
    private final BiFunction<A, DynamicOps<?>, Q> encodeMapper;
    private final BiFunction<Q, DynamicOps<?>, A> decodeMapper;

    public MappedMapCodec(MapCodec<Q> delegate, BiFunction<A, DynamicOps<?>, Q> encodeMapper, BiFunction<Q, DynamicOps<?>, A> decodeMapper) {
        this.delegate = delegate;
        this.encodeMapper = encodeMapper;
        this.decodeMapper = decodeMapper;
    }

    @Override
    public <T> Stream<T> keys(DynamicOps<T> ops) {
        return delegate.keys(ops);
    }

    @Override
    public <T> DataResult<A> decode(DynamicOps<T> ops, MapLike<T> input) {
        return delegate.decode(ops, input).map(q -> decodeMapper.apply(q, ops));
    }

    @Override
    public <T> RecordBuilder<T> encode(A input, DynamicOps<T> ops, RecordBuilder<T> prefix) {
        return delegate.encode(encodeMapper.apply(input, ops), ops, prefix);
    }
}
