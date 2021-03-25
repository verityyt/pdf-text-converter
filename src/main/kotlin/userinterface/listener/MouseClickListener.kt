package userinterface.listener

import userinterface.Userinterface
import userinterface.screens.PdfViewerScreen
import java.awt.event.MouseEvent
import java.awt.event.MouseListener

class MouseClickListener : MouseListener {

    override fun mouseClicked(event: MouseEvent?) {
        if (event != null) {

            val x = event.x
            val y = event.y

            for (widget in Userinterface.screen.widgets) {
                widget.mouseClick(x, y)
            }

            Userinterface.screen.mouseClick(x, y)

        }
    }

    override fun mousePressed(event: MouseEvent?) {
        if (event != null) {
            if (Userinterface.screen is PdfViewerScreen) {
                (Userinterface.screen as PdfViewerScreen).dragStartX = event.x
                (Userinterface.screen as PdfViewerScreen).dragStartY = event.y
            }
        }
    }

    override fun mouseReleased(event: MouseEvent?) {}

    override fun mouseEntered(e: MouseEvent?) {}

    override fun mouseExited(e: MouseEvent?) {}

}