package userinterface

import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.ImageObserver

abstract class Screen {

    abstract val widgets: List<Widget>

    abstract fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver)

    abstract fun mouseClick(x: Int, y: Int)

    abstract fun mouseMove(x: Int, y: Int)

}