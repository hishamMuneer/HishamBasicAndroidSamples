package com.hisham.javalibrary.lruCacheImpl

/**
 * Created by Hisham on 05/Nov/2018 - 00:15
 */
object LruCacheDriver {

    @JvmStatic
    fun main(args: Array<String>){
        val myLruCache = MyLruCache(4)
        myLruCache.get(1)
        myLruCache.get(2)
        myLruCache.get(3)
        myLruCache.get(4)
        myLruCache.get(5)
        myLruCache.get(6)
        myLruCache.get(7)
        myLruCache.get(8)
        myLruCache.get(7)
        myLruCache.get(1)
        myLruCache.get(9)
        myLruCache.get(10)
        myLruCache.remove(10)
        myLruCache.remove(4)
        myLruCache.remove(8)

//        myLruCache.get(1)
//        myLruCache.get(10)
//        myLruCache.get(15)
//        myLruCache.get(10)
//        myLruCache.get(12)
//        myLruCache.get(18)
//        myLruCache.get(13)

        myLruCache.printCacheDetails()
    }

}