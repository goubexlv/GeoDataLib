package cm.daccvo.geodatalib.model

import kotlinx.serialization.Serializable

@Serializable
data class CountryDetailed(
    val name: String,
    val iso3: String,
    val iso2: String,
    val numeric_code: String,
    val phonecode: String,
    val capital: String,
    val currency: String,
    val currency_name: String,
    val currency_symbol: String,
    val tld: String,
    val native: String?,  // <-- nullable ici
    val region: String,
    val region_id: Int,
    val subregion: String,
    val subregion_id: Int,
    val nationality: String,
    val timezones: List<Timezone>,
    val translations: Map<String, String>,
    val latitude: String,
    val longitude: String,
    val emoji: String,
    val emojiU: String
)


@Serializable
data class Timezone(
    val zoneName: String,
    val gmtOffset: Int,
    val gmtOffsetName: String,
    val abbreviation: String,
    val tzName: String
)

@Serializable
data class Translations(
    val ko: String? = null,
    val `pt-BR`: String? = null,
    val pt: String? = null,
    val nl: String? = null,
    val hr: String? = null,
    val fa: String? = null,
    val de: String? = null,
    val es: String? = null,
    val fr: String? = null,
    val ja: String? = null,
    val it: String? = null,
    val `zh-CN`: String? = null,
    val tr: String? = null,
    val ru: String? = null,
    val uk: String? = null,
    val pl: String? = null
)