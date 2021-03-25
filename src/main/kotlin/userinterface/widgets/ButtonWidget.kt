package userinterface.widgets

import userinterface.Widget
import userinterface.utils.CustomFont
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.ImageObserver

class ButtonWidget(
    var x: Int,
    var xAdd: Int,
    var y: Int,
    var yAdd: Int,
    var w: Int,
    var h: Int,
    var arc: Int,
    var text: String,
    var action: () -> Unit
) : Widget() {

    override var hovered = false

    override fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver) {
        if (hovered) {

            g2.color = Color.black
            g2.stroke = BasicStroke(2f)
            g2.fillRoundRect(x, y, w, h, arc, arc)

            g.color = Color.white
            g.font = CustomFont.regular?.deriveFont(24f)
            g.drawString(text, x + xAdd, y + yAdd)

        } else {

            g2.color = Color.black
            g2.stroke = BasicStroke(2f)
            g2.drawRoundRect(x, y, w, h, arc, arc)

            g.color = Color.black
            g.font = CustomFont.regular?.deriveFont(24f)
            g.drawString(text, x + xAdd, y + yAdd)

        }
    }

    override fun mouseClick(x: Int, y: Int) {
        if (x >= this.x && x <= (this.x + this.w) && y >= this.y && y <= (this.y + this.h + 50)) {
            action()
        }
    }

    override fun mouseMove(x: Int, y: Int) {
        hovered = x >= this.x && x <= (this.x + this.w) && y >= this.y && y <= (this.y + this.h + 50)
    }

}