class Bike() : Vehicle() {
    override fun move() {
        println("Начинаю крутить педали")
    }

    override val numberOfWheels : Int
        get() = 2

    var name = "OLOLO"
}