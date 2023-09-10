package philosopher

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.concurrent.thread

class Philosopher(private val name: String, private val leftFork: Mutex, private val rightFork: Mutex) {
    suspend fun eat() {
        println("$name начал есть")
        leftFork.withLock {
            println("$name взял левую вилку")
            delay(100) // Философ ест
            rightFork.withLock {
                println("$name взял правую вилку и закончил есть")
            }
        }
    }

    suspend fun think() {
        println("$name начал размышлять")
        delay(200) // Философ размышляет
        println("$name закончил размышлять")
    }
}

fun main() = runBlocking {
    val philosophers = List(5) { index ->
        Philosopher("Философ $index", Mutex(), Mutex())
    }

    val jobs = philosophers.map { philosopher ->
        launch {
            repeat(3) {
                philosopher.think()
                philosopher.eat()
            }
        }
    }

    jobs.forEach { it.join() }
}
