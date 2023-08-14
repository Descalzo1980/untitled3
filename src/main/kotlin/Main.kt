import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.reflect.typeOf
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
//    tryFlow()
//    flowOf()
//    asFlow()
//    flow()
//    channelFlow()

    val fruitsList = mutableListOf("Orange", "Apple", "Lime", "Apple", "Apple", "Apple")

    fruitsList.size
    println(fruitsList.last())
    println(fruitsList.indexOf("Apple"))
    println(fruitsList.lastIndexOf("Apple"))
    fruitsList[1] = "OLOLOL"
    println(fruitsList)
    fruitsList.remove("Lime")
    fruitsList.asFlow()
    fruitsList.removeAt(3)
    fruitsList.forEach(::printFruit)


    val stateFlow = MutableStateFlow<Int>(0)
    val sharedFlow = MutableSharedFlow<Int>()
//    CoroutineScope(Dispatchers.IO).launch {
//        stateFlow.collect {
//            println(it)
//        }
//    }
//
//    stateFlow.value = 1
//    stateFlow.value = 2
//    stateFlow.value = 1
//    stateFlow.value = 3
//
//    CoroutineScope(Dispatchers.IO).launch {
//        stateFlow.collect{
//            println(it.toString())
//        }
//    }


    runBlocking {
        sharedFlow.emit(1)
        sharedFlow.emit(2)
        sharedFlow.emit(2)
        sharedFlow.emit(1)
        sharedFlow.emit(3)
        sharedFlow.collect {
            println(it)
        }
    }

}


fun printFruit(fruit: String) {
    println(fruit)
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
        (0..10).forEach {
            emit(it)
        }
    }
        .collect{
            delay(1000)
            println(it.toString())
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

