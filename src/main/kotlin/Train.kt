class Train: Moveable {
    override fun move() {
        super.move()
    }

    override fun info() {
        println("������� ����� � ������")
    }

    override val speed : Double
        get() = 908.0
}