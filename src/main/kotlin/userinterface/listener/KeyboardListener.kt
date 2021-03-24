package userinterface.listener

import sun.rmi.runtime.Log
import userinterface.Userinterface
import utils.Logger
import utils.TextRecognizer
import java.awt.Color
import java.awt.Rectangle
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.tools.Tool
import kotlin.math.absoluteValue


class KeyboardListener : KeyListener {

    override fun keyTyped(event: KeyEvent?) {}

    override fun keyPressed(e: KeyEvent?) {}

    override fun keyReleased(event: KeyEvent?) {
        if (event != null) {
            val keyCode = event.keyCode
            val keyChar = event.keyChar

            if (keyCode == 10) {
                if (Userinterface.dragStartY != 0) {
                    Logger.info("Cutting out regions...")

                    Logger.debug("Calculate region coordinates...")

                    val image = ImageIO.read(Userinterface.getCurrent())!!
                    val rectW = (Userinterface.dragEndX - Userinterface.dragStartX)
                    val rectH = ((Userinterface.dragEndY + 35) - (Userinterface.dragStartY + 35))

                    Logger.debug("Calculation finished! Cropping image...")

                    val cropped = cropImage(
                        image,
                        Userinterface.dragStartX,
                        Userinterface.dragStartY + 35,
                        rectW,
                        rectH,
                    )!!

                    val folder = File("${PdfToTextConverter.temp.absolutePath}\\current\\")

                    folder.deleteRecursively()
                    folder.mkdirs()

                    Logger.info("Successfully cut out region!")

                    ImageIO.write(cropped, "jpg", File("${folder.absolutePath}\\region.jpg"))

                    Thread.sleep(1500)

                    Userinterface.setTitle(extension = "Copied to clipboard")
                    Userinterface.show = false
                    Toolkit.getDefaultToolkit().systemClipboard.setContents(StringSelection(TextRecognizer.detect()), null)
                }
            }
        }
    }

    private fun cropImage(src: BufferedImage, x: Int, y: Int, w: Int, h: Int): BufferedImage? {
        return src.getSubimage(x, y, w, h)
    }

}