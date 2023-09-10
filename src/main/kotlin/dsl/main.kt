package dsl

class HTML {
    private val head = Head()
    private val body = Body()

    fun head(init: Head.() -> Unit) {
        head.init()
    }

    fun body(init: Body.() -> Unit) {
        body.init()
    }

    override fun toString(): String {
        return "<html>\n$head$body</html>"
    }
}

class Head {
    private val elements = mutableListOf<HeadElement>()

    fun title(title: String) {
        elements.add(HeadElement("title", title))
    }

    override fun toString(): String {
        return "<head>\n${elements.joinToString("\n")}\n</head>\n"
    }
}

class HeadElement(private val name: String, private val content: String) {
    override fun toString(): String {
        return "<$name>$content</$name>"
    }
}

class Body {
    private val elements = mutableListOf<BodyElement>()

    fun h1(text: String) {
        elements.add(BodyElement("h1", text))
    }

    fun p(text: String) {
        elements.add(BodyElement("p", text))
    }

    override fun toString(): String {
        return "<body>\n${elements.joinToString("\n")}\n</body>\n"
    }
}

class BodyElement(private val name: String, private val content: String) {
    override fun toString(): String {
        return "<$name>$content</$name>"
    }
}

fun main() {
    val htmlDoc = HTML()
    htmlDoc.head {
        title("Пример HTML-документа на Kotlin DSL")
    }
    htmlDoc.body {
        h1("Заголовок")
        p("Это абзац текста.")
    }

    val result = htmlDoc.toString()
    println(result)
}


