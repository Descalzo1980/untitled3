interface Storage<T> {
    fun get() : T
    fun add(item: T)
}