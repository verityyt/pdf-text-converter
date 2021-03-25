import userinterface.Userinterface
import java.awt.BorderLayout
import java.awt.Dimension
import java.io.File
import javax.swing.*

object PdfToTextConverter {

    val tesseractData = File("C:\\Users\\joshs\\Desktop\\")
    val temp = File("${System.getProperty("java.io.tmpdir")}\\pdfTextConverter\\")

    @JvmStatic
    fun main(args: Array<String>) {
        // Requires Ghostscript

        if(checkOs()) {
            Userinterface.open()
        }
    }

    fun checkOs(): Boolean {
        val operatingSystem = System.getProperty("os.name")

        if(operatingSystem != "Windows 10") {

            val dialog = JDialog()
            dialog.add(JLabel("     This software only supports Windows 10 (64/32bit"))

            dialog.title = "Error"
            dialog.size = Dimension(350,100)
            dialog.isVisible = true

            return false

        }

        return true
    }

}