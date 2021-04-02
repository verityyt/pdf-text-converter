package userinterface.utils

import java.awt.*
import java.io.File

object CustomFont {

    var regular: Font? = null
    var light: Font? = null

    fun registerFonts() {
        registerRegular()
        registerLight()
    }


    private fun registerRegular() {
        regular =
            Font.createFont(Font.TRUETYPE_FONT, if(PdfToTextConverter.useForBuild) {
                File("fonts\\Product-Sans-Regular.ttf")
            }else {
                File("files\\fonts\\Product-Sans-Regular.ttf")
            })
        val ge = GraphicsEnvironment.getLocalGraphicsEnvironment()
        ge.registerFont(regular)
    }

    private fun registerLight() {
        light =
            Font.createFont(Font.TRUETYPE_FONT, if(PdfToTextConverter.useForBuild) {
                File("fonts\\Product-Sans-Light.ttf")
            }else {
                File("files\\fonts\\Product-Sans-Light.ttf")
            })
        val ge = GraphicsEnvironment.getLocalGraphicsEnvironment()
        ge.registerFont(light)
    }

}