package com.kotlin.playground

fun main() {
    fun foo(bar: Int = 0, baz: Int = 1, qux: () -> Unit) { qux }

    foo(1) { println("hello") }     // Uses the default value baz = 1
    foo(qux = { println("hello") }) // Uses both default values bar = 0 and baz = 1
    foo { println("hello") }

    run { println("...") }


    val pair = "a" to "b"
    val takeIf = pair.takeIf { it.first.contentEquals("a") }

    println(takeIf)
}

