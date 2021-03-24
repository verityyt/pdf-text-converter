package userinterface

import userinterface.listener.ClickListener
import java.awt.*
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JComponent
import javax.swing.JFrame

object Userinterface {

    lateinit var frame: JFrame

    private var images = mutableListOf<File>()
    private var curImageIndex = 0

    fun open() {

        val output = File("output\\")

        for (file in output.listFiles()!!) {
            images.add(file)
        }

        frame = JFrame()

        val comp = object : JComponent() {
            override fun paint(g: Graphics) {
                val g2 = g as Graphics2D

                g2.setRenderingHint(
                    RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY
                )
                g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
                )
                g2.setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON
                )
                g2.setRenderingHint(
                    RenderingHints.KEY_STROKE_CONTROL,
                    RenderingHints.VALUE_STROKE_NORMALIZE
                )
                g2.setRenderingHint(
                    RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR
                )
                g2.setRenderingHint(
                    RenderingHints.KEY_FRACTIONALMETRICS,
                    RenderingHints.VALUE_FRACTIONALMETRICS_ON
                )

                val image = ImageIO.read(images[curImageIndex])
                g.drawImage(image,0,-35,638,825,this)

                g.font = g.font.deriveFont(72f)
                g.color = Color.black

                g.drawString("<",10,371)
                g.drawString(">",575,371)

            }
        }

        frame.add(comp)

        frame.addMouseListener(ClickListener())

        frame.size = Dimension(638, 825)
        frame.isResizable = false
        frame.isUndecorated = false
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.title = "PDF to Text Converter"
        frame.isAlwaysOnTop = true
        frame.isVisible = true
        frame.isAlwaysOnTop = false

        Thread {
            while(true) {
                Thread.sleep(1000 / 60)
                frame.repaint()
            }
        }.start()

    }

    fun nextImage() {
        if(curImageIndex == (images.size - 1)) {
            curImageIndex = 0
        }else {
            curImageIndex++
        }
    }

    fun previousImage() {
        if(curImageIndex == 0) {
            curImageIndex = images.size - 1
        }else {
            curImageIndex--
        }
    }

}