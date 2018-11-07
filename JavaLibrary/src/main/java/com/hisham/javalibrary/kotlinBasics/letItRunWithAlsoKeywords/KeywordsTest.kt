package com.hisham.javalibrary.kotlinBasics.letItRunWithAlsoKeywords

import java.awt.Color
import java.awt.Color.RED

/**
 * Created by Hisham on 07/Nov/2018 - 22:58
 */
object KeywordsTest {

    @JvmStatic
    fun main(args: Array<String>) {
        val str = "Hello world"
        val length = str.let {
            println(it)
            it.length
        }


        val a = 1
        val b = 2

        val x = a.let { it + 2 }.let { val i = it + b; i }
        println(x) //5


        var y = "Data"
        y.let { (it + "Hi").let { it -> println("Inner is $it and outer is $it") } }


        var name: String? = "Kotlin let null check"
        name?.let { println(it) } //prints Kotlin let null check
        var name2 = name?.let { "Hi $it" }
        name = null
        name?.let { println(it) } //nothing happens
        println(name2)


        var p: String? = null
        val any: String? = p?.let {
            println("p is $p")
            it
        } ?: run {
            println("p was null. Setting default value to: ")
            p = "Kotlin"
            p
        }

        println(p)
        println(any)
//Prints
//p was null. Setting default value to:
//Kotlin



        data class Person(var name: String, var tutorial : String)
        var person = Person("Anupam", "Kotlin")

        val apply = person.apply {
            this.tutorial = "Swift"
        }
        println(person)

        val apple: Apple = Apple(RED)
        val redApple = apple.takeIf { it.color == RED }
        val otherApple = apple.takeUnless { it.color == RED }

        println(redApple)
        println(otherApple)

        fun task(): List<Boolean> {
            val isEven: Int.() -> Boolean = { this % 2 == 0 }
            val isOdd: Int.() -> Boolean = { this % 2 != 0 }

            return listOf(42.isOdd(), 239.isOdd(), 294823098.isEven())
        }

    }

    data class Apple(var color: Color)
}