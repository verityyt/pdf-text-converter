import userinterface.Userinterface
import utils.Configuration
import java.awt.Dimension
import java.io.File
import javax.swing.*

object PdfToTextConverter {

    var tesseractData = File("")
    val temp = File("${System.getProperty("java.io.tmpdir")}\\pdfTextConverter\\")

    @JvmStatic
    fun main(args: Array<String>) {
        if (checkOs()) {
            if (!Configuration.exists) {
                Configuration.create()
            }

            tesseractData = File(Configuration.load("tesseractDataPath"))

            if (tesseractData == File("")) {
                showErrorFrame("Please set 'tesseractDataPath' value in config file")
            } else {
                Userinterface.open()
            }
        }
    }

    fun checkOs(): Boolean {
        val operatingSystem = System.getProperty("os.name")

        if (operatingSystem != "Windows 10") {
            showErrorFrame("This software only supports Windows 10 (64/32bit)")

            return false
        }

        return true
    }

    private fun showErrorFrame(errMsg: String) {
        val dialog = JDialog()
        dialog.add(JLabel("     $errMsg"))

        dialog.title = "Error"
        dialog.size = Dimension(350, 100)
        dialog.isVisible = true
    }

}