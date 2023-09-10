package executer

import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope.coroutineContext
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future
import kotlin.RuntimeException
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


fun main(){
//    val executorService: ExecutorService = Executors.newSingleThreadExecutor()
//    val future: Future<*> = executorService.submit {
//        Callable{ "Asinc task" }
//    }
//    println(future.get())

//    val scope = CoroutineScope(EmptyCoroutineContext)
//    val job: Job = scope.launch {
////        ololo()
////        aboutJob()
//    superVision()
//    runBlocking {
//        superVision()
//    }
    runBlocking {
        val handler = CoroutineExceptionHandler{_,exeptions ->
            println("поймал сома")
        }

        val scope = CoroutineScope(SupervisorJob() + handler)
        scope.launch {
            delay(1000)
            throw RuntimeException("Exceptions!")
        }
        scope.launch {
            delay(2000)
            println("Отпустил сома")
        }
        delay(2000)
    }
}

suspend fun ololo(): Unit = coroutineScope{
    val job1 = launch {
        delay(1000)
        println("Hello job 1")
    }
    val job2 = launch {
        delay(2000)
        println("Hello job2")
    }
    job1.join()
    job2.join()
    println(job1.isActive)
    println(job2.isCompleted)
    checkCoroutineName()

}

fun checkCoroutineName(){
    val context: CoroutineContext = CoroutineName("A name")
    val coroutineName : CoroutineName? = context[CoroutineName]
    val job: Job? = context[Job]

    println(coroutineName)
    println(job)
}

suspend fun aboutJob(): Unit = coroutineScope {
    val name = CoroutineName("Some name")
    val job = Job()
    val result: Deferred<Unit> = async {
        delay(2000)
        println("async result")
    }
    delay(1000)
    result.await()
    result.cancel()
//    launch ( name + job) {
//        val childName = coroutineContext(CoroutineName)
//        println(childName == name)
//    }
}

suspend fun superVision(): Unit = coroutineScope {
    val sharedJob =  SupervisorJob()
    val job1 = launch(sharedJob) {
        delay(1000)
        throw RuntimeException()
    }
    val job2 = launch(sharedJob) {
        delay(2000)
        println("А это напечатается")
    }

    job1.join()
    job2.join()

}