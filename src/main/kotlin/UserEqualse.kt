data class UserEqualse(
    var firstName: String ,
    var passport: Int,
    var age: Int)
{
    override fun equals(other : Any?) : Boolean {
        if (this === other) return true
        if (other !is UserEqualse) return false

        if (firstName != other.firstName) return false
        if (passport != other.passport) return false
        if (age != other.age) return false

        return true
    }

    override fun hashCode() : Int {
        var result = firstName.hashCode()
        result = 31 * result + passport
        result = 31 * result + age
        return result
    }

}