package utilitycodecs

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.MapCodec.assumeMapUnsafe
import org.slf4j.Logger
import utilitycodecs.util.codec.FallbackMapCodec
import utilitycodecs.util.codec.LoggerCodec
import utilitycodecs.util.accessor.KeyDispatchCodecAccessor.compressedValueKey

fun <V> Codec<V>.log(logger: Logger): Codec<V> =
    LoggerCodec(this, logger)

fun <V> Codec<V>.mapificate(): MapCodec<V> =
    FallbackMapCodec(
        assumeMapUnsafe(this),
        this.fieldOf(compressedValueKey())
    )
