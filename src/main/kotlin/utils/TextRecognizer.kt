package utils

import net.sourceforge.tess4j.Tesseract
import net.sourceforge.tess4j.Tesseract1
import java.io.File

object TextRecognizer {

    const val DEFAULT_CHAR_WHITELIST = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-. "

    fun detect(): String {

        val file = File("${PdfToTextConverter.temp.absolutePath}\\current\\region.jpg")

        if (file.exists()) {
            val tesseract = Tesseract.getInstance()
            tesseract.setDatapath(PdfToTextConverter.tesseractData.absolutePath)
            tesseract.setLanguage("deu")
            tesseract.setTessVariable("tessedit_char_whitelist", DEFAULT_CHAR_WHITELIST)

            val recognition = tesseract.doOCR(file)

            if (recognition != null && recognition != "") {
                return recognition
            }
        }

        return ""
    }

}