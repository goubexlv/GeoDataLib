package cm.daccvo.geodatalib.internal

import com.google.i18n.phonenumbers.PhoneNumberUtil

class PhoneNumberValidatorAndroid : PhoneNumberValidator {
    override fun isValid(number: String, region: String): Boolean {
        return try {
            val phoneUtil = PhoneNumberUtil.getInstance()
            val phoneNumber = phoneUtil.parse(number, region)
            phoneUtil.isValidNumber(phoneNumber)
        } catch (e: Exception) {
            false
        }
    }
}

actual fun providePhoneNumberValidator(): PhoneNumberValidator = PhoneNumberValidatorAndroid()
