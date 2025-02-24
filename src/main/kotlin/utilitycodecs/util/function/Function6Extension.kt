@file:Suppress("unused")
package utilitycodecs.util.function

import com.mojang.datafixers.util.Function5
import com.mojang.datafixers.util.Function6
import com.mojang.datafixers.util.Function7

fun <P1, P2, P3, P4, P5, P6, R> Function6<P1, P2, P3, P4, P5, P6, R>.insertFirst(p1: P1): Function5<P2, P3, P4, P5, P6, R> =
    Function5 { p2, p3, p4, p5, p6 -> this.apply(p1, p2, p3, p4, p5, p6) }

fun <P1, P2, P3, P4, P5, P6, R> Function6<P1, P2, P3, P4, P5, P6, R>.insertSecond(p2: P2): Function5<P1, P3, P4, P5, P6, R> =
    Function5 { p1, p3, p4, p5, p6 -> this.apply(p1, p2, p3, p4, p5, p6) }

fun <P1, P2, P3, P4, P5, P6, R> Function6<P1, P2, P3, P4, P5, P6, R>.insertThird(p3: P3): Function5<P1, P2, P4, P5, P6, R> =
    Function5 { p1, p2, p4, p5, p6 -> this.apply(p1, p2, p3, p4, p5, p6) }

fun <P1, P2, P3, P4, P5, P6, R> Function6<P1, P2, P3, P4, P5, P6, R>.insertFourth(p4: P4): Function5<P1, P2, P3, P5, P6, R> =
    Function5 { p1, p2, p3, p5, p6 -> this.apply(p1, p2, p3, p4, p5, p6) }

fun <P1, P2, P3, P4, P5, P6, R> Function6<P1, P2, P3, P4, P5, P6, R>.insertFifth(p5: P5): Function5<P1, P2, P3, P4, P6, R> =
    Function5 { p1, p2, p3, p4, p6 -> this.apply(p1, p2, p3, p4, p5, p6) }

fun <P1, P2, P3, P4, P5, P6, R> Function6<P1, P2, P3, P4, P5, P6, R>.insertSixth(p6: P6): Function5<P1, P2, P3, P4, P5, R> =
    Function5 { p1, p2, p3, p4, p5 -> this.apply(p1, p2, p3, p4, p5, p6) }

fun <P1, P2, P3, P4, P5, P6, P7, R> Function6<P2, P3, P4, P5, P6, P7, R>.dropFirst(): Function7<P1, P2, P3, P4, P5, P6, P7, R> =
    Function7 { _, p2, p3, p4, p5, p6, p7 -> this.apply(p2, p3, p4, p5, p6, p7) }

fun <P1, P2, P3, P4, P5, P6, P7, R> Function6<P1, P3, P4, P5, P6, P7, R>.dropSecond(): Function7<P1, P2, P3, P4, P5, P6, P7, R> =
    Function7 { p1, _, p3, p4, p5, p6, p7 -> this.apply(p1, p3, p4, p5, p6, p7) }

fun <P1, P2, P3, P4, P5, P6, P7, R> Function6<P1, P2, P4, P5, P6, P7, R>.dropThird(): Function7<P1, P2, P3, P4, P5, P6, P7, R> =
    Function7 { p1, p2, _, p4, p5, p6, p7 -> this.apply(p1, p2, p4, p5, p6, p7) }

fun <P1, P2, P3, P4, P5, P6, P7, R> Function6<P1, P2, P3, P5, P6, P7, R>.dropFourth(): Function7<P1, P2, P3, P4, P5, P6, P7, R> =
    Function7 { p1, p2, p3, _, p5, p6, p7 -> this.apply(p1, p2, p3, p5, p6, p7) }

fun <P1, P2, P3, P4, P5, P6, P7, R> Function6<P1, P2, P3, P4, P6, P7, R>.dropFifth(): Function7<P1, P2, P3, P4, P5, P6, P7, R> =
    Function7 { p1, p2, p3, p4, _, p6, p7 -> this.apply(p1, p2, p3, p4, p6, p7) }

fun <P1, P2, P3, P4, P5, P6, P7, R> Function6<P1, P2, P3, P4, P5, P7, R>.dropSixth(): Function7<P1, P2, P3, P4, P5, P6, P7, R> =
    Function7 { p1, p2, p3, p4, p5, _, p7 -> this.apply(p1, p2, p3, p4, p5, p7) }

fun <P1, P2, P3, P4, P5, P6, P7, R> Function6<P1, P2, P3, P4, P5, P6, R>.dropSeventh(): Function7<P1, P2, P3, P4, P5, P6, P7, R> =
    Function7 { p1, p2, p3, p4, p5, p6, _ -> this.apply(p1, p2, p3, p4, p5, p6) }