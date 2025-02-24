package utilitycodecs.util.codec.mixedtuple;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;

import java.util.List;

public interface Applicative<R, A> {
    <T> DataResult<R> apply(DynamicOps<T> ops, List<T> inputs, A applicator);

    <T> DataResult<List<T>> unapply(DynamicOps<T> ops, R value);
}
