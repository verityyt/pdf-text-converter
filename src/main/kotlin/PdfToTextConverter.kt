import userinterface.Userinterface
import utils.ImageConverter
import utils.Logger
import utils.TextRecognizer
import java.io.File

object PdfToTextConverter {

    val tesseractData = File("C:\\Users\\joshs\\Desktop\\")
    val temp = File("${System.getProperty("java.io.tmpdir")}\\pdfTextConverter\\")

    @JvmStatic
    fun main(args: Array<String>) {

        // Requires Ghostscript

        val choice = FileChooser.open()

        if(choice == null) {
            exitProcess(-1)
        }else {
            ImageConverter.convert(choice)
        }

        Userinterface.open()

    }

}