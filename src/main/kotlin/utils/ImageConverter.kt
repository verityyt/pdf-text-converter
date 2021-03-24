package utils

import org.ghost4j.document.PDFDocument
import org.ghost4j.renderer.SimpleRenderer
import java.awt.image.BufferedImage
import java.awt.image.RenderedImage
import java.io.File
import javax.imageio.ImageIO


object ImageConverter {

    fun convert(file: File) {

        val doc = PDFDocument()
        doc.load(file)

        val renderer = SimpleRenderer()
        renderer.resolution = 300

        val images = renderer.render(doc)

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

    }

}