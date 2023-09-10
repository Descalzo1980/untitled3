package fibonacci

import io.reactivex.Observable
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.math.BigInteger
import java.util.concurrent.Executors
import kotlin.concurrent.thread
import kotlin.random.Random

val mutex = Mutex()
suspend fun tryMuetx(){
    val job1 = CoroutineScope(Dispatchers.Default).launch {
        mutex.withLock {
            println("Job 1 ����� � ����������� ������")
            delay(1000) // �������� 1 �������
            println("Job 1 ����� �� ����������� ������")
        }
    }
    val job2 = CoroutineScope(Dispatchers.Default).launch {
        delay(500) // �������� ����������, ����� job1 ����� � ����������� ������
        mutex.withLock {
            println("Job 2 ����� � ����������� ������")
            delay(1000) // �������� 1 �������
            println("Job 2 ����� �� ����������� ������")
        }
    }

    job1.join()
    job2.join()
}
fun main() {

    val lock = Any()
    runBlocking {
        tryMuetx()
    }


// ������ �����
    Thread {
        synchronized(lock) {
            println("������ ����� ����� � ����������� ������")
            Thread.sleep(2000)
            println("������ ����� ����� �� ����������� ������")
        }
    }.start()

// ������ �����
    Thread {
        Thread.sleep(1000)
        synchronized(lock) {
            println("������ ����� ����� � ����������� ������")
            Thread.sleep(2000)
            println("������ ����� ����� �� ����������� ������")
        }
    }.start()


//    var a = 0
//    var b = 0
//
//    val thread1 = thread {
//        a = 1
//        println(b)
//    }
//
//    val thread2 = thread {
//        b = 1
//        println(a)
//    }
//    val fibonacci : Sequence<BigInteger> = sequence {
//        var first = 0.toBigInteger()
//        var second = 1.toBigInteger()
//        while (true) {
//            yield(first)
//            val temp = first
//            first += second
//            second = temp
//        }
//    }
//    println(fibonacci.take(100).toList())
//    randomNumbers().take(10).forEach { println(it) }
//    randomUniqueStrings(5).take(10).forEach { println(it) }

//    val thread = thread {
//        Thread.sleep(2000)
//        println("end of thread")
//    }
//    thread.join()
//    println("end of main")

//    val thread1 = thread(name = "thread1"){
//        Thread.sleep(1000)
//        println("end of thread: ${Thread.currentThread().name}")
//    }
//
//    val thread2 = thread(name = "thread2"){
//        Thread.sleep(2000)
//        println("end of thread: ${Thread.currentThread().name}")
//    }
//    thread1.join()
//    thread2.join()
//    println("end of main")
}
//    val thread = thread {
//        repeat(100){
//            val thread = Thread.currentThread()
//            if(!thread.isInterrupted){
//                println("�������� $it")
//                Thread.sleep(3000)
//            }else{
//                println("����� ������� �� �������� $it")
//                Thread.currentThread().interrupt()
//            }
//        }
//
//    }
//    thread.interrupt()

//    val thread = Thread{
//        println("Hello")
//        val threadName = Thread.currentThread().name
//        println("Hello from: $threadName")
//    }
//    thread.start()

//    val kotlinThread = thread{
//        val threadNameKotlin = Thread.currentThread().name
//        println("Hello from kotlin $threadNameKotlin")
//    }
//    kotlinThread.interrupt()
//    kotlinThread.stop()
//
//    val executor = Executors.newFixedThreadPool(2) // �������� ���� �������
//    executor.submit {
//        println("Hello from executor $executor")
//    }
//    executor.shutdown()
//
//    val observable = Observable.create<Int> { emitter ->
//        println("Hello from emitter $emitter")
//    }
//    observable.subscribe { data ->
//        println("Hello from emitter $data")
//    }
//
//    val threadSimple = SimpleThread()
//    threadSimple.start()
//
//    val threadWithRunnable = Thread(SimpleRunnable())
//    threadWithRunnable.start()
//
//}
//class SimpleThread: Thread() {
//    public override fun run() {
//        println("${currentThread()} has run.")
//    }
//}
//
//class SimpleRunnable: Runnable {
//    public override fun run() {
//        println("${Thread.currentThread()} has run.")
//    }
//}
//fun randomNumbers(
//    seed: Long = System.currentTimeMillis()
//): Sequence<Int> = sequence {
//    val random = Random(seed)
//    while (true) {
//        yield(random.nextInt())
//    }
//}
//
//fun randomUniqueStrings(
//    length: Int,
//    seed: Long = System.currentTimeMillis()
//): Sequence<String> = sequence {
//    val random = Random(seed)
//    val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9')
//    while (true) {
//        val randomString = (1..length)
//            .map { i -> random.nextInt(charPool.size) }
//            .map(charPool::get)
//            .joinToString("");
//        yield(randomString)
//    }
//}.distinct()


