package atomic

import kotlin.concurrent.thread

class AtomicCounterUnsafe {

    private var counter = 0

    fun increment() {
        synchronized(this) {
            counter++
        }
    }

    fun current(): Int = synchronized(this) {
        counter
    }

    /*    fun increment(){
        counter++
    }
    так будет выводить всякую фигню
    fun current(): Int = counter
    }*/
}

fun main(){
    val counter = AtomicCounterUnsafe()
    (1..100)
        .map {
            thread { repeat(1000){counter.increment()} }
        }
        .forEach { thread -> thread.join() }
    println(counter.current())
}