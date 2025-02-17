@file:Suppress("unused")

package utilitycodecs.util.function

import java.util.function.Function
import java.util.function.Supplier

fun <P1, R> Supplier<R>.dropFirst(): Function<P1, R> =
    Function { _ -> this.get() }