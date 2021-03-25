package userinterface.screens

import userinterface.Screen
import userinterface.Userinterface
import userinterface.Widget
import userinterface.utils.CustomFont
import utils.SoundManager
import java.awt.*
import java.awt.datatransfer.StringSelection
import java.awt.image.ImageObserver
import java.io.File
import javax.imageio.ImageIO

class EndScreen(val recognition: String) : Screen() {

    override val widgets: List<Widget> = listOf()

    private var recognitionDisplay = ""
    private var hoveredCopy = false
    private var ignoreHover = false

    init {
        if (recognition.length >= 32) {
            recognitionDisplay = "${recognition.substring(0, 32)}..."
        }else {
            recognitionDisplay = recognition
        }

        println("B: $recognition")
    }

    override fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver) {

        g.drawImage(Userinterface.icon, 200, 70, 200, 200, observer)

        g.font = CustomFont.regular?.deriveFont(36f)
        g.color = Color.black
        g.drawString("PDF to Text Converter", 130, 300)

        g.font = CustomFont.light?.deriveFont(24f)
        g.drawString("Text has been copied to your clipboard!", 80, 365)

        g.font = CustomFont.light?.deriveFont(16f)
        g.drawString("Press CTRL + V to paste into any textfield", 83, 385)

        g.font = CustomFont.light?.deriveFont(24f)
        g.drawString("\"$recognitionDisplay\"", 80 + 40, 372 + 90)

        g.drawImage(ImageIO.read(File("files\\images\\copy.png")), 35 + 40, 340 + 90, 45, 45, observer)

        if (hoveredCopy) {
            g2.color = Color.decode("#95a5a6")
            g2.composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)
            g2.fillRoundRect(37 + 40, 310 + 90, 70, 20, 5, 5)
            g2.fillPolygon(intArrayOf(65 + 40, 57 + 40, 49 + 40), intArrayOf(330 + 90, 337 + 90, 330 + 90), 3)
            g2.composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f)

            g.color = Color.black
            g.font = CustomFont.light?.deriveFont(12f)
            g.drawString("Copy again", 42 + 40, 324 + 90)
        }

    }

    override fun mouseClick(x: Int, y: Int) {
        if (x > (35 + 40) && x < (35 + 40 + 45) && y > (340  + 90) && y < (340 + 45 + 90 + 20)) {
            Thread {
                ignoreHover = true
                hoveredCopy = false

                SoundManager.playSound("copy")
                Toolkit.getDefaultToolkit().systemClipboard.setContents(
                    StringSelection(recognition),
                    null
                )

                Thread.sleep(150)

                ignoreHover = false
                hoveredCopy = true
            }.start()
        }
    }

    override fun mouseMove(x: Int, y: Int) {
        hoveredCopy = (!ignoreHover && x > (35 + 40) && x < (35 + 40 + 45) && y > (340  + 90) && y < (340 + 45 + 90 + 20))
    }

}