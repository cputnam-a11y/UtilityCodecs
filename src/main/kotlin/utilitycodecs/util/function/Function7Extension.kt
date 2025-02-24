@file:Suppress("unused")
package utilitycodecs.util.function

import com.mojang.datafixers.util.Function6
import com.mojang.datafixers.util.Function7
import com.mojang.datafixers.util.Function8

fun <P1, P2, P3, P4, P5, P6, P7, R> Function7<P1, P2, P3, P4, P5, P6, P7, R>.insertFirst(p1: P1): Function6<P2, P3, P4, P5, P6, P7, R> =
    Function6 { p2, p3, p4, p5, p6, p7 -> this.apply(p1, p2, p3, p4, p5, p6, p7) }

fun <P1, P2, P3, P4, P5, P6, P7, R> Function7<P1, P2, P3, P4, P5, P6, P7, R>.insertSecond(p2: P2): Function6<P1, P3, P4, P5, P6, P7, R> =
    Function6 { p1, p3, p4, p5, p6, p7 -> this.apply(p1, p2, p3, p4, p5, p6, p7) }

fun <P1, P2, P3, P4, P5, P6, P7, R> Function7<P1, P2, P3, P4, P5, P6, P7, R>.insertThird(p3: P3): Function6<P1, P2, P4, P5, P6, P7, R> =
    Function6 { p1, p2, p4, p5, p6, p7 -> this.apply(p1, p2, p3, p4, p5, p6, p7) }

fun <P1, P2, P3, P4, P5, P6, P7, R> Function7<P1, P2, P3, P4, P5, P6, P7, R>.insertFourth(p4: P4): Function6<P1, P2, P3, P5, P6, P7, R> =
    Function6 { p1, p2, p3, p5, p6, p7 -> this.apply(p1, p2, p3, p4, p5, p6, p7) }

fun <P1, P2, P3, P4, P5, P6, P7, R> Function7<P1, P2, P3, P4, P5, P6, P7, R>.insertFifth(p5: P5): Function6<P1, P2, P3, P4, P6, P7, R> =
    Function6 { p1, p2, p3, p4, p6, p7 -> this.apply(p1, p2, p3, p4, p5, p6, p7) }

fun <P1, P2, P3, P4, P5, P6, P7, R> Function7<P1, P2, P3, P4, P5, P6, P7, R>.insertSixth(p6: P6): Function6<P1, P2, P3, P4, P5, P7, R> =
    Function6 { p1, p2, p3, p4, p5, p7 -> this.apply(p1, p2, p3, p4, p5, p6, p7) }

fun <P1, P2, P3, P4, P5, P6, P7, R> Function7<P1, P2, P3, P4, P5, P6, P7, R>.insertSeventh(p7: P7): Function6<P1, P2, P3, P4, P5, P6, R> =
    Function6 { p1, p2, p3, p4, p5, p6 -> this.apply(p1, p2, p3, p4, p5, p6, p7) }

fun <P1, P2, P3, P4, P5, P6, P7, P8, R> Function7<P2, P3, P4, P5, P6, P7, P8, R>.dropFirst(): Function8<P1, P2, P3, P4, P5, P6, P7, P8, R> =
    Function8 { _, p2, p3, p4, p5, p6, p7, p8 -> this.apply(p2, p3, p4, p5, p6, p7, p8) }

fun <P1, P2, P3, P4, P5, P6, P7, P8, R> Function7<P1, P3, P4, P5, P6, P7, P8, R>.dropSecond(): Function8<P1, P2, P3, P4, P5, P6, P7, P8, R> =
    Function8 { p1, _, p3, p4, p5, p6, p7, p8 -> this.apply(p1, p3, p4, p5, p6, p7, p8) }

fun <P1, P2, P3, P4, P5, P6, P7, P8, R> Function7<P1, P2, P4, P5, P6, P7, P8, R>.dropThird(): Function8<P1, P2, P3, P4, P5, P6, P7, P8, R> =
    Function8 { p1, p2, _, p4, p5, p6, p7, p8 -> this.apply(p1, p2, p4, p5, p6, p7, p8) }

fun <P1, P2, P3, P4, P5, P6, P7, P8, R> Function7<P1, P2, P3, P5, P6, P7, P8, R>.dropFourth(): Function8<P1, P2, P3, P4, P5, P6, P7, P8, R> =
    Function8 { p1, p2, p3, _, p5, p6, p7, p8 -> this.apply(p1, p2, p3, p5, p6, p7, p8) }

fun <P1, P2, P3, P4, P5, P6, P7, P8, R> Function7<P1, P2, P3, P4, P6, P7, P8, R>.dropFifth(): Function8<P1, P2, P3, P4, P5, P6, P7, P8, R> =
    Function8 { p1, p2, p3, p4, _, p6, p7, p8 -> this.apply(p1, p2, p3, p4, p6, p7, p8) }

fun <P1, P2, P3, P4, P5, P6, P7, P8, R> Function7<P1, P2, P3, P4, P5, P7, P8, R>.dropSixth(): Function8<P1, P2, P3, P4, P5, P6, P7, P8, R> =
    Function8 { p1, p2, p3, p4, p5, _, p7, p8 -> this.apply(p1, p2, p3, p4, p5, p7, p8) }

fun <P1, P2, P3, P4, P5, P6, P7, P8, R> Function7<P1, P2, P3, P4, P5, P6, P8, R>.dropSeventh(): Function8<P1, P2, P3, P4, P5, P6, P7, P8, R> =
    Function8 { p1, p2, p3, p4, p5, p6, _ , p8 -> this.apply(p1, p2, p3, p4, p5, p6, p8) }

fun <P1, P2, P3, P4, P5, P6, P7, P8, R> Function7<P1, P2, P3, P4, P5, P6, P7, R>.dropEighth(): Function8<P1, P2, P3, P4, P5, P6, P7, P8, R> =
    Function8 { p1, p2, p3, p4, p5, p6, p7, _ -> this.apply(p1, p2, p3, p4, p5, p6, p7) }