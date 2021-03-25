import userinterface.Userinterface
import java.io.File

object PdfToTextConverter {

    val tesseractData = File("C:\\Users\\joshs\\Desktop\\")
    val temp = File("${System.getProperty("java.io.tmpdir")}\\pdfTextConverter\\")

    @JvmStatic
    fun main(args: Array<String>) {

        // Requires Ghostscript

        Userinterface.open()

    }

}