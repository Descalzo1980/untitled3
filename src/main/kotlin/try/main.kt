package `try`

import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
    val handler = CoroutineExceptionHandler{_, exception ->
    println("поймал ошибку: $exception")
}
    val scope = CoroutineScope(SupervisorJob() + handler)
    scope.launch {
        delay(100)
        throw RuntimeException("Exception")
    }
    scope.launch {
        delay(200)
        println("Result")
    }
    delay(300)
}