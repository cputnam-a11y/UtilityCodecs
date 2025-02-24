@file:Suppress("unused")

package utilitycodecs.util.function

import com.mojang.datafixers.util.Function3
import java.util.function.BiFunction
import java.util.function.Function

fun <P1, P2, R> BiFunction<P1, P2, R>.insertFirst(p1: P1): Function<P2, R> =
    Function { p2 -> this.apply(p1, p2) }

fun <P1, P2, R> BiFunction<P1, P2, R>.insertSecond(p2: P2): Function<P1, R> =
    Function { p1 -> this.apply(p1, p2) }

fun <P1, P2, P3, R> BiFunction<P2, P3, R>.dropFirst(): Function3<P1, P2, P3, R> =
    Function3 { _, p2, p3 -> this.apply(p2, p3) }

fun <P1, P2, P3, R> BiFunction<P1, P2, R>.dropLast(): Function3<P1, P2, P3, R> =
    Function3 { p1, p2, _ -> this.apply(p1, p2) }
