class Car(): Vehicle(),Moveable {
    override fun move() {
        println("Заводу движок")
        println("Начинаю движение")
    }
    override val numberOfWheels : Int
        get() = 4

    override val speed : Double
        get() = 8.0
}
