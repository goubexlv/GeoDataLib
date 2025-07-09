package cm.daccvo.geodatalib.repository

import cm.daccvo.geodatalib.model.Country
import cm.daccvo.geodatalib.model.CountryDetailed

interface GeoDataRepository {
    suspend fun loadCountries(): Result<List<Country>>
    suspend fun loadCountriesDetail(): Result<List<CountryDetailed>>

    suspend fun searchCountryByName(query: String): Result<List<Country>>
    suspend fun searchCountryDetailByName(query: String): Result<List<CountryDetailed>>
    suspend fun getCitiesByCountryName(name: String): Result<List<String>>
    suspend fun getAllCities(): Result<List<String>>

}