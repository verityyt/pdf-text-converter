package utils

import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.File

object Configuration {

    private val file = File("files\\config.json")

    val exists = file.exists()

    fun create() {
        val json = JSONObject()
        json["tesseractDataPath"] = ""

        file.writeText(json.toJSONString())
    }

    fun load(key: String): String {
        val json = JSONParser().parse(file.readText()) as JSONObject

        return json[key].toString()
    }

}