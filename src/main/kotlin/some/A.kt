package some

class A {

    fun show(b: Log){
        b.log("hello")
        b.log("world")
    }
}
interface Log{
    fun log(arg : String)
}
class B: Log{

    override fun log(arg: String){
        println(arg)
    }

    fun printRemote(){
        throw IllegalStateException("no remote")
    }
}

class C{
    fun tryRemote(b: B){
        b.printRemote()
    }
}