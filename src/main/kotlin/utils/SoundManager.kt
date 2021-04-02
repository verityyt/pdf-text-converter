package utils

import java.io.File
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.AudioInputStream

object SoundManager {

    fun playSound(name: String) {
        val clip = AudioSystem.getClip()
        val inputStream: AudioInputStream = AudioSystem.getAudioInputStream(if(PdfToTextConverter.useForBuild) {
            File("sounds\\$name.wav")
        }else {
            File("files\\sounds\\$name.wav")
        })
        clip.open(inputStream)
        clip.start()
    }

}