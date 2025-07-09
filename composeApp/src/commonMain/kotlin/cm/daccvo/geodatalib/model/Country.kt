package cm.daccvo.geodatalib.model

import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val name: String,
    val cities: List<String> = emptyList()
)
