package utilitycodecs.util.codec.mixedtuple;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Terminal<R> implements Applicative<R, R> {
    @NotNull
    @Contract(value = " -> new", pure = true)
    public static <R> Terminal<R> terminal() {
        return new Terminal<>();
    }

    @Override
    public <T> DataResult<R> apply(DynamicOps<T> ops, List<T> inputs, R applicator) {
        return DataResult.success(applicator);
    }

    @Override
    public <T> DataResult<List<T>> unapply(DynamicOps<T> ops, R value) {
        return DataResult.success(new ArrayList<>());
    }
}
