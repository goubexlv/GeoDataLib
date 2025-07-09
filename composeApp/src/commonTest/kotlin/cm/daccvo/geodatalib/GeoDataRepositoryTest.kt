package cm.daccvo.geodatalib

import cm.daccvo.geodatalib.repository.GeoDataRepositoryImpl
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue


class GeoDataRepositoryTest {

    private val repo = GeoDataRepositoryImpl()

    @Test
    fun testLoadCountries_returnsNonEmptyList() = runTest {
        val result = repo.loadCountries()
        assertTrue(result.isSuccess)
        val countries = result.getOrNull()
        assertNotNull(countries)
        assertTrue(countries.isNotEmpty())
    }

    @Test
    fun testLoadCountryDetail_returnsNonEmptyList() = runTest {
        val result = repo.loadCountriesDetail()
        if (result.isFailure) {
            println("Erreur lors du chargement des pays : ${result.exceptionOrNull()}")
        }
        assertTrue(result.isSuccess, "Expected result to be successful")

        val countries = result.getOrNull()
        assertNotNull(countries, "Expected countries list to not be null")
        assertTrue(countries.isNotEmpty(), "Expected countries list to not be empty")
    }



    @Test
    fun testSearchCountryByName_returnsMatchingResults() = runTest {
        val result = repo.searchCountryByName("Ca")

        assertTrue(result.isSuccess, "Expected a successful result")

        val countries = result.getOrNull()
        println("Test result countries: $countries")
        assertTrue(!countries.isNullOrEmpty(), "Expected non-empty result")

        // Vérifie que tous les pays trouvés contiennent "fra" (ex: "France")
        assertTrue(countries.all { it.name.contains("Ca", ignoreCase = true) }, "All results should contain 'fra'")
    }

    @Test
    fun testGetCitiesByCountryName_returnsCorrectCities() = runTest {
        val result = repo.getCitiesByCountryName("Cameroon")
        assertTrue(result.isSuccess, "Expected success")
        val cities = result.getOrNull()
        assertTrue(!cities.isNullOrEmpty(), "Expected non-empty list of cities")

        println("Test result cities: $cities") // <-- affichage volontaire du résultat filtré

        // Assertions
        assertTrue(cities.contains("Douala"), "Expected 'Douala' in cities")
        assertTrue(cities.contains("Yaoundé"), "Expected 'Yaoundé' in cities")
        assertTrue(cities.size < 1000, "Expected cities list to be reasonably sized")
    }




}