@file:Suppress("unused")
package utilitycodecs

import com.mojang.serialization.DynamicOps
import com.mojang.serialization.MapCodec
import org.slf4j.Logger
import utilitycodecs.util.codec.FallbackMapCodec
import utilitycodecs.util.codec.LoggerMapCodec
import utilitycodecs.util.codec.MappedMapCodec
import java.util.function.BiFunction

fun <V> MapCodec<V>.log(logger: Logger): MapCodec<V> =
    LoggerMapCodec(this, logger)

fun <V> MapCodec<V>.withFallback(fallback: MapCodec<V>): MapCodec<V> =
    FallbackMapCodec(this, fallback)

fun <V, Q> MapCodec<Q>.mapWithOps(
    encodeMapper: BiFunction<V, DynamicOps<*>, Q>,
    decodeMapper: BiFunction<Q, DynamicOps<*>, V>
): MapCodec<V> =
    MappedMapCodec(this, encodeMapper, decodeMapper)