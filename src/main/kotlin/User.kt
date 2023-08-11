class User(var firstName: String,var second: String,var age: Int) {
    override fun toString() : String {
        return "User [First name $firstName, Last name $second, Age $age]"
    }

    var speakerVolume = 2

    fun age(){
        println("Age = $age")
    }

    fun born() {

    }

    fun allName(): String{
        return "$firstName + $second"
    }

    override fun equals(other : Any?) : Boolean {
        if (this === other) return true
        if (other !is User) return false

        if (firstName != other.firstName) return false
        if (second != other.second) return false
        if (age != other.age) return false

        return true
    }

    override fun hashCode() : Int {
        var result = firstName.hashCode()
        result = 31 * result + second.hashCode()
        result = 31 * result + age
        return result
    }
}