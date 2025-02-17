package utilitycodecs;

import com.mojang.datafixers.kinds.App;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import utilitycodecs.util.codec.FallbackMapCodec;
import utilitycodecs.util.accessor.KeyDispatchCodecAccessor;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Helper class for creating extending codec functionality
 * <p>
 * you probably don't want to copy this class, but use it as an example
 * </p>
 */
public class CodecExtensionHelper {
    /**
     * @param original               the codec to extend
     * @param builder                the builder function to create the new codec
     * @param defaultValueApplicator a function applying default values to the vanilla object *NOTE: these values will not be applied to objects not constructed by the codec
     * @param <T>                    type of the object the codec is for
     * @return a wrapper codec that first tries to use the new codec, and if that fails, falls back to the original codec applying default values
     */
    public static <T> Codec<T> buildExtensionCodec(Codec<T> original, BiFunction<RecordCodecBuilder.Instance<T>, RecordCodecBuilder<T, T>, ? extends App<RecordCodecBuilder.Mu<T>, T>> builder, Function<T, T> defaultValueApplicator) {
        // using a lazy codec is not always necessary, but as it can help with issues that stem from classloading order and initialization order, this helper does it.
        return Codec.lazyInitialized(
                () -> Codec.withAlternative(
                        RecordCodecBuilder.create(
                                instance -> builder.apply(
                                        instance,
                                        mapificate(original).forGetter(Function.identity())
                                )
                        ),
                        original.xmap(
                                defaultValueApplicator,
                                defaultValueApplicator
                        )
                )
        );
    }

    /**
     * @param original the codec to extend
     * @param builder  the builder function to create the new codec
     * @param <T>      type of the object the codec is for
     * @return a wrapper codec that first tries to use the new codec, and if that fails, falls back to the original codec
     */
    public static <T> Codec<T> buildExtensionCodec(Codec<T> original, BiFunction<RecordCodecBuilder.Instance<T>, RecordCodecBuilder<T, T>, ? extends App<RecordCodecBuilder.Mu<T>, T>> builder) {
        // no default value applicator
        return buildExtensionCodec(original, builder, Function.identity());
    }

    /**
     * for use with codecs that are not map codecs, or a derivitive. probably ask before using this, as the usage and semantics are a little weird.
     * at the time of writing, COMPRESSED_VALUE_KEY was located in com.mojang.serialization.MapCodec$1.
     * you may use a mixin accessor to acquire this if possible, or other means if not.
     * you may also inline the value, assuming you know it will not change over your version range
     * @param codec a codec of which the result may or may not be a map
     * @return a wrapped codec that will either directly delegate to the input codec if the input is a map, or create a map with the input as the value of the key {@code COMPRESSED_VALUE_KEY}
     * @param <T> the type of the object the codec encodes and decodes
     */
    public static <T> MapCodec<T> mapificate(Codec<T> codec) {
        return new FallbackMapCodec<>(
                MapCodec.assumeMapUnsafe(codec),
                codec.fieldOf(KeyDispatchCodecAccessor.compressedValueKey())
        );
    }
}