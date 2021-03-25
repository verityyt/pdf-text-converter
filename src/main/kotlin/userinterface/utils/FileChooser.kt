package userinterface.utils

import java.io.File
import javax.swing.JFileChooser
import javax.swing.JFrame
import javax.swing.UIManager
import javax.swing.filechooser.FileNameExtensionFilter

object FileChooser {

    fun open(): File? {

        val chooser = JFileChooser()
        chooser.currentDirectory = File(System.getProperty("user.home") + "\\Desktop")
        chooser.fileSelectionMode = JFileChooser.FILES_ONLY
        chooser.fileFilter = FileNameExtensionFilter("Portable Document Format (.pdf)", "pdf")

        UIManager.setLookAndFeel(UIManager.getLookAndFeel())

        val choice = chooser.showDialog(JFrame(), "Select PDF file")

        if(choice == JFileChooser.APPROVE_OPTION) {
            return chooser.selectedFile
        }

        return null
    }

}