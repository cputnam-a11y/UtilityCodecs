import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import org.junit.jupiter.api.Test;
import utilitycodecs.CodecExtensionHelper;

public class CodecTest {
    private static final Codec<String> STRING_CODEC = Codec.STRING;

    private static final Codec<String> EXTENDED_STRING = CodecExtensionHelper.buildExtensionCodec(
            STRING_CODEC,
            (instance, wrappedOriginal) -> instance.group(
                    wrappedOriginal,
                    Codec.INT
                            .fieldOf("length")
                            .forGetter(String::length)
            ).apply(instance, (original, _) -> original)
    );

    private static final Codec<String> EXTRA_EXTENDED_STRING = CodecExtensionHelper.buildExtensionCodec(
            EXTENDED_STRING,
            (instance, wrappedOriginal) -> instance.group(
                    wrappedOriginal,
                    Codec.STRING
                            .fieldOf("extra")
                            .forGetter(_ -> "Extra")
            ).apply(instance, (original, _) -> original)
    );

    @Test
    public void basicTest() {
        var encodeResult = EXTENDED_STRING.encodeStart(JsonOps.INSTANCE, "Hello World");
        var encoded = encodeResult.getOrThrow();
        assert encoded.isJsonObject() : "Expected JsonObject, got " + encoded;
        {
            var obj = encoded.getAsJsonObject();
            assert obj.get("value").getAsString().equals("Hello World") : "Expected 'Hello World', got " + obj.get("value").getAsString();
            assert obj.get("length").getAsInt() == 11 : "Expected 11, got " + obj.get("length").getAsInt();
        }

        var decodeResult = EXTENDED_STRING.decode(JsonOps.INSTANCE, encoded);
        var decoded = decodeResult.getOrThrow().getFirst();
        assert decoded.equals("Hello World") : "Expected 'Hello World', got " + decoded;
    }

    @Test
    public void extraTest() {
        var encodeResult = EXTRA_EXTENDED_STRING.encodeStart(JsonOps.INSTANCE, "Hello World");
        var encoded = encodeResult.getOrThrow();
        assert encoded.isJsonObject() : "Expected JsonObject, got " + encoded;
        System.out.println(encoded);
        {
            var obj = encoded.getAsJsonObject();
            assert obj.get("value").getAsString().equals("Hello World") : "Expected 'Hello World', got " + obj.get("value").getAsString();
            assert obj.get("length").getAsInt() == 11 : "Expected 11, got " + obj.get("length").getAsInt();
            assert obj.get("extra").getAsString().equals("Extra") : "Expected 'Extra', got " + obj.get("extra").getAsString();
        }

        var decodeResult = EXTRA_EXTENDED_STRING.decode(JsonOps.INSTANCE, encoded);
        var decoded = decodeResult.getOrThrow().getFirst();
        assert decoded.equals("Hello World") : "Expected 'Hello World', got " + decoded;
    }
}
