package lock

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread
import kotlin.concurrent.withLock

fun main() {
    var a = 0
    var b = 0

    val lock: Lock = ReentrantLock()
    val thread1 = thread{
        lock.withLock { a = 1 }
    }

    val thread2 = thread {
        lock.withLock { b = 1 }
    }
    thread1.join()
    thread2.join()
    lock.withLock {
        println(a)
        println(b)
    }
    if(lock.tryLock()){
        try {
            println("OLOLO")
        }finally {
            lock.unlock()
        }
    }else{
        println("OLOLO1")
    }
}