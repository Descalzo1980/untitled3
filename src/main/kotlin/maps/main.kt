package maps

//    val passportNumbers = listOf("QW111","AA444","KL567")
//    val personsPassword = listOf("Ivan Ivanov","Boris Grebenshchikov", "Petr Petrov","Arnold Schwarzenegger")
//fun main(){
//      println(getPersonByPasswordNumber("KL567"))
//}
//
//fun getPersonByPasswordNumber(number : String): String{
//    val indexOf = passportNumbers.indexOf(number)
//    val person = personsPassword[indexOf]
//    return person
//}

fun main(){
//    val password = mapOf<String, String>(
//        "Ivan Ivanov" to "QW111",
//        "Boris Grebenshchikov" to "KL567",
//        "Petr Petrov" to "AA444"
//    )
//    val person = password["Boris Grebenshchikov"]
//    println(person)
//
//    val telephones = mapOf(
//        12345 to 124.65,
//        12346 to 134.65,
//        12347 to 144.65,
//        12348 to 154.65,
//    )
//    println(telephones.entries)

//    val books = mapOf(
//        "Ivan Ivanov" to listOf("Book1", "Book2"),
//        "Boris Grebenshchikov" to listOf("Book3", "Book4"),
//        "Boris Grebenshchikov" to listOf("Book3", "Book4"),
//    )
//    val allBooks = books.flatMap { (_, bookList) ->
//        bookList
//    }
//    println(books)

    val books = mutableMapOf(
        "123" to "OLOLO",
        "12345" to "OLOLO12345",
    )

//    println(books)
//    println(books.values)
//    println(books.entries)
//    println(books.keys)
//    println(books.size
//    books["12345"] = "Stas"
//    println(books["12345"])
//    books.replace("12345", "Stas", "Sergey")
//    println(books["12345"])

    for (i in books)
        println("Это key${i.key}, Это value${i.value}")


}

fun <K, V> mapOf(vararg pairs: Pair<K, V>): Map<K, V> {
    val map = mutableMapOf<K, V>()
    for ((key, value) in pairs) {
        map[key] = value
    }
    return map
}




