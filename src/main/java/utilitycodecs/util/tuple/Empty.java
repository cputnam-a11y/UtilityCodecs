package utilitycodecs.util.tuple;

import java.util.Optional;

public final class Empty implements Tuple {
    public static final Empty INSTANCE = new Empty();

    private Empty() {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Optional<?> get(int index) {
        return Optional.empty();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Empty;
    }
}
