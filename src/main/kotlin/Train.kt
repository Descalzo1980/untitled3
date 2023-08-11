class Train: Moveable {
    override fun move() {
        super.move()
    }

    override fun info() {
        println("Закинул уголь и поехал")
    }

    override val speed : Double
        get() = 908.0
}