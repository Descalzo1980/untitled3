package philosopher

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.concurrent.thread

class Philosopher(private val name: String, private val leftFork: Mutex, private val rightFork: Mutex) {
    suspend fun eat() {
        println("$name ����� ����")
        leftFork.withLock {
            println("$name ���� ����� �����")
            delay(100) // ������� ���
            rightFork.withLock {
                println("$name ���� ������ ����� � �������� ����")
            }
        }
    }

    suspend fun think() {
        println("$name ����� ����������")
        delay(200) // ������� ����������
        println("$name �������� ����������")
    }
}

fun main() = runBlocking {
    val philosophers = List(5) { index ->
        Philosopher("������� $index", Mutex(), Mutex())
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
