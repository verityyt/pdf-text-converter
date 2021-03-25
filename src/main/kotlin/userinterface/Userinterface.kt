package userinterface

import userinterface.listener.KeyboardListener
import userinterface.listener.MouseClickListener
import userinterface.listener.MouseMotionListener
import userinterface.screens.EndScreen
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

    var screen: Screen = StartScreen()

    val icon = ImageIO.read(File("files\\images\\icon.png"))

    fun open() {

        Logger.info("Opening frame...")

        CustomFont.registerFonts()

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

                screen.paint(g, g, this)
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

        val screenWidth = Toolkit.getDefaultToolkit().screenSize.width
        val screenHeight = Toolkit.getDefaultToolkit().screenSize.height
        val frameWidth = frame.size.width
        val frameHeight = frame.size.height

        frame.move(((screenWidth - frameWidth) / 2), ((screenHeight - frameHeight) / 2))

        Logger.info("Opened frame (on top)!")

        Thread {
            while (true) {
                Thread.sleep(1000 / 60)
                frame.repaint()
            }
        }.start()

    }

    fun loadScreen(screen: Screen) {
        this.screen = screen
    }

}