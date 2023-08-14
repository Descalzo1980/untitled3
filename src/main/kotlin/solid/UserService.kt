package solid

import User


// Принцип единой ответственности (SRP)
//SRP гласит, что у класса должна быть только одна причина для изменения.
// В разработке для Android это означает, что у каждого класса должна
// быть одна обязанность или цель.


data class UserData(
    val userId : String
)

class  UserAuthenticationService {
    fun  authenticationUser (username: String , password: String ) {
        // Логика аутентификации
    }
}

class  UserDataService {
    fun  getUserData (userId: String ) : UserData {
        // Получение пользовательских данных с сервера
        return UserData(userId)
    }
}

//Принцип открытости-закрытости (OCP)
//Этот принцип гласит, что программные объекты (классы, модули, функции)
// должны быть открыты
// для расширения, но закрыты для модификации

/**
 * В примере OCP мы представили интерфейс PaymentProcessor,
 * который определяет processPayment()метод. Каждый платежный процессор
 * (кредитная карта, PayPal, биткойн) реализует этот интерфейс и предоставляет
 * свою конкретную реализацию. Это позволяет нам добавлять новые платежные
 * системы без изменения существующего кода.*/


data class Payment(
    val payment : String
)
interface  PaymentProcessor {
    fun  processPayment (payment: Payment )
}

class  CreditCardPaymentProcessor : PaymentProcessor {
    override  fun  processPayment (payment: Payment ) {
        // Код для обработки платежей кредитной картой
    }
}

class  PayPalPaymentProcessor : PaymentProcessor {
    override  fun  processPayment (payment: Payment ) {
        // Код для обработки платежей PayPal
    }
}

class  BitcoinPaymentProcessor : PaymentProcessor {
    override  fun  processPayment (payment: Payment ) {
        // Код для обработки биткойн-платежей
    }
}


/**
 *Принцип замещения Лискова (LSP) : этот принцип гласит,
 * что объекты суперкласса должны заменяться объектами его
 * подклассов, не влияя на корректность программы.
 * Это гарантирует правильную реализацию отношений наследования.
 * Придерживаясь принципа подстановки Лискова, мы гарантируем,
 * что отношения наследования реализованы должным образом, а объекты
 * подклассов могут беспрепятственно заменять
 * объекты суперкласса без неожиданного поведения или нарушения кода.
 * */

abstract class Shape {
    abstract fun calculateArea(): Double
}

class Rectangle(private val width: Double, private val height: Double) : Shape() {
    override fun calculateArea(): Double {
        return width * height
    }
}

class Circle(private val radius: Double) : Shape() {
    override fun calculateArea(): Double {
        return PI * radius * radius
    }

    companion object{
        private const val PI = 3.14
    }
}

fun printArea(shape: Shape) {
    println("Area: ${shape.calculateArea()}")
}

/**
 *Принцип разделения интерфейсов (ISP) . Этот принцип гласит,
 * что клиенты не должны зависеть от интерфейсов, которые
 * они не используют. Он продвигает идею иметь
 * меньшие и более конкретные интерфейсы, а не один большой интерфейс.
 * */

interface  Workable {
    fun  work ()
}

interface  Eatable {
    fun  eat ()
}

interface  Sleepable {
    fun  sleep ()
}

class  Developer : Workable , Eatable , Sleepable {
    override  fun  work () {
        // Код для работы разработчиком
    }

    override  fun  eat () {
        // Код для еды в качестве разработчика
    }

    override  fun sleep () {
        // Код для сна в качестве разработчика
    }
}

/**
 * Принцип инверсии зависимостей (DIP) : этот принцип гласит,
 * что модули высокого уровня не должны зависеть от модулей низкого уровня.
 * Оба должны зависеть от абстракций. Это способствует
 * слабой связи и облегчает модульное тестирование и ремонтопригодность.
 *
 * В примере с DIP UserService больше не создается экземпляр класса
 * UserRepository напрямую. Вместо этого он принимает экземпляр
 * UserRepository через свой конструктор, следуя принципу инверсии
 * зависимостей.*/

//No D
/*class UserService {
    private val userRepository = UserRepository()

    fun getUser(userId: String): User {
        return userRepository.getUser(userId)
    }
}*/

interface UserRepository {
    fun getUser(userId: String): User
}
class UserService(private val userRepository: UserRepository) {
    fun getUser(userId: String): User {
        return userRepository.getUser(userId)
    }
}