package generics

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.LinkedHashSet
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.system.measureTimeMillis

class Group<T>

abstract class Mammal(){
    abstract fun sound()

    fun soundOl(){
        println("Mau")
    }
}

class Dog(val item: String) : Mammal(){
    override fun sound() {
       println("OLOLOL")
    }

}

class Producer<out T>(private val item: T) {
    fun produceSome(): T {
        return item
    }
}

fun <T> getRandom(list : List<T>): T{
    val randomIndex = Random.nextInt(list.size)
    return list[randomIndex]
}

fun removeAllStaff(list: MutableList<*>): Boolean{
    return list.removeIf { it == null }
}

inline fun <reified T>T.getTagName() = T::class.isInstance(value = Dog(item = "Corgi"))
//fun main() {
//    val list = (1..100).toList()
//    println(getRandom(list))
//
//    val dogs: List<Dog> = listOf()
//    val mammals:List<Mammal> = dogs
//    val dog = Dog(item = "Corgi")
//    dog.soundOl()
//    val producer = Producer("Hello, World!")
//    val result: String = producer.produceSome()
//    println(result)
//    println(list.contains(200))
//    val list1: MutableList<*> = mutableListOf(1,null,2,3)
//    val element: Any? = list1[1]
//    println(element)
//    val someObject = Dog(item = "Corgi")
//    val tagName = someObject.getTagName()
//    println(tagName)
//    val listList = mutableListOf<Int>(1, 2, 3, 4)
//    val iterator = listList.iterator()
//    while (iterator.hasNext()) {
//        val n = iterator.next()
//        if (n % 2 == 0) {
//            iterator.remove()
//        }
//    }
//    println(listList)
//    val list = mutableListOf<Int>(1, 2, 3, 4)
//    val each = list.iterator()
//    while (each.hasNext()){
//        if(each.next() % 2 == 0){
//            each.remove()
//        }
//    }
//    println(list)

//    val a = ArrayList<String>()
//    a.add("12")
//    a.add("23")
//    a.add("34")
//    val b = ArrayList<String>(initialCapacity = 10)
//    b.add(5,"5")
//    println(b)
//    val c = emptyList<Nothing>()

    fun addZerosBeforeEach(list: LinkedList<Int>) {
        val startTime = System.currentTimeMillis()
        val elem: MutableListIterator<Int> = list.listIterator()
        while (elem.hasNext()) {
            elem.add(0)
            elem.next()
        }
        val endTime = System.currentTimeMillis()
        println("Время выполнения: ${endTime - startTime} миллисекунд")
    }

fun addZerosBeforeEachFor(list: LinkedList<Int>) {
    val startTime = System.currentTimeMillis()
    for (i in 0..<list.size step 2) {
        list.add(i, 0)
    }
    val endTime = System.currentTimeMillis()
    println("Время выполнения: ${endTime - startTime} миллисекунд")
}

fun createHashMap(size: Int): HashMap<Int, Int> {
    val hashMap = HashMap<Int, Int>(size)
    for (i in 1..size) {
        hashMap[i] = i
    }
    return hashMap
}

fun createTreeMap(size: Int): TreeMap<Int , Int> {
    val treeMap = TreeMap<Int, Int>()
    for (i in 1..size) {
        treeMap[i] = i
    }
    return treeMap
}

fun createLinkedHashSet(size: Int): LinkedHashSet<Int> {
    val linkedHashSet = LinkedHashSet<Int>(size)
    for (i in 1..size) {
        linkedHashSet.add(i)
    }
    return linkedHashSet
}

fun main() {
//    val list = LinkedList<Int>()
//    for (i in 1..1000000) {
//        list.add(i)
//    }
//
//    println("Результат выполнения addZerosBeforeEach:")
//    addZerosBeforeEach(list)
//
//    println("Результат выполнения addZerosBeforeEachFor:")
//    addZerosBeforeEachFor(list)
    val size = 100000

    val hashMapTime = measureTimeMillis {
        val hashMap = createHashMap(size)
    }
    println("Время создания HashMap: $hashMapTime миллисекунд")

    val treeMapTime = measureTimeMillis {
        val treeMap = createTreeMap(size)
    }
    println("Время создания TreeMap: $treeMapTime миллисекунд")

    val linkedHashSetTime = measureTimeMillis {
        val linkedHashSet = createLinkedHashSet(size)
    }
    println("Время создания LinkedHashSet: $linkedHashSetTime миллисекунд")
}

/*    list1.add(1 as Nothing)
    list1.add(Any() as Nothing)
    list1.add(null)*/ // Null can not be a value of a non-null type Nothing
    //override fun contains(element: @UnsafeVariance E): Boolean
    //override fun containsAll(elements: Collection<@UnsafeVariance E>): Boolean
    //public fun lastIndexOf(element: @UnsafeVariance E): Int
    //public fun indexOf(element: @UnsafeVariance E): Int
//}