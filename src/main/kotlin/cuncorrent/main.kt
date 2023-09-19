package cuncorrent

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger
import javax.management.loading.ClassLoaderRepository

@Volatile // �� ��������, ����� �������, ��������
//var counter = AtomicInteger(0)
var counter = 0
//val mutex = Mutex() // �� ��� ���, ���� ��������
suspend fun main() {

//    massiveRun {
//        mutex.withLock {
//            counter++
//        }
//    }
//    massiveRun { counter++ }
//    println(counter)

//    when_collected_flow_multiple_time_then_return_same_values()
//    tryChanel()
//    coldChanel()

//    flowAnd()
//    someFlow()
    someSharedFlow()
}

suspend fun massiveRun(action : suspend () -> Unit ){
    withContext(Dispatchers.Default){
        repeat(100){
            launch {
                repeat(100) {action()}
            }
        }
    }
}

fun when_collected_flow_multiple_time_then_return_same_values() = runBlocking {
    val coldStream = flow {
        for (i in 1..5) {
            delay(100L)
            emit(i)
        }
    }
    val collect1 = buildString {
        coldStream.collect { append(it).append(", ") }
    }.removeSuffix(", ")
    val collect2 = buildString {
        coldStream.collect { append(it).append(", ") }
    }.removeSuffix(", ")
    println(collect1)
    println(collect2)
}

suspend fun tryChanel(): Unit = coroutineScope{
    val chanel = Channel<Int>()

    launch {
        repeat(5){index ->
            val message = "Message $index"
            println("Message: $index")
        }
    }
    chanel.close()
}

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun coldChanel(): Unit = coroutineScope {
    val channel = produce {
        var count = 0
        while (true){
            val x = count++
            println("send: $x")
            send(x)
        }
    }
    launch {
        for (value in channel){
            println("receive: $value")
        }
    }

    val flow = flow<String> {
        while (true)
            emit("some value")
    }

    val chanel = produce<String> {
        while (true)
            send("some value")
    }

    flow{emit("ololo")}
        .onEach { println(it) }
        .onStart { println("doStart") }
        .onCompletion { println("onComplete") }
        .catch { emit("Error") }
        .collect{ println("collection") }



}

suspend fun flowAnd(): Unit = coroutineScope {
    val numberFlow = (1..5).asFlow()
    val numberFlow1 = (1..5).asFlow()
    val letter = flowOf("A", "B","C","D","E")
    val square = numberFlow.map { it * 2 }
    square.collect{
        println("��������� ����� $it")
    }

    val eventNum = numberFlow.filter { it % 2 == 0 }
    eventNum.collect{
        println("������ ������ $it")
    }

    val combine = numberFlow1.zip(letter){ number, letter ->
        "$number$letter"
    }
    combine.collect{ println("����� ������ $it") }
}

suspend fun someFlow(): Unit = coroutineScope {
    val sharedFlow  = flowOf("Message1")
    val sharedFlow1 = flowOf("Message2")

//    launch { sharedFlow.collect{ println("#1 $it") } }
//    launch { sharedFlow.collect{ println("#2 $it") } }
//
//    delay(1000)
//    sharedFlow.collect()
//    sharedFlow1.emit("Message2")
    launch {
        val result = sharedFlow.zip(sharedFlow1) { message , message1 ->
            "$message$message1"
        }
        result.collect { println("����� ������ $it") }
    }
}

suspend fun someSharedFlow(): Unit = coroutineScope {
    val state = MutableStateFlow("A")
    launch { state.collect { println("collect $it") } }

    delay(1000)
    state.value = "B"

    delay(1000)
    launch { state.collect { println("collect $it")  } }
}

/*Flow � Channel - ��� ��� ������ ������� � ������ � �������� ������ � Kotlin.

Flow - ��� ����������� ����� ������, ������� ����� ���� ��������� �
�������������. �� ������������ ����� ������������������ ��������,
������� ����� ���� ����������� ����������. Flow - ��� ����� ���������������
����������� ��� ������, ������� ������������� ������� API ��� ������ �
�������� ������. �� ����� ���� ����������� ��� ������ � �������� ������,
������� �� �������� ��������� ���������, ����� ��� ������ �� ���� ������
��� ���������� ������� ��������.

Channel - ��� �������� �������������, ������� ���������
���������� � �������� ������ ����� ����������. �� ������������ ���
������������� ���������� ������, ������� �������� ��������� ���������,
����� ��� ������ ������� ��� �������� ������� ����������. Channel - ���
�������� ������� ������, ������� ������������ ������������� ����� ����������.

� ����� ���� flow � produce
������� ����������� ����� ������ � ����� ��������������.
������, flow ������������ ����� ������������������ ��������,
������� ����� ���� ����������� ����������, ����� ��� produce
 ������������ ��� �������� � ��������� ������ ����� ����������.*/

//class StudentInfoViewModel(private val repository : StudentDataRepository){
//    private val _iuState = MutableStateFlow(UiState.Init)
//    val uiState: SharedFlow<UiState> = _iuState.asStateFlow
//
//    fun onCreate(){
//        viewModelScope.launch{
//            repository.studentData().collect { data ->
//                _iuState.update{data.toUiState}
//            }
//        }
//    }
//}