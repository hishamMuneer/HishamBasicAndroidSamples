package com.hisham.javalibrary.lruCacheImpl

/**
 * Created by Hisham on 05/Nov/2018 - 00:15
 */
object LruCacheDriver {

    @JvmStatic
    fun main(args: Array<String>){
        val myLruCache = MyLruCacheDeque(6)
        myLruCache.getItem(1)
        myLruCache.getItem(2)
        myLruCache.getItem(3)
        myLruCache.getItem(4)
        myLruCache.getItem(5)
        myLruCache.getItem(1)
        myLruCache.getItem(6)
        myLruCache.getItem(7)
        myLruCache.getItem(8)
        myLruCache.getItem(7)
        myLruCache.printCacheDetails()
        System.out.println("Current map size: ${myLruCache.currentSizeMap()}" )
        System.out.println("Current deque size: ${myLruCache.currentSizeDeque()}" )
        myLruCache.getItem(1)
        myLruCache.getItem(9)
        myLruCache.getItem(10)
//        myLruCache.remove(10)
//        myLruCache.remove(4)
//        myLruCache.remove(8)

//        myLruCache.get(1)
//        myLruCache.get(10)
//        myLruCache.get(15)
//        myLruCache.get(10)
//        myLruCache.get(12)
//        myLruCache.get(18)
//        myLruCache.get(13)

        myLruCache.printCacheDetails()
        System.out.println("Current map size: ${myLruCache.currentSizeMap()}" )
        System.out.println("Current deque size: ${myLruCache.currentSizeDeque()}" )
        System.out.println("Hits: ${myLruCache.getHitCount()}" )
        System.out.println("Misses: ${myLruCache.getMissCount()}" )
    }

}