class Case<T, E>(var item: T,var item2: E): Storage<T> {

    override fun get() : T {
        return item
    }

    override fun add(item : T){
        this.item = item
    }

    override fun toString() : String {
        return "Case(item=$item, item2=$item2)"
    }

    fun get2(): E {
        return item2
    }

    fun set2(item2 : E){
        this.item2 = item2
    }
}