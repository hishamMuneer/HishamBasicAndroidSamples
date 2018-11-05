package com.hisham.javalibrary.lruCacheImpl

import java.util.*

/**
 * Created by Hisham on 05/Nov/2018 - 21:30
 */
class MyLruCacheDeque(private val cacheSize: Int) {

    data class MyModel (val key: Int, val data: String)

    private val deque: Deque<MyModel>
    private val map: HashMap<Int, MyModel> = HashMap(cacheSize)
    private var hit: Int = 0
    private var miss: Int =0
    init {
        deque = LinkedList()
    }

    private fun createModel(key: Int): MyModel = MyModel(key, "Value: $key")

    fun putItem(key: Int): String {
        val value = createModel(key)
        if(deque.size >= cacheSize) { // make space
            if(map.containsKey(key)){ // hit
                if(deque.remove(map[key]))
                    deque.addFirst(map[key])
            } else { // miss
                val removeLast = deque.removeLast()
                map.remove(removeLast.key)
                deque.addFirst(value)
                map[key] = value
            }
        } else {
            deque.addFirst(value)
            map[key] = value
        }
        return value.data
    }

    fun getItem(key: Int): String? {
        return if(map.containsKey(key)) {
            hit++
            map[key]?.data
        } else {
            miss++
            putItem(key)
        }
    }

    fun currentSizeMap(): Int = map.size
    fun currentSizeDeque(): Int = deque.size
    fun getHitCount(): Int = hit
    fun getMissCount(): Int = miss
    fun printCacheDetails() {
        val iterator = deque.iterator()
        while(iterator.hasNext()){
            val (key, data) = iterator.next()
            System.out.println(data)
        }
    }

}