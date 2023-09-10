package atomic

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.CopyOnWriteArraySet
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

class AtomicCounterUnsafe {

    private var counter = 0

    private var atomicCounter = AtomicInteger(0)

    fun increment(){
        atomicCounter.incrementAndGet()
    }

    fun current(): Int = atomicCounter.get()

//    fun increment() {
//        synchronized(this) {
//            counter++
//        }
//    }
//
//    fun current(): Int = synchronized(this) {
//        counter
//    }

//    fun increment(){
//        counter++
//    }
//    так будет выводить всякую фигню
//    fun current(): Int = counter
}


fun main(){
    val counter = AtomicCounterUnsafe()

    val concurrentQueue = ConcurrentLinkedQueue<String>()

    val concurrentMap = ConcurrentHashMap<String, Int>()

    val copy = CopyOnWriteArrayList<String>()

    val set = CopyOnWriteArraySet<String>()


    (1..100)
        .map {
            thread { repeat(1000){counter.increment()} }
        }
        .forEach { thread -> thread.join() }
    println(counter.current())
}


/*
CountDownLatch
CountDownLatch предоставляет возможность одному
или нескольким потокам ждать, пока определенное количество
операций завершится, прежде чем они продолжат выполнение. Вот пример:
fun main() {
    val latch = CountDownLatch(3)

    val thread1 = Thread {
        Thread.sleep(1000)
        println("Thread 1 is done")
        latch.countDown()
    }

    val thread2 = Thread {
        Thread.sleep(2000)
        println("Thread 2 is done")
        latch.countDown()
    }

    val thread3 = Thread {
        Thread.sleep(1500)
        println("Thread 3 is done")
        latch.countDown()
    }

    thread1.start()
    thread2.start()
    thread3.start()

    latch.await()

    println("All threads are done")
}
*/


/*CyclicBarrier позволяет нескольким потокам собираться в одной
точке и продолжать выполнение
только после того, как все потоки достигнут этой точки. Вот пример:
import java.util.concurrent.CyclicBarrier

fun main() {
    val barrier = CyclicBarrier(3)

    val thread1 = Thread {
        println("Thread 1 is waiting")
        barrier.await()
        println("Thread 1 is done")
    }

    val thread2 = Thread {
        println("Thread 2 is waiting")
        barrier.await()
        println("Thread 2 is done")
    }

    val thread3 = Thread {
        println("Thread 3 is waiting")
        barrier.await()
        println("Thread 3 is done")
    }

    thread1.start()
    thread2.start()
    thread3.start()
}

*/


/*
*
* Semaphore позволяет ограничивать доступ к ресурсам или
* критическим секциям определенным числом потоков. Вот пример:
*
* import java.util.concurrent.Semaphore

fun main() {
    val semaphore = Semaphore(2)
    * // Разрешаем двум потокам одновременный доступ

    val thread1 = Thread {
        semaphore.acquire()
        println("Thread 1 is in a critical section")
        Thread.sleep(2000)
        println("Thread 1 exits the critical section")
        semaphore.release()
    }

    val thread2 = Thread {
        semaphore.acquire()
        println("Thread 2 is in a critical section")
        Thread.sleep(1000)
        println("Thread 2 exits the critical section")
        semaphore.release()
    }

    val thread3 = Thread {
        semaphore.acquire()
        println("Thread 3 is in a critical section")
        Thread.sleep(1500)
        println("Thread 3 exits the critical section")
        semaphore.release()
    }

    thread1.start()
    thread2.start()
    thread3.start()
}

* */
