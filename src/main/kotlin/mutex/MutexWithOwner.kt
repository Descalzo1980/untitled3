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

    fun isLockedByCurrentThread(): Boolean {
        return owner == Thread.currentThread()
    }
}

fun main() {
    val customMutex = MutexWithOwner()

    runBlocking {
        customMutex.lock()
        println("����� 1 ������� �������")
        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
        }
        customMutex.unlock()
        println("����� 1 ��������� �������")
    }

    runBlocking {
        withContext(Dispatchers.IO) {
            Thread.sleep(500)
        }
        customMutex.lock()
        println("����� 2 ������� �������")
        customMutex.unlock()
        println("����� 2 ��������� �������")
    }
}
