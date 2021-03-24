package utils

import org.ghost4j.document.PDFDocument
import org.ghost4j.renderer.SimpleRenderer
import java.awt.image.RenderedImage
import java.io.File
import javax.imageio.ImageIO


object ImageConverter {

    fun convert(file: File) {

        val temp = File(System.getProperty("java.io.tmpdir"))

        val doc = PDFDocument()
        doc.load(file)

        val renderer = SimpleRenderer()
        renderer.resolution = 300

        val images = renderer.render(doc)

        val folder = File("output\\")

        folder.deleteRecursively()
        folder.mkdir()

        for (i in images.indices) {
            ImageIO.write(images[i] as RenderedImage, "png", File(("output//page${i + 1}").toString() + ".png"))
        }

    }

}