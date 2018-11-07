package com.hisham.javalibrary.kotlinBasics.higerOrderFunctions

/**
 * Created by Hisham on 07/Nov/2018 - 17:50
 */
object Benchmarking {

    @JvmStatic
    fun main(args: Array<String>) {
        benchmarkingProcess()
        BenchMarkingChild().doParent()
    }

    private fun benchmarkingProcess() {

        val benchmark = benchmark {
            for (i in 0..10000000000L) Object()
        }

        println("Executed in : $benchmark ms")

//        val time = System.currentTimeMillis()
//        for (i in 0..10000000000) {
////            println("Object: ${java.lang.Object()} - $i")
//            java.lang.Object()
//        }
//        println("Total time taken : ${System.currentTimeMillis() - time} ms")


    }

    private fun benchmark(block: () -> Unit): Long {
        val time = System.currentTimeMillis()
        block()
        return System.currentTimeMillis() - time
    }
}