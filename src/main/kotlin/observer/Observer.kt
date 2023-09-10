package observer

interface Observer {
    fun onChange()
}

interface Observable{
    fun registerObserver(observer : Observer)
    fun unregisterObserver(observer : Observer)
    fun notifyObserver()
}

class ObservableUnsafe: Observable{
    private val observers: MutableSet<Observer> = mutableSetOf()
    override fun registerObserver(observer : Observer) {
        observers.add(observer)
    }

    override fun unregisterObserver(observer : Observer) {
        observers.remove(observer)
    }

    override fun notifyObserver() {
        observers.forEach { observer -> observer.onChange() }
    }
}

class ObservableSafe: Observable{
    private val lock : Any = Any()
    private val observers: MutableSet<Observer> = mutableSetOf()

    override fun registerObserver(observer : Observer) {
        synchronized(lock){
            observers.add(observer)
        }
    }

    override fun unregisterObserver(observer : Observer) {
        synchronized(lock){
            observers.remove(observer)
        }
    }

    override fun notifyObserver() {
        val observersCopy = synchronized(lock){
            observers.toList()
        }
        observersCopy.forEach{observer -> observer.onChange()}
    }
}

fun main() {
    val observable = ObservableSafe()

    val observer1 = object : Observer {
        override fun onChange() {
            println("Ќаблюдатель 1 получил уведомление об изменении")
        }
    }
    val observer2 = object : Observer {
        override fun onChange() {
            println("Ќаблюдатель 2 получил уведомление об изменении")
        }
    }
    observable.registerObserver(observer1)
    observable.registerObserver(observer2)
    observable.notifyObserver()
    observable.unregisterObserver(observer1)
    observable.notifyObserver()
}