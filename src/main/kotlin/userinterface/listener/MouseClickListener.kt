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

            if (Userinterface.screen is PdfViewerScreen) {
                if (x > 7 && x < (7 + 100) && y > 320 && y < (320 + 75)) {
                    (Userinterface.screen as PdfViewerScreen).previousImage()
                } else if (x > 570 && x < (570 + 100) && y > 320 && y < (320 + 75)) {
                    (Userinterface.screen as PdfViewerScreen).nextImage()
                }
            }

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