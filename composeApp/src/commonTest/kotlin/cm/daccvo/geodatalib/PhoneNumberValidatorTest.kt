package cm.daccvo.geodatalib

import cm.daccvo.geodatalib.internal.providePhoneNumberValidator
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PhoneNumberValidatorTest {

    private val validator = providePhoneNumberValidator()

    @Test
    fun testValidPhoneNumber() {
        val result = validator.isValid("+237690000000", "CM") // ✅ Cameroun
        println("le resultat est : $result")
        assertTrue(result, "Le numéro camerounais devrait être valide")
    }

    @Test
    fun testInvalidPhoneNumber() {
        val result = validator.isValid("123456", "CM")
        println("le resultat est : $result")
        assertFalse(result, "Ce numéro est invalide")
    }

}