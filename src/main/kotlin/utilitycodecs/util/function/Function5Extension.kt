@file:Suppress("unused")

package utilitycodecs.util.function

import com.mojang.datafixers.util.Function4
import com.mojang.datafixers.util.Function5
import com.mojang.datafixers.util.Function6

fun <P1, P2, P3, P4, P5, R> Function5<P1, P2, P3, P4, P5, R>.insertFirst(p1: P1): Function4<P2, P3, P4, P5, R> =
    Function4 { p2, p3, p4, p5 -> this.apply(p1, p2, p3, p4, p5) }

fun <P1, P2, P3, P4, P5, R> Function5<P1, P2, P3, P4, P5, R>.insertSecond(p2: P2): Function4<P1, P3, P4, P5, R> =
    Function4 { p1, p3, p4, p5 -> this.apply(p1, p2, p3, p4, p5) }

fun <P1, P2, P3, P4, P5, R> Function5<P1, P2, P3, P4, P5, R>.insertThird(p3: P3): Function4<P1, P2, P4, P5, R> =
    Function4 { p1, p2, p4, p5 -> this.apply(p1, p2, p3, p4, p5) }

fun <P1, P2, P3, P4, P5, R> Function5<P1, P2, P3, P4, P5, R>.insertFourth(p4: P4): Function4<P1, P2, P3, P5, R> =
    Function4 { p1, p2, p3, p5 -> this.apply(p1, p2, p3, p4, p5) }

fun <P1, P2, P3, P4, P5, R> Function5<P1, P2, P3, P4, P5, R>.insertFifth(p5: P5): Function4<P1, P2, P3, P4, R> =
    Function4 { p1, p2, p3, p4 -> this.apply(p1, p2, p3, p4, p5) }

fun <P1, P2, P3, P4, P5, P6, R> Function5<P2, P3, P4, P5, P6, R>.dropFirst(): Function6<P1, P2, P3, P4, P5, P6, R> =
    Function6 { _, p2, p3, p4, p5, p6 -> this.apply(p2, p3, p4, p5, p6) }

fun <P1, P2, P3, P4, P5, P6, R> Function5<P1, P3, P4, P5, P6, R>.dropSecond(): Function6<P1, P2, P3, P4, P5, P6, R> =
    Function6 { p1, _, p3, p4, p5, p6 -> this.apply(p1, p3, p4, p5, p6) }

fun <P1, P2, P3, P4, P5, P6, R> Function5<P1, P2, P4, P5, P6, R>.dropThird(): Function6<P1, P2, P3, P4, P5, P6, R> =
    Function6 { p1, p2, _, p4, p5, p6 -> this.apply(p1, p2, p4, p5, p6) }

fun <P1, P2, P3, P4, P5, P6, R> Function5<P1, P2, P3, P5, P6, R>.dropFourth(): Function6<P1, P2, P3, P4, P5, P6, R> =
    Function6 { p1, p2, p3, _, p5, p6 -> this.apply(p1, p2, p3, p5, p6) }

fun <P1, P2, P3, P4, P5, P6, R> Function5<P1, P2, P3, P4, P6, R>.dropFifth(): Function6<P1, P2, P3, P4, P5, P6, R> =
    Function6 { p1, p2, p3, p4, _, p6 -> this.apply(p1, p2, p3, p4, p6) }

fun <P1, P2, P3, P4, P5, P6, R> Function5<P1, P2, P3, P4, P5, R>.dropSixth(): Function6<P1, P2, P3, P4, P5, P6, R> =
    Function6 { p1, p2, p3, p4, p5, _ -> this.apply(p1, p2, p3, p4, p5) }