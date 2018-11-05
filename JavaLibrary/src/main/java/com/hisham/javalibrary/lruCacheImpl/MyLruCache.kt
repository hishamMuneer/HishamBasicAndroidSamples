package com.hisham.javalibrary.lruCacheImpl

import sun.misc.LRUCache

/**
 * Created by Hisham on 04/Nov/2018 - 23:24 - Not written correctly, broken
 */
class MyLruCache(private val cacheSize: Int) {
    private data class MyEntry(val key: Int) {
//        var key: Int? = null
        var value: String? = null
        var nextEntry: MyEntry? = null
        var previousEntry: MyEntry? = null
        override fun toString(): String {
            return "MyEntry[ key: $key, $value]"
        }
    }
    private val map: HashMap<Int, MyEntry> = HashMap(cacheSize)
    private var front: MyEntry? = null // front of doubly LL
    private var rear: MyEntry? = null // rear of doubly LL

    fun get(key: Int?): String? {
        if (key == null) return null
        // if map doesn't contains the key
        return if (!map.containsKey(key)) {
            val entry = MyEntry(key)
            entry.value = getValueFromKey(key)
            if(front == null){
                front = entry
                rear = entry
            }
            if (map.size >= cacheSize) { // map size need to be trimmed before addition
                removeFromKey(rear?.key)
            }
            moveEntryToTop(entry)
            map[key] = entry
//            add(key)
            null
        } else {
            // its a hit, move the entry to front
            val myEntry = map[key]
            val removeEntry = removeFromKey(myEntry?.key)
            moveEntryToTop(removeEntry!!)
            removeEntry.value
        }
    }

    fun remove(key: Int?): String? {
        return removeFromKey(key)?.value
    }

    private fun removeFromKey(key: Int?): MyEntry? {
        if (key == null) return null
        if(!map.containsKey(key)) return null
        val removeEntry = map[key]
        var localFront = front
        var localRear = rear
        if(removeEntry == localFront && localFront != null){
            localFront = localFront.nextEntry
            localFront?.previousEntry = null
            front = localFront
        } else if(removeEntry == localRear && localRear != null){
            localRear = localRear.previousEntry
            localRear?.nextEntry = null
            rear = localRear
        } else {
            val removePrev = removeEntry?.previousEntry
            val removeNext = removeEntry?.nextEntry

            removePrev?.nextEntry = removeNext
            removeNext?.previousEntry = removePrev
        }
        map.remove(key)
        return removeEntry
    }

    private fun moveEntryToTop(hitEntry: MyEntry) {
        if(hitEntry == front){
            // do nothing, item is already on front
            return
        }
        if(hitEntry == rear){
            // last entry was hit and it was rear too, move rear to its previous
            rear = hitEntry.previousEntry
        }
        val hitsPrev = hitEntry.previousEntry
        val hitsNext = hitEntry.nextEntry
        if (hitsPrev != null) hitsPrev.nextEntry = hitsNext
        if (hitsNext != null) hitsNext.previousEntry = hitsPrev
        // Hit entry is removed from the position and linked list is mended
        // Now moving the entry to first position
        hitEntry.previousEntry = null
        hitEntry.nextEntry = front
        front = hitEntry
    }

    private fun getValueFromKey(key: Int): String {
        // value is fetched from some place
        return "Value: $key"
    }



    fun printCacheDetails(){
        printEntries(front)
    }

    private fun printEntries(front: MyEntry?) {
        if(front == null) {
            return
        }
        System.out.println("$front")
        printEntries(front.nextEntry)
    }
}