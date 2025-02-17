package utilitycodecs.util.accessor;

import com.mojang.serialization.codecs.KeyDispatchCodec;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

public class KeyDispatchCodecAccessor {
    private static final MethodHandle COMPRESSED_VALUE_KEY;

    static {
        var lookup = MethodHandles.lookup();
        try {
            Field compressedValueKeyField = KeyDispatchCodec.class.getDeclaredField("COMPRESSED_VALUE_KEY");
            compressedValueKeyField.setAccessible(true);
            COMPRESSED_VALUE_KEY = lookup.unreflectGetter(compressedValueKeyField);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static String compressedValueKey() {
        try {
            return (String) COMPRESSED_VALUE_KEY.invoke();
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
}
