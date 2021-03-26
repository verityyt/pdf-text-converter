package userinterface.listener

import userinterface.Userinterface
import userinterface.screens.PdfViewerScreen
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionListener

class MouseMotionListener : MouseMotionListener {

    override fun mouseDragged(event: MouseEvent?) {
        if (event != null && Userinterface.screen is PdfViewerScreen) {
            (Userinterface.screen as PdfViewerScreen).dragEndX = event.x - 5
            (Userinterface.screen as PdfViewerScreen).dragEndY = event.y - 25
        }
    }

    override fun mouseMoved(event: MouseEvent?) {
        if (event != null) {
            val x = event.x - 5
            val y = event.y - 25

            for (widget in Userinterface.screen.widgets) {
                widget.mouseMove(x, y)
            }

            Userinterface.screen.mouseMove(x, y)
        }
    }

}