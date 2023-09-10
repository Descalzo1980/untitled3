package cuncorrent

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicInteger

@Volatile // не работает, нужны атомики, например
//var counter = AtomicInteger(0)
var counter = 0
//val mutex = Mutex() // ну или так, тоже работает
suspend fun main() {

//    massiveRun {
//        mutex.withLock {
//            counter++
//        }
//    }
    massiveRun { counter++ }
    println(counter)
}

suspend fun massiveRun(action : suspend () -> Unit ){
    withContext(Dispatchers.Default){
        repeat(100){
            launch {
                repeat(100) {action()}
            }
        }
    }
}