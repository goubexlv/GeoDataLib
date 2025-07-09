package cm.daccvo.geodatalib.internal

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.ExperimentalResourceApi
import kotlin.jvm.java



actual fun readResourceText(path: String): String? {
    return object {}.javaClass.getResource(path)?.readText()
}