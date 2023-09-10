//package moskala
//
//import io.reactivex.Observable
//import io.reactivex.Single
//import io.reactivex.schedulers.Schedulers
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.runBlocking
//import java.io.ObjectInputFilter
//import kotlin.concurrent.thread
//import kotlin.coroutines.resume
//import kotlin.coroutines.resumeWithException
//import kotlin.coroutines.suspendCoroutine
////fun main() {
////    repeat(100_000) {
////        thread {
////            Thread.sleep(1000L)
////            print(".")
////        }
////    }
////}
//
//fun main() = runBlocking {
//    repeat(100_000) {
//        launch {
//            delay(1000L)
//            print(".")
//        }
//    }
//}
//fun main() {
////    makeHello("������, ���!") { text ->
////        maker(text)
//    }
//}
//
//fun makeHello(text: String, maker: (String) -> String): String {
//    return maker(text)
//}
//
//fun maker(text: String): String {
//    println(text)
//    return text
//}

//interface Call {
//    fun execute(message: String)
//}
//
//class Child : Call {
//    override fun execute(message: String) {
//        println("Child: ������ ��������� � ����������: $message")
//    }
//}
//
//class Parent : Call {
//    override fun execute(message: String) {
//        println("Parent: ������ ��������� � ����������: $message")
//    }
//}
//
//class TaskManager {
//    fun performTask(callback: Call, message: String) {
//        // ����� ����������� ��������� ������.
//        // ����� ���������� ������ �������� �������, ����� ��������� � ����������.
//        callback.execute(message)
//    }
//}
//
//fun main() {
//    val taskManager = TaskManager()
//
//    val childCallback = Child()
//    val parentCallback = Parent()
//
//    // ��������� ������ � �������������� �������� Child
//    taskManager.performTask(childCallback, "������, ��� ������ ��� �������!")
//
//    // ��������� ������ � �������������� �������� Parent
//    taskManager.performTask(parentCallback, "������, ��� ������ ��� ��������!")
//}

//typealias Call = (String) -> Unit
//
//fun childCallback(message: String) {
//    println("Child: ������ ��������� � ����������: $message")
//}
//
//fun parentCallback(message: String) {
//    println("Parent: ������ ��������� � ����������: $message")
//}
//
//class TaskManager {
//    fun performTask(callback: Call, message: String) {
//        // ����� ����������� ��������� ������.
//        // ����� ���������� ������ �������� �������, ����� ��������� � ����������.
//        callback(message)
//    }
//}
//
//fun main() {
//    val taskManager = TaskManager()
//
//    // ��������� ������ � �������������� ������� childCallback
//    taskManager.performTask(::childCallback, "������, ��� ������ ��� �������!")
//
//    // ��������� ������ � �������������� ������� parentCallback
//    taskManager.performTask(::parentCallback, "������, ��� ������ ��� ��������!")
//}

//
//interface Operation<T> {
//    fun performAsync(callback: (T?, Throwable?) -> Unit)
//}
//
//suspend fun <T> Operation<T>.perform(): T =
//    suspendCoroutine { continuation ->
//        performAsync { value, exception ->
//            when {
//                exception != null -> // ������ ��������
//                    continuation.resumeWithException(exception)
//                else -> // �������, ���� ��������
//                    continuation.resume(value as T)
//            }
//        }
//    }
//
//fun onCreate() {
//    disposables += getNewsFromApi()
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .map { news ->
//            news.sortedByDescending { it.publishedAt }
//        }
//        .subscribe { sortedNews ->
//            view.showNews(sortedNews)
//        }
//}
//
//fun onCreate() {
//    val news = getNewsFromApi()
//    val sortedNews = news
//        .sortedByDescending { it.publishedAt }
//    view.showNews(sortedNews)
//}

//data class News(
//    val title: String,
//    val description: String,
//    val date: String,
//    val source: String,
//    val imageUrl: String
//)
//
//fun getNewsFromApi(): Single<List<News>> {
//
//}

//fun showNews() {
//    disposables += Observable.zip(
//        getConfigFromApi().flatMap { getNewsFromApi(it) },
//        getUserFromApi(),
//            override fun invoke(
//                p1 : List<News> ,
//                p2 : ObjectInputFilter.Config
//            ) : Pair<List<News> , ObjectInputFilter.Config> {
//                return Pair(news , config)
//            }
//        })
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe { (news, config) ->
//            view.showNews(news, config)
//        }
//}




