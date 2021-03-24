package userinterface.listener

import userinterface.Userinterface
import java.awt.event.MouseEvent
import java.awt.event.MouseListener

class ClickListener : MouseListener {

    override fun mouseClicked(event: MouseEvent?) {
        if(event != null) {
            val x = event.x
            val y = event.y

            if(x > 7 && x < (7 + 100) && y > 320 && y < (320 + 75)) {
                Userinterface.previousImage()
            }else if(x > 570 && x < (570 + 100) && y > 320 && y < (320 + 75)) {
                Userinterface.nextImage()
            }

        }
    }

    override fun mousePressed(e: MouseEvent?) { }

    override fun mouseReleased(e: MouseEvent?) { }

    override fun mouseEntered(e: MouseEvent?) { }

    override fun mouseExited(e: MouseEvent?) { }

}