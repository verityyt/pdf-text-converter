import userinterface.Userinterface
import utils.ImageConverter
import utils.TextRecognizer
import java.io.File

object PdfToTextConverter {

    val file = File("C:\\Users\\joshs\\Desktop\\Test.pdf")
    val tesseractData = File("C:\\Users\\joshs\\Desktop\\")
    val temp = File("${System.getProperty("java.io.tmpdir")}\\pdfTextConverter\\")

    @JvmStatic
    fun main(args: Array<String>) {

        // Requires Ghostscript

        ImageConverter.convert(file)

        Userinterface.open()

    }

}