<<<<<<< HEAD
fun main() {
    val result = arrayOf("a", "b", "c")
    result.swap(1,2)
    result.print()
    val dog = Dog()
    dog.sleep()
    dog.bark()

}
class Dog{
    fun bark(){
        println("Woff")
    }
}

fun Dog.sleep(){
    println("May")
}

fun Dog.bark(){
    println("OLOLO")
}
fun String.getSecond(abc: String): Char{
    return abc[3]
}

fun String.getSecond(): Char{
    return this[3]
}

fun <T> Array<T>.swap(first: Int, second: Int){
    val index1 = this[first]
    val index2 = this[second]
    this[first] = index2
    this[second] = index1
}

fun <T> Array<T>.print(){
    for (i in this){
        println(i)
    }
}
=======
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun main() {
    testFlow()
}

fun testFlow() = runBlocking {
    val result = 1..10
    val flow = result.asFlow()
//    println(result)
//    val flow = flowOf(1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10)
    println("Print only even numbers multipl by 10: ")
    flow.filter { it % 2 == 0 }.map { it * 10 }.collect {
            println(it)
        }
    println("Print only odd numbers: ")
    flow.filter { it % 2 == 1 }.collect {
            println(it)
        }
}

>>>>>>> abc149e (ololo)
fun main() {
    testFlow()
}

fun testFlow() = runBlocking {
    val result = 1..10
    val flow = result.asFlow()
//    println(result)
//    val flow = flowOf(1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10)
    println("Print only even numbers multipl by 10: ")
    flow.filter { it % 2 == 0 }.map { it * 10 }.collect {
        println(it)
    }
    println("Print only odd numbers: ")
    flow.filter { it % 2 == 1 }.collect {
        println(it)
    }
}