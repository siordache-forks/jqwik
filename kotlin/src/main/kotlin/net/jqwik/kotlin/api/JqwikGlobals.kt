package net.jqwik.kotlin.api

import kotlinx.coroutines.*
import net.jqwik.api.Arbitrary
import net.jqwik.api.Functions
import net.jqwik.api.arbitraries.FunctionArbitrary
import org.apiguardian.api.API
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.reflect.KClass

/**
 * Function to create an arbitrary for [Pair<A, B>][Pair].
 */
@API(status = API.Status.EXPERIMENTAL, since = "1.6.0")
fun <A, B> anyPair(firstArbitrary: Arbitrary<A>, secondArbitrary: Arbitrary<B>) =
    combine(firstArbitrary, secondArbitrary) { a, b -> Pair(a, b) }

/**
 * Function to create an arbitrary for [Triple<A, B, C>][Triple].
 */
@API(status = API.Status.EXPERIMENTAL, since = "1.6.0")
fun <A, B, C> anyTriple(firstArbitrary: Arbitrary<A>, secondArbitrary: Arbitrary<B>, thirdArbitrary: Arbitrary<C>) =
    combine(firstArbitrary, secondArbitrary, thirdArbitrary) { a, b, c -> Triple(a, b, c) }


/**
 * Function to create a [FunctionWrapper].
 *
 * This is a Kotlin convenience for [Functions.function] which requires a Java class instead.
 */
@API(status = API.Status.EXPERIMENTAL, since = "1.6.0")
fun anyFunction(kClass: KClass<*>): Functions.FunctionWrapper {
    return Functions.function(kClass.java)
}

/**
 * Create a [FunctionArbitrary] for Kotlin function without parameters.
 */
@API(status = API.Status.EXPERIMENTAL, since = "1.6.0")
fun <R> anyFunction0(returning: Arbitrary<R>): FunctionArbitrary<Function0<R>, R> {
    return anyFunction(Function0::class).returning(returning)
}

/**
 * Create a [FunctionArbitrary] for Kotlin function with 1 parameter.
 */
@API(status = API.Status.EXPERIMENTAL, since = "1.6.0")
fun <A, R> anyFunction1(returning: Arbitrary<R>): FunctionArbitrary<Function1<A, R>, R> {
    return anyFunction(Function1::class).returning(returning)
}

/**
 * Create a [FunctionArbitrary] for Kotlin function with 2 parameters.
 */
@API(status = API.Status.EXPERIMENTAL, since = "1.6.0")
fun <A, B, R> anyFunction2(returning: Arbitrary<R>): FunctionArbitrary<Function2<A, B, R>, R> {
    return anyFunction(Function2::class).returning(returning)
}

/**
 * Create a [FunctionArbitrary] for Kotlin function with 3 parameters.
 */
@API(status = API.Status.EXPERIMENTAL, since = "1.6.0")
fun <A, B, C, R> anyFunction3(returning: Arbitrary<R>): FunctionArbitrary<Function3<A, B, C, R>, R> {
    return anyFunction(Function3::class).returning(returning)
}

/**
 * Create a [FunctionArbitrary] for Kotlin function with 4 parameters.
 */
@API(status = API.Status.EXPERIMENTAL, since = "1.6.0")
fun <A, B, C, D, R> anyFunction4(returning: Arbitrary<R>): FunctionArbitrary<Function4<A, B, C, D, R>, R> {
    return anyFunction(Function4::class).returning(returning)
}

/**
 * Wrap a property so that it can use asynchronous functions within.
 *
 * @param block Code block that should return a [Boolean] or [Unit].
 */
@API(status = API.Status.EXPERIMENTAL, since = "1.6.0")
fun <T> runBlockingProperty(
    context: CoroutineContext = EmptyCoroutineContext,
    block: suspend CoroutineScope.() -> T
): T {
    return runBlocking(context, block)
}