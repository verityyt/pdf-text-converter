package userinterface.listener

import userinterface.Userinterface
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionListener

class MouseMotionListener : MouseMotionListener {

    override fun mouseDragged(event: MouseEvent?) {
        if (event != null) {
            Userinterface.dragEndX = event.x
            Userinterface.dragEndY = event.y
        }
    }

    override fun mouseMoved(event: MouseEvent?) {
        if (event != null) {
            val x = event.x
            val y = event.y

            for (widget in Userinterface.screen.widgets) {
                widget.mouseMove(x, y)
            }
        }
    }

}