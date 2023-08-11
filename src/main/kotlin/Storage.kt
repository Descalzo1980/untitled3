interface Storage<T> {
    fun get()
    fun add()
    fun get() : T
    fun add(item: T)
}