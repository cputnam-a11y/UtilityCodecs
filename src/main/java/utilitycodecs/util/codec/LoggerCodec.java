package utilitycodecs.util.codec;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import org.slf4j.Logger;

public record LoggerCodec<T>(Codec<T> delgate, Logger logger) implements Codec<T> {

    @Override
    public <T1> DataResult<Pair<T, T1>> decode(DynamicOps<T1> ops, T1 input) {
        logger.debug("Decoding {{}} with {{}}", input, delgate.getClass());
        var output = delgate.decode(ops, input);
        logger.debug("Decoded {{}} with {{}}, Result: {}", input, delgate.getClass(), output);
        return output;
    }

    @Override
    public <T1> DataResult<T1> encode(T input, DynamicOps<T1> ops, T1 prefix) {
        logger.debug("Encoding {{}} with {{}}", input, delgate.getClass());
        var output = delgate.encode(input, ops, prefix);
        logger.debug("Encoded {{}} with {{}}, Result: {}", input, delgate.getClass(), output);
        return output;
    }

    @Override
    public String toString() {
        return "LoggingCodec[" + delgate + "]";
    }

    public static <T> LoggerCodec<T> of(Codec<T> delegate, Logger logger) {
        return new LoggerCodec<>(delegate, logger);
    }
}
