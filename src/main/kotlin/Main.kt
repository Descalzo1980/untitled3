import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    tryFlow()
    flowOf()
    asFlow()
    flow()
    channelFlow()
}


inline fun <reified T> typeIs(x : Any): Boolean {
  return x is T
}

fun tryFlow() = runBlocking{
    flow {
        (0..10).forEach {
            emit(it)
        }
    }.map {
        it * it
    }.collect {
        println(it.toString())
    }
}

fun flowOf() = runBlocking {
    flowOf(4, 2, 5, 1, 7)
        .collect {
            println(it.toString())
        }
}
fun asFlow() = runBlocking {
    (1..5).asFlow()
        .collect {
            println(it.toString())
        }
}
fun flow() = runBlocking {
    flow {
        (0..10).forEach{
            emit(it)
        }
    }
        .collect{
            it.toString()
        }
}
fun channelFlow() = runBlocking {
    channelFlow{
        (0..10).forEach{
            send(it)
        }
    }.collect{
        it.toString()
    }
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