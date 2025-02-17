package utilitycodecs.util.tuple;

import java.util.Optional;

public class VarTuple implements Tuple {
    private final Object[] values;

    public VarTuple(Object... values) {
        this.values = values;
    }

    @Override
    public int size() {
        return values.length;
    }

    @Override
    public Optional<?> get(int index) {
        return index >= 0 && index < values.length
               ? Optional.of(values[index])
               : Optional.empty();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tuple tuple) || tuple.size() != this.size())
            return false;
        for (int i = 0; i < values.length; i++) {
            if (this.get(i).equals(tuple.get(i)))
                return false;
        }
        return true;
    }
}
