import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun main() {
  val a: Animal = Cat()  //так можно
//  val b: BoxOfAnimals<Animal> = BoxOfAnimals<Cat>(Cat())  //а вот так не получитс€
  val c: BoxOfAnimals<Cat> = BoxOfAnimals<Cat>(Cat()) //“ак сработает
  val fruitsArray = arrayOf("Apple", "Orange","Banana")
  println(typeIs<Int>("abc"))
  println(typeIs<String>("abc"))
}


inline fun <reified T> typeIs(x : Any): Boolean {
  return x is T
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