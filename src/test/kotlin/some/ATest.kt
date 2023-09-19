package some

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ATest {

    @Test
    fun show() {
        val a = A()
        val log = FakeLog.Base()
        a.show(log)
        log.checkLogCalledWidth("hello",2)
        log.checkLogCalledWidth("world",2)
    }
}

private interface FakeLog: Log{

    fun checkLogCalledWidth(arg : String, times: Int)

    class Base : FakeLog{

        private var count = 0

        private val logCalledWithArgsList = mutableListOf<String>()

        override fun checkLogCalledWidth(arg : String, times : Int) {
            assertEquals(times, logCalledWithArgsList.size)
            assertEquals(arg, logCalledWithArgsList[count++])
        }

        override fun log(arg : String) {
            logCalledWithArgsList.add(arg)
        }

    }
}