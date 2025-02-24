package utilitycodecs.util.collector;

import com.mojang.serialization.DataResult;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Unwraps a {@code List<DataResult<T>>} into a {@code DataResult<List<T>>}
 * @param <T>
 */
public class UnwrappingCollector<T> implements Collector<DataResult<T>, MutableObject<DataResult<List<T>>>, DataResult<List<T>>> {
    @Override
    public Supplier<MutableObject<DataResult<List<T>>>> supplier() {
        return () -> new MutableObject<>(DataResult.success(new ArrayList<>()));
    }

    @Override
    public BiConsumer<MutableObject<DataResult<List<T>>>, DataResult<T>> accumulator() {
        return (o, r) -> o.setValue(
                o.getValue().flatMap(
                        list -> r
                                .ifSuccess(list::add)
                                .map(v -> list)
                )
        );
    }

    @Override
    public BinaryOperator<MutableObject<DataResult<List<T>>>> combiner() {
        return (a, b) -> {
            a.setValue(
                    a.getValue().flatMap(
                            l1 -> b.getValue()
                                    .ifSuccess(l1::addAll)
                                    .map(l2 -> l1)
                    )
            );
            return a;
        };
    }

    @Override
    public Function<MutableObject<DataResult<List<T>>>, DataResult<List<T>>> finisher() {
        return MutableObject::getValue;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of();
    }
}
