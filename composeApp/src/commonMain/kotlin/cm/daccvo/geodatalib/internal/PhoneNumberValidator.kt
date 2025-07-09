package cm.daccvo.geodatalib.internal

interface PhoneNumberValidator {
    fun isValid(number: String, region: String): Boolean
}

expect fun providePhoneNumberValidator(): PhoneNumberValidator
