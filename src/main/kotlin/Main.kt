fun main(){
    val sum = { x: Int, y: Int -> x + y }
    printResult(2, 3, sum)

    loadDataFromServer { data ->
        println("Loaded data: $data")
    }

    println("Loading data...")

    val myClass = MyClass()
    println(myClass.sing)
    myClass.myAbstractMethod()
}

fun printResult(x: Int, y: Int, sum: (Int, Int) -> Int) {
    val result = sum(x, y)
    println("The sum of $x and $y is $result")
}

fun loadDataFromServer(callback: (List<String>) -> Unit) {
    // Simulate loading data from a remote server
    Thread.sleep(5000)

    // Return some dummy data
    val data = listOf("John", "Jane", "Bob", "Alice")

    // Invoke the callback function with the loaded data
    callback(data)
}

interface MyInterface {
    val property: Int
    val sing : Int
        get() = 42
}

abstract class MyAbstractClass {
    val property: Int = 42 // Абстрактные классы могут содержать инициализированные свойства
    abstract fun myAbstractMethod()
}

class MyClass : MyAbstractClass(), MyInterface {
    override fun myAbstractMethod() {
        println("Abstract method implementation")
    }
}

