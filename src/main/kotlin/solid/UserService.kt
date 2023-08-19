package solid

import User


// ������� ������ ��������������� (SRP)
//SRP ������, ��� � ������ ������ ���� ������ ���� ������� ��� ���������.
// � ���������� ��� Android ��� ��������, ��� � ������� ������ ������
// ���� ���� ����������� ��� ����.


data class UserData(
    val userId : String
)

class  UserAuthenticationService {
    fun  authenticationUser (username: String , password: String ) {
        // ������ ��������������
    }
}

class  UserDataService {
    fun  getUserData (userId: String ) : UserData {
        // ��������� ���������������� ������ � �������
        return UserData(userId)
    }
}

//������� ����������-���������� (OCP)
//���� ������� ������, ��� ����������� ������� (������, ������, �������)
// ������ ���� �������
// ��� ����������, �� ������� ��� �����������

/**
 * � ������� OCP �� ����������� ��������� PaymentProcessor,
 * ������� ���������� processPayment()�����. ������ ��������� ���������
 * (��������� �����, PayPal, �������) ��������� ���� ��������� � �������������
 * ���� ���������� ����������. ��� ��������� ��� ��������� ����� ���������
 * ������� ��� ��������� ������������� ����.*/


data class Payment(
    val payment : String
)
interface  PaymentProcessor {
    fun  processPayment (payment: Payment )
}

class  CreditCardPaymentProcessor : PaymentProcessor {
    override  fun  processPayment (payment: Payment ) {
        // ��� ��� ��������� �������� ��������� ������
    }
}

class  PayPalPaymentProcessor : PaymentProcessor {
    override  fun  processPayment (payment: Payment ) {
        // ��� ��� ��������� �������� PayPal
    }
}

class  BitcoinPaymentProcessor : PaymentProcessor {
    override  fun  processPayment (payment: Payment ) {
        // ��� ��� ��������� �������-��������
    }
}


/**
 *������� ��������� ������� (LSP) : ���� ������� ������,
 * ��� ������� ����������� ������ ���������� ��������� ���
 * ����������, �� ����� �� ������������ ���������.
 * ��� ����������� ���������� ���������� ��������� ������������.
 * ������������� �������� ����������� �������, �� �����������,
 * ��� ��������� ������������ ����������� ������� �������, � �������
 * ���������� ����� ���������������� ��������
 * ������� ����������� ��� ������������ ��������� ��� ��������� ����.
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
 *������� ���������� ����������� (ISP) . ���� ������� ������,
 * ��� ������� �� ������ �������� �� �����������, �������
 * ��� �� ����������. �� ���������� ���� �����
 * ������� � ����� ���������� ����������, � �� ���� ������� ���������.
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
        // ��� ��� ������ �������������
    }

    override  fun  eat () {
        // ��� ��� ��� � �������� ������������
    }

    override  fun sleep () {
        // ��� ��� ��� � �������� ������������
    }
}

/**
 * ������� �������� ������������ (DIP) : ���� ������� ������,
 * ��� ������ �������� ������ �� ������ �������� �� ������� ������� ������.
 * ��� ������ �������� �� ����������. ��� ������������
 * ������ ����� � ��������� ��������� ������������ � ������������������.
 *
 * � ������� � DIP UserService ������ �� ��������� ��������� ������
 * UserRepository ��������. ������ ����� �� ��������� ���������
 * UserRepository ����� ���� �����������, ������ �������� ��������
 * ������������.*/

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