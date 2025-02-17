@file:Suppress("unused")

package utilitycodecs.util.function

import com.mojang.datafixers.util.Function3
import com.mojang.datafixers.util.Function4
import com.mojang.datafixers.util.Function5

fun <P1, P2, P3, P4, R> Function4<P1, P2, P3, P4, R>.curryFirst(p1: P1): Function3<P2, P3, P4, R> =
    Function3 { p2, p3, p4 -> this.apply(p1, p2, p3, p4) }

fun <P1, P2, P3, P4, R> Function4<P1, P2, P3, P4, R>.currySecond(p2: P2): Function3<P1, P3, P4, R> =
    Function3 { p1, p3, p4 -> this.apply(p1, p2, p3, p4) }

fun <P1, P2, P3, P4, R> Function4<P1, P2, P3, P4, R>.curryThird(p3: P3): Function3<P1, P2, P4, R> =
    Function3 { p1, p2, p4 -> this.apply(p1, p2, p3, p4) }

fun <P1, P2, P3, P4, R> Function4<P1, P2, P3, P4, R>.curryFourth(p4: P4): Function3<P1, P2, P3, R> =
    Function3 { p1, p2, p3 -> this.apply(p1, p2, p3, p4) }

fun <P1, P2, P3, P4, P5, R> Function4<P2, P3, P4, P5, R>.dropFirst(): Function5<P1, P2, P3, P4, P5, R> =
    Function5 { _, p2, p3, p4, p5 -> this.apply(p2, p3, p4, p5) }

fun <P1, P2, P3, P4, P5, R> Function4<P1, P3, P4, P5, R>.dropSecond(): Function5<P1, P2, P3, P4, P5, R> =
    Function5 { p1, _, p3, p4, p5 -> this.apply(p1, p3, p4, p5) }

fun <P1, P2, P3, P4, P5, R> Function4<P1, P2, P4, P5, R>.dropThird(): Function5<P1, P2, P3, P4, P5, R> =
    Function5 { p1, p2, _, p4, p5 -> this.apply(p1, p2, p4, p5) }

fun <P1, P2, P3, P4, P5, R> Function4<P1, P2, P3, P5, R>.dropFourth(): Function5<P1, P2, P3, P4, P5, R> =
    Function5 { p1, p2, p3, _, p5 -> this.apply(p1, p2, p3, p5) }

fun <P1, P2, P3, P4, P5, R> Function4<P1, P2, P3, P4, R>.dropFifth(): Function5<P1, P2, P3, P4, P5, R> =
    Function5 { p1, p2, p3, p4, _ -> this.apply(p1, p2, p3, p4) }