package cm.daccvo.geodatalib.repository

import cm.daccvo.geodatalib.internal.readResourceText
import cm.daccvo.geodatalib.model.Country
import cm.daccvo.geodatalib.model.CountryDetailed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


internal class GeoDataRepositoryImpl : GeoDataRepository {

    private var cache: List<Country>? = null
    private var cacheCountry: List<CountryDetailed>? = null

    override suspend fun loadCountries(): Result<List<Country>> = withContext(Dispatchers.Default) {
        cache?.let { return@withContext Result.success(it) }

        runCatching {
            val json = readResourceText("/countries+cities.json")
                ?: error("countries+cities.json not found")
            Json { ignoreUnknownKeys = true }.decodeFromString<List<Country>>(json)
                .also { cache = it }
        }
    }

    override suspend fun loadCountriesDetail(): Result<List<CountryDetailed>> = withContext(Dispatchers.Default) {
        cacheCountry?.let { return@withContext Result.success(it) }

        runCatching {
            val json = readResourceText("/countries.json")
                ?: error("countries.json not found")
            Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }.decodeFromString<List<CountryDetailed>>(json)
                .also { cacheCountry = it }
        }
    }

    override suspend fun searchCountryByName(query: String): Result<List<Country>> {
        return loadCountries().map { list ->
            list.filter { it.name.contains(query, ignoreCase = true) }
        }
    }

    override suspend fun searchCountryDetailByName(query: String): Result<List<CountryDetailed>> {
        return loadCountriesDetail().map { list ->
            list.filter { it.name.contains(query, ignoreCase = true) }
        }
    }

    override suspend fun getAllCities(): Result<List<String>> {
        return loadCountries().map { list ->
            list.flatMap { it.cities }
        }
    }

    override suspend fun getCitiesByCountryName(name: String): Result<List<String>> = withContext(Dispatchers.Default) {
        runCatching {
            val countries = loadCountries().getOrThrow()
            val cities = countries.firstOrNull { it.name.equals(name, ignoreCase = true) }?.cities ?: emptyList()
            cities
        }
    }


}