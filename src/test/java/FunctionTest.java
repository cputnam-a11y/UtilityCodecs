import org.junit.jupiter.api.Test;
import utilitycodecs.util.function.BiFunctionExtensionKt;
import utilitycodecs.util.function.FunctionExtensionKt;

import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionTest {
    @Test
    public void test1() {
        Function<Integer, Object> function = (Integer i) -> {
            if (i == 0) return i;
            else return 1;
        };
        BiFunction<Object, Integer, Object> drop1 = FunctionExtensionKt.dropFirst(function);
        assert BiFunctionExtensionKt.insertFirst(drop1, null)
                .apply(0)
                .equals(0);

    }
}
