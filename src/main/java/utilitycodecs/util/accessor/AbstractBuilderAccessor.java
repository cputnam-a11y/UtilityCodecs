package utilitycodecs.util.accessor;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.RecordBuilder;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

public class AbstractBuilderAccessor {
    private static final MethodHandle BUILDER;

    static {
        try {
            Field builderField = RecordBuilder.AbstractBuilder.class.getDeclaredField("builder");
            builderField.setAccessible(true);
            BUILDER = MethodHandles.lookup().unreflectGetter(builderField);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static DataResult<?> builder(RecordBuilder.AbstractBuilder<?, ?> builder) {
        try {
            return (DataResult<?>) BUILDER.invoke(builder);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

}
