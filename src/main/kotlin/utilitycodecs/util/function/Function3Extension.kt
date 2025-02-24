@file:Suppress("unused")

package utilitycodecs.util.function

import com.mojang.datafixers.util.Function3
import com.mojang.datafixers.util.Function4
import java.util.function.BiFunction

fun <P1, P2, P3, R> Function3<P1, P2, P3, R>.insertFirst(p1: P1): BiFunction<P2, P3, R> =
    BiFunction { p2, p3 -> this.apply(p1, p2, p3) }

fun <P1, P2, P3, R> Function3<P1, P2, P3, R>.insertSecond(p2: P2): BiFunction<P1, P3, R> =
    BiFunction { p1, p3 -> this.apply(p1, p2, p3) }

fun <P1, P2, P3, R> Function3<P1, P2, P3, R>.insertThird(p3: P3): BiFunction<P1, P2, R> =
    BiFunction { p1, p2 -> this.apply(p1, p2, p3) }

fun <P1, P2, P3, P4, R> Function3<P2, P3, P4, R>.dropFirst(): Function4<P1, P2, P3, P4, R> =
    Function4 { _, p2, p3, p4 -> this.apply(p2, p3, p4) }

fun <P1, P2, P3, P4, R> Function3<P1, P3, P4, R>.dropSecond(): Function4<P1, P2, P3, P4, R> =
    Function4 { p1, _, p3, p4 -> this.apply(p1, p3, p4) }

fun <P1, P2, P3, P4, R> Function3<P1, P2, P4, R>.dropThird(): Function4<P1, P2, P3, P4, R> =
    Function4 { p1, p2, _, p4 -> this.apply(p1, p2, p4) }

fun <P1, P2, P3, P4, R> Function3<P1, P2, P3, R>.dropFourth(): Function4<P1, P2, P3, P4, R> =
    Function4 { p1, p2, p3, _ -> this.apply(p1, p2, p3) }