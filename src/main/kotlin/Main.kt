fun main() {
    val result = arrayOf("a", "b", "c")
    result.swap(1,2)
    result.print()
    val dog = Dog()
    dog.sleep()
    dog.bark()

}
class Dog{
    fun bark(){
        println("Woff")
    }
}

fun Dog.sleep(){
    println("May")
}

fun Dog.bark(){
    println("OLOLO")
}
fun String.getSecond(abc: String): Char{
    return abc[3]
}

fun String.getSecond(): Char{
    return this[3]
}

fun <T> Array<T>.swap(first: Int, second: Int){
    val index1 = this[first]
    val index2 = this[second]
    this[first] = index2
    this[second] = index1
}

fun <T> Array<T>.print(){
    for (i in this){
        println(i)
    }
}