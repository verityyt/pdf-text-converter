package userinterface.screens

import userinterface.utils.CustomFont
import userinterface.Screen
import userinterface.Userinterface
import userinterface.Widget
import userinterface.utils.FileChooser
import userinterface.widgets.ButtonWidget
import utils.ImageConverter
import java.awt.Button
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.ImageObserver
import kotlin.system.exitProcess

class StartScreen : Screen() {

    override val widgets: List<Widget> = listOf(ButtonWidget(230, 40, 730, 25, 175, 35, 20, "Continue") {
        val choice = FileChooser.open()

        if (choice != null) {
            loading = true.also { updateDots() }
            ImageConverter.convert(choice) { Userinterface.loadScreen(PdfViewerScreen()) }
        }
    })

    private var loading = false
    private var dots = "."

    private fun updateDots() {
        Thread {
            while(true) {
                Thread.sleep(800)

                if (dots == "...") {
                    dots = "."
                } else {
                    dots += "."
                }
            }
        }.start()
    }

    override fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver) {

        g.drawImage(Userinterface.icon, 200, 70, 200, 200, observer)

        g.font = CustomFont.regular?.deriveFont(36f)
        g.color = Color.black
        g.drawString("PDF to Text Converter", 130, 300)

        g.font = CustomFont.light?.deriveFont(24f)
        g.drawString("How does it work?", 35, 375)

        g.drawString("1. Select a PDF (Portable Document File)", 35, 440)
        g.drawString("2. Choose a page", 35, 470)
        g.drawString("3. Select region", 35, 500)
        g.drawString("4. Press ENTER", 35, 530)

        for (widget in widgets) {
            widget.paint(g, g2, observer)
        }

        if (loading) {
            g.color = Color.black
            g.font = CustomFont.light?.deriveFont(24f)
            g.drawString("> Converting$dots", 35, 595)
        }

    }

    override fun mouseClick(x: Int, y: Int) { }

    override fun mouseMove(x: Int, y: Int) { }

}