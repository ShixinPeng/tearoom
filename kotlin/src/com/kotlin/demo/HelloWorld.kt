package com.kotlin.demo

import java.util.function.Consumer

fun main(args: Array<String>) {
    println("hello world")
    var list: List<String> = listOf("hello","world","kotlin")
    for (str in list){
        println(str)
    }

    println("----------")
    list.forEach(Consumer { println(it) })
    println("----------")
    list.forEach(System.out::println)
}