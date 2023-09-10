package dsl

class BodyMyElement(private val name: String, private val content: String) {
    override fun toString(): String {
        return "<$name>$content<$name>"
    }
}

class BodyBody {
    private val element = mutableListOf<BodyMyElement>()

    fun h1(text: String) {
        element.add(BodyMyElement("h1", text))
    }

    fun p(text: String) {
        element.add(BodyMyElement("p", text))
    }

    fun title(title: String) {
        element.add(BodyMyElement("title", title))
    }

    override fun toString() : String {
        return "<body>\n${element.joinToString("\n")}\n</body>\n"
    }
}

class MyHTML() {
    private val myHTML = BodyBody()

    fun myHead(init: BodyBody.() -> Unit) {
        myHTML.init()
    }

    override fun toString(): String {
        return myHTML.toString()
    }
}

fun main() {
    val myHTML = MyHTML()
    myHTML.myHead {
        title("Пример HTML-документа на Kotlin DSL")
    }

    myHTML.myHead {
        h1("Заголовок Н1")
    }

    myHTML.myHead {
        p("Параграф")
    }

    val result = myHTML.toString()
    println(result)
}
