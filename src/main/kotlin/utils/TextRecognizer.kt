package utils

import net.sourceforge.tess4j.Tesseract
import net.sourceforge.tess4j.Tesseract1
import java.io.File

object TextRecognizer {

    const val DEFAULT_CHAR_WHITELIST = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-. "

    fun detect(): String {

        Logger.info("Recognize text from image...")

        Logger.debug("Loading cropped region file...")

        val file = File("${PdfToTextConverter.temp.absolutePath}\\current\\region.jpg")

        if (file.exists()) {
            Logger.debug("Configuring tesseract...")

            val tesseract = Tesseract.getInstance()
            tesseract.setDatapath(PdfToTextConverter.tesseractData.absolutePath)
            tesseract.setLanguage("deu")
            tesseract.setTessVariable("tessedit_char_whitelist", DEFAULT_CHAR_WHITELIST)

            Logger.debug("Configuration finished! Scanning image for text...")

            val recognition = tesseract.doOCR(file)

            if (recognition != null && recognition != "") {
                Logger.info("Successfully recognized text in image!")
                return recognition
            }
        }

        return ("").also { Logger.info("No text recognized!") }
    }

}