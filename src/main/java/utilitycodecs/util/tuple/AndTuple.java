package utilitycodecs.util.tuple;

import java.util.Optional;

public record AndTuple(Tuple t1, Tuple t2) implements Tuple {

    @Override
    public int size() {
        return t1.size() + t2.size();
    }

    @Override
    public Optional<?> get(int index) {
        if (index < t1.size()) {
            return t1.get(index);
        } else {
            return t2.get(index - t1.size());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tuple tuple) || tuple.size() != size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!this.get(i).equals(tuple.get(i))) {
                return false;
            }
        }
        return true;
    }
}
