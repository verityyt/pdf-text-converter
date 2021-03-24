package utils

import java.text.SimpleDateFormat
import java.util.*

object Logger {

    private val debug = true

    fun info(text: String) {
        println("[INFO]: $text")
    }

    fun debug(text: String) {
        println("\u001B[31m[DEBUG]: $text\u001B[0m")
    }

}