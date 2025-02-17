package utilitycodecs.util.codec;

import com.mojang.serialization.*;
import org.slf4j.Logger;

import java.util.stream.Stream;
public class LoggerMapCodec<T> extends MapCodec<T> {
    private final MapCodec<T> delgate;
    private final org.slf4j.Logger logger;

    public LoggerMapCodec(MapCodec<T> delgate, Logger logger) {
        this.delgate = delgate;
        this.logger = logger;
    }

    @Override
    public String toString() {
        return "LoggingCodec[" + delgate + "]";
    }

    public static <T> LoggerMapCodec<T> of(MapCodec<T> delegate, Logger logger) {
        return new LoggerMapCodec<>(delegate, logger);
    }

    @Override
    public <T1> Stream<T1> keys(DynamicOps<T1> ops) {
        return delgate.keys(ops);
    }

    @Override
    public <T1> DataResult<T> decode(DynamicOps<T1> ops, MapLike<T1> input) {
        logger.debug("Decoding {{}} with {{}}", input, delgate.getClass());
        var output = delgate.decode(ops, input);
        logger.debug("Decoded {{}} with {{}}, Result: {}", input, delgate.getClass(), output);
        return output;
    }

    @Override
    public <T1> RecordBuilder<T1> encode(T input, DynamicOps<T1> ops, RecordBuilder<T1> prefix) {
        logger.debug("Encoding {{}} with {{}}", input, delgate.getClass());
        var output = delgate.encode(input, ops, prefix);
        logger.debug("Encoded {{}} with {{}}, Result: {}", input, delgate.getClass(), output);
        return output;
    }
}
