package mutex

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.withContext

class MutexWithOwner {
    private val mutex = Mutex()
    private var owner: Thread? = null

    suspend fun lock() {
        mutex.lock()
        owner = Thread.currentThread()
    }

    fun unlock() {
        if (isLockedByCurrentThread()) {
            owner = null
            mutex.unlock()
        } else {
            throw IllegalStateException("������� �� ������������ ������� �������")
        }
    }

    fun isLockedByCurrentThread() : Boolean {
        return owner == Thread.currentThread()
    }
}

fun main() = runBlocking {
    val customMutex = MutexWithOwner()
    val job1 = CoroutineScope(Dispatchers.Default).launch {
        customMutex.lock()
        println("����� 1 ������� �������")
        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
        }
        customMutex.unlock()
        println("����� 1 ��������� �������")
    }

    val job2 = CoroutineScope(Dispatchers.Default).launch {
        withContext(Dispatchers.IO) {
            Thread.sleep(500)
        }
        customMutex.lock()
        println("����� 2 ������� �������")
        customMutex.unlock()
        println("����� 2 ��������� �������")
    }

    customMutex.start()
    customMutex.doLogic()

    job1.join()
    job2.join()



}
