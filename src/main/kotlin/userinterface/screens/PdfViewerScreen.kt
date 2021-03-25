package userinterface.screens

import userinterface.Screen
import userinterface.Widget
import java.awt.*
import java.awt.image.ImageObserver
import java.io.File
import javax.imageio.ImageIO

class PdfViewerScreen : Screen() {

    override val widgets: List<Widget> = listOf()

    private var images = mutableListOf<File>()
    private var curImageIndex = 0

    var dragStartX = 0
    var dragStartY = 0
    var dragEndX = 0
    var dragEndY = 0

    private var load = true

    override fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver) {

        if (load) {
            val converted = File("${PdfToTextConverter.temp.absolutePath}\\converted\\")

            for (file in converted.listFiles()!!) {
                images.add(file)
            }

            load = false
        }

        val image = ImageIO.read(images[curImageIndex])
        g.drawImage(image, 0, -35, 638, 825, observer)

        g.font = g.font.deriveFont(72f)
        g.color = Color.black

        g.drawString("<", 10, 371)
        g.drawString(">", 575, 371)

        val rectW = (dragEndX - dragStartX)
        val rectH = (dragEndY - dragStartY)
        val color = Color.decode("#3498db")

        g2.color = color
        g2.stroke = BasicStroke(3f)
        g2.drawRect(dragStartX, dragStartY, rectW, rectH)

        g2.composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f)
        g2.fillRect(dragStartX, dragStartY, rectW, rectH)

        g2.composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f)
    }

    fun nextImage() {
        if (curImageIndex == (images.size - 1)) {
            curImageIndex = 0
        } else {
            curImageIndex++
        }
    }

    fun previousImage() {
        if (curImageIndex == 0) {
            curImageIndex = images.size - 1
        } else {
            curImageIndex--
        }
    }

    fun getCurrent(): File {
        return images[curImageIndex]
    }

}