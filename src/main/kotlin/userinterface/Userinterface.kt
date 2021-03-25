package userinterface

import userinterface.listener.KeyboardListener
import userinterface.listener.MouseClickListener
import userinterface.listener.MouseMotionListener
import userinterface.screens.StartScreen
import userinterface.utils.CustomFont
import utils.Logger
import java.awt.*
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JComponent
import javax.swing.JFrame

object Userinterface {

    lateinit var frame: JFrame

    private var images = mutableListOf<File>()
    private var curImageIndex = 0

    var dragStartX = 0
    var dragStartY = 0
    var dragEndX = 0
    var dragEndY = 0

    var show = true
    var screen: Screen = StartScreen()

    val icon = ImageIO.read(File("files\\images\\icon.png"))

    fun open() {

        Logger.info("Opening frame...")

        CustomFont.registerFonts()

        val converted = File("${PdfToTextConverter.temp.absolutePath}\\converted\\")

        for (file in converted.listFiles()!!) {
            images.add(file)
        }

        frame = JFrame()

        val comp = object : JComponent() {
            override fun paint(g: Graphics) {
                /*if (show) {
                    val image = ImageIO.read(images[curImageIndex])
                    g.drawImage(image, 0, -35, 638, 825, this)

                    g.font = g.font.deriveFont(72f)
                    g.color = Color.black

                    g.drawString("<", 10, 371)
                    g.drawString(">", 575, 371)

                    val rectW = (dragEndX - dragStartX)
                    val rectH = (dragEndY - dragStartY)
                    val color = Color.decode("#3498db")

                    g.color = color
                    g.stroke = BasicStroke(3f)
                    g.drawRect(dragStartX, dragStartY, rectW, rectH)

                    g2.composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f)
                    g2.fillRect(dragStartX, dragStartY, rectW, rectH)

                    g2.composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f)
                }*/

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

                screen.paint(g, g as Graphics2D, this)
            }
        }

        frame.add(comp)

        frame.addMouseListener(MouseClickListener())
        frame.addMouseMotionListener(MouseMotionListener())
        frame.addKeyListener(KeyboardListener())

        frame.size = Dimension(638, 825)
        frame.isResizable = false
        frame.isUndecorated = false
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.title = "PDF to Text Converter"
        frame.iconImage = icon
        frame.isAlwaysOnTop = true
        frame.isVisible = true
        frame.isAlwaysOnTop = false

        Logger.info("Opened frame (on top)!")

        Thread {
            while (true) {
                Thread.sleep(1000 / 60)
                frame.repaint()
            }
        }.start()

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

    fun setTitle(title: String = "PDF to Text Converter", extension: String = "") {
        frame.isVisible = false
        if (extension != "") {
            frame.title = "$title | $extension"
        } else {
            frame.title = title
        }
        frame.isVisible = true
    }

}