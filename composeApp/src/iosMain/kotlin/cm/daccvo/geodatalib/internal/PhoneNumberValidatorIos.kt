package cm.daccvo.geodatalib.internal

actual fun providePhoneNumberValidator(): PhoneNumberValidator = object : PhoneNumberValidator {
    override fun isValid(number: String, region: String): Boolean {
        // Ne fait rien, retourne false
        return false
    }
}