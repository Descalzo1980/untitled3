abstract class Vehicle() {
    abstract val numberOfWheels: Int

    fun info(){
        println("Number of wheels is $numberOfWheels")
    }

    abstract fun move()

    var a = 5

}