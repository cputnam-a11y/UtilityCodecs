package utilitycodecs.util.tuple;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public record Single<T>(@NotNull T value) implements Tuple {
    @Override
    public int size() {
        return 1;
    }

    @Override
    public Optional<?> get(int index) {
        return index == 0
               ? Optional.of(value)
               : Optional.empty();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Tuple tuple
                && tuple.size() == this.size()
                && tuple.get(0).equals(this.get(0));
    }
}
