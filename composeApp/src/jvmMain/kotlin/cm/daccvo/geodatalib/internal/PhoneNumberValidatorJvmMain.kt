package cm.daccvo.geodatalib.internal

import com.google.i18n.phonenumbers.PhoneNumberUtil

actual fun providePhoneNumberValidator(): PhoneNumberValidator = PhoneNumberValidatorAndroid()


class PhoneNumberValidatorAndroid : PhoneNumberValidator {
    private val phoneUtil = PhoneNumberUtil.getInstance()

    override fun isValid(number: String, region: String): Boolean {
        return try {
            val parsed = phoneUtil.parse(number, region)
            phoneUtil.isValidNumberForRegion(parsed, region)
        } catch (e: Exception) {
            false
        }
    }
}