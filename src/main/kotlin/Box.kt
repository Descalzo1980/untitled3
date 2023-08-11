class Box<T>(private var item: T) {
    fun getItem(): T {
        return item
    }

    fun setItem(newItem: T) {
        item = newItem
    }

    override fun equals(other : Any?) : Boolean {
        return super.equals(other)
    }

    override fun hashCode() : Int {
        return super.hashCode()
    }

    override fun toString() : String {
        return "Box($item)"
    }
}