import userinterface.Userinterface
import utils.ImageConverter
import java.io.File

object PdfToTextConverter {

    @JvmStatic
    fun main(args: Array<String>) {

        // Requires Ghostscript

        val file = File("C:\\Users\\joshs\\Desktop\\Test.pdf")

        Userinterface.open()

        /*ImageConverter.convert(file)*/

    }

}