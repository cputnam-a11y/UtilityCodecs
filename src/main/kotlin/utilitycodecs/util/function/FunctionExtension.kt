@file:Suppress("unused")

package utilitycodecs.util.function

import java.util.function.BiFunction
import java.util.function.Function
import java.util.function.Supplier

fun <P1, R> Function<P1, R>.curryFirst(p1: P1): Supplier<R> =
    Supplier { this.apply(p1) }

fun <P1, P2, R> Function<P2, R>.dropFirst(): BiFunction<P1, P2, R> =
    BiFunction { _, p2 -> this.apply(p2) }

fun <P1, P2, R> Function<P1, R>.dropSecond(): BiFunction<P1, P2, R> =
    BiFunction { p1, _ -> this.apply(p1) }