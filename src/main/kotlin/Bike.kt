class Bike() : Vehicle() {
    override fun move() {
        println("������� ������� ������")
    }

    override val numberOfWheels : Int
        get() = 2

    var name = "OLOLO"
}