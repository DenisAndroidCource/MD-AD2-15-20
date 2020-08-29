package by.it.academy.kotlinexample

class Admin(
        private var accessCode: String,
        name: String,
        email: String,
        phone: String,
        private val surname: String
) : AccessController, UserInfo(name, email, phone, surname) {
    override fun hasAccess(): Boolean {
        return true
    }
}