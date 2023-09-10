package sealed

sealed class State {
    data object Initial : State()
    data object Running : State()
    data object Paused : State()
    data object Finished : State()
}

fun handleState(state: State) {
    when(state) {
        is State.Initial -> println("The game is starting...")
        is State.Running -> println("The game is running...")
        is State.Paused -> println("The game is paused...")
        is State.Finished -> println("The game is finished!")
    }
}

sealed class Result {
    data class Success(val data: String) : Result()
    data class Error(val message: String) : Result()
}

fun handleResult(result: Result) {
    when(result) {
        is Result.Success -> println(result.data)
        is Result.Error -> println(result.message)
    }
}

sealed class StateMachine {
    data object Initial : StateMachine()
    data object Running : StateMachine()
    data object Paused : StateMachine()
    data object Finished : StateMachine()
}

fun handleStateMachine(state: StateMachine){
    when(state){
        is StateMachine.Initial -> println("The game is starting...")
        is StateMachine.Running -> println("The game is running...")
        is StateMachine.Paused -> println("The game is paused...")
        is StateMachine.Finished -> println("The game is finished!")
    }
}

sealed class ViewState {
    data class Loading (val loading: Boolean): ViewState()
    data class Success(val data: List<String>) : ViewState()
    data class Error(val message: String) : ViewState()
}

fun handleViewState(viewState: ViewState) {
    when(viewState) {
        is ViewState.Loading -> showLoadingIndicator(viewState.loading)
        is ViewState.Success -> showData(viewState.data)
        is ViewState.Error -> showError(viewState.message)
    }
}

private fun showLoadingIndicator(isLoading: Boolean) {
    if (isLoading) {
        println("Loading...")
    } else {
        println("Loading finished.")
    }
}
private fun showData(data: List<String>) {
    println("Data: $data")
}
private fun showError(message: String) {
    println("Error: $message")
}

/** Factory
 * Фабричный шаблон используется для создания объектов без раскрытия логики создания экземпляров клиенту.
 * Запечатанные классы можно использовать для определения различных типов объектов, которые будет создавать фабрика.
 * */

sealed class Product {
    abstract fun getDescription(): String
}

data object ConcreteProductA : Product() {
    override fun getDescription() : String {
        return "This is product A"
    }
}

data object ConcreteProductB : Product() {
    override fun getDescription() : String {
        return "This is product B"
    }
}

object ProductFactory {
    fun createProduct(type: String): Product {
        return when(type) {
            "A" -> ConcreteProductA
            "B" -> ConcreteProductB
            else -> throw IllegalArgumentException("Unknown product type")
        }
    }
}


/**Factory method
 * Шаблон фабричного метода используется для создания объектов без указания их конкретных типов.
 * Запечатанные классы можно использовать для определения типов создаваемых объектов.
 * */

sealed class Animal {
    abstract fun makeSound()
}

data object Dog : Animal() {
    override fun makeSound() {
        println("Woof!")
    }
}

data object Cat : Animal() {
    override fun makeSound() {
        println("Meow!")
    }
}

object AnimalFactory {
    fun createAnimal(type: String): Animal? {
        return when (type) {
            "dog" -> Dog
            "cat" -> Cat
            else -> null
        }
    }
}

/**Strategy pattern
 * Шаблон стратегии используется для выбора алгоритма во время выполнения.
 * Запечатанные классы можно использовать для определения различных стратегий,
 * которые можно выбрать.
 * */

sealed class SortingStrategy {
    abstract fun sort(array: IntArray)
}

data object BubbleSort : SortingStrategy() {
    override fun sort(array: IntArray) {
        // implementation of bubble sort
    }
}

data object MergeSort : SortingStrategy() {
    override fun sort(array: IntArray) {
        // implementation of merge sort
    }
}

class Sorter(private var strategy: SortingStrategy) {
    fun setStrategy(strategy: SortingStrategy) {
        this.strategy = strategy
    }

    fun sort(array: IntArray) {
        strategy.sort(array)
    }
}
/** Шаблон декоратора
 * Шаблон декоратора используется для динамического добавления функциональности объекту без изменения его структуры.
 * Запечатанные классы можно использовать для определения базового объекта и его классов-декораторов.*/


sealed class Coffee {
    abstract fun getCost(): Double
    abstract fun getDescription(): String
}

data object BasicCoffee : Coffee() {
    override fun getCost() : Double {
        return 2.0
    }

    override fun getDescription() : String {
        return "Basic coffee"
    }
}

abstract class CoffeeDecorator(val coffee: Coffee) : Coffee()

class Milk(coffee: Coffee) : CoffeeDecorator(coffee) {
    override fun getCost(): Double {
        return coffee.getCost() + 0.5
    }

    override fun getDescription(): String {
        return coffee.getDescription() + ", with milk"
    }
}

class Sugar(coffee: Coffee) : CoffeeDecorator(coffee) {
    override fun getCost(): Double {
        return coffee.getCost() + 0.25
    }

    override fun getDescription(): String {
        return coffee.getDescription() + ", with sugar"
    }
}


fun main(){
    val runningState = State.Running
    handleState(runningState)

    val successResult: Result = Result.Success("Data retrieved successfully")
    val errorResult: Result = Result.Error("An error occurred")
    handleResult(successResult)
    handleResult(errorResult)

    val initialStateMachine: StateMachine = StateMachine.Initial
    val runningStateMachine: StateMachine = StateMachine.Running
    val pausedStateMachine: StateMachine = StateMachine.Paused
    val finishedStateMachine: StateMachine = StateMachine.Finished
    handleStateMachine(initialStateMachine)
    handleStateMachine(runningStateMachine)
    handleStateMachine(pausedStateMachine)
    handleStateMachine(finishedStateMachine)


    val loadingState: ViewState = ViewState.Loading(true)
    val successState: ViewState = ViewState.Success(listOf("ololo", "ololo"))
    val errorState: ViewState = ViewState.Error("An error occurred")

    handleViewState(loadingState)
    handleViewState(successState)
    handleViewState(errorState)


    val productA: Product = ProductFactory.createProduct("A")
    val productB: Product = ProductFactory.createProduct("B")

    println(productA.getDescription())
    println(productB.getDescription())

    val dog = AnimalFactory.createAnimal("dog") // Dog
    val cat = AnimalFactory.createAnimal("cat") // Cat
    val rabbit = AnimalFactory.createAnimal("rabbit") // null
    dog?.makeSound()
    cat?.makeSound()
    rabbit?.makeSound()

    val arrayToSort = intArrayOf(5, 2, 8, 1, 3)

    val bubbleSorter = Sorter(BubbleSort)
    println("Using Bubble Sort:")
    bubbleSorter.sort(arrayToSort)
    println(arrayToSort.contentToString())

    val mergeSorter = Sorter(MergeSort)
    println("Using Merge Sort:")
    mergeSorter.sort(arrayToSort)
    println(arrayToSort.contentToString())


    val basicCoffee = BasicCoffee
    val coffeeWithMilk = Milk(basicCoffee)
    val coffeeWithSugar = Sugar(basicCoffee)
    val coffeeWithMilkAndSugar = Sugar(Milk(basicCoffee))
}
