package utils

import org.ghost4j.document.PDFDocument
import org.ghost4j.renderer.SimpleRenderer
import userinterface.Userinterface
import java.awt.image.BufferedImage
import java.awt.image.RenderedImage
import java.io.File
import javax.imageio.ImageIO


object ImageConverter {

    fun convert(file: File, exec: () -> Unit) {
        Thread {
            Logger.info("Converting ${file.name} to images...")

            Logger.debug("Loading document...")

            val doc = PDFDocument()
            doc.load(file)

            Logger.debug("Rendering document...")

            val renderer = SimpleRenderer()
            renderer.resolution = 300

            val images = renderer.render(doc)

            Logger.debug("Converted document to images!")
            Logger.debug("Saving converted images to temp folder...")

            val folder = File("${PdfToTextConverter.temp.absolutePath}\\converted\\")

            folder.deleteRecursively()
            folder.mkdirs()

            for (i in images.indices) {
                val image = images[i]

                val resizedImage = BufferedImage(638, 825, BufferedImage.TYPE_INT_RGB)
                val graphics2D = resizedImage.createGraphics()
                graphics2D.drawImage(image, 0, 0, 638, 825, null)
                graphics2D.dispose()

                ImageIO.write(resizedImage as RenderedImage, "png", File(("${folder.absolutePath}\\page${i + 1}").toString() + ".png"))
            }

            Logger.info("Successfully converted document to images!")

            exec()
        }.start()
    }

}