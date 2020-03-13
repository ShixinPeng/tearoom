package com.kotlin.lecture01

fun main(args: Array<String>) {
    println(sum(3, 1))
    sumPrint(5, 3)
    printTemplate(1, 1)
}

fun sum(a: Int, b: Int): Int = a + b

fun sumPrint(a: Int, b: Int): Unit {
    println(a + b)
}

/**
 * 字符串模板a
 */
fun printTemplate(a: Int, b: Int) {
    println("$a + $b = ${a + b}")
}
