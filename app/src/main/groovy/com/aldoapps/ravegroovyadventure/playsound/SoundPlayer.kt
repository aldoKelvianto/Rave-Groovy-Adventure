package com.aldoapps.ravegroovyadventure.playsound

import android.content.res.AssetManager
import android.media.AudioManager
import android.media.SoundPool
import android.util.Log
import java.io.IOException
import java.util.*

/**
 * Created by aldo on 02/03/18.
 */
@Suppress("DEPRECATION")
object SoundPlayer {

    private val soundPool: SoundPool

    private val soundList = mutableListOf<Sound>()

    private const val TAG = "soundplayer"

    private const val MAX_SOUND: Int = 3

    private const val SOUNDS_FOLDER = "win_sounds"

    const val DEFAULT_SPEED = 1.0f

    const val DEFAULT_VOLUME = 1.0f

    const val DEFAULT_PRIORITY = 1

    const val DEFAULT_LOOP = 0 // don't loop

    const val DEFAULT_QUALITY = 0

    init {
        soundPool = SoundPool(MAX_SOUND, AudioManager.STREAM_MUSIC, DEFAULT_QUALITY)
    }

    private fun getFileNames(assetManager: AssetManager): List<String> {
        var fileNames: List<String> = listOf()
        try {
            fileNames = assetManager.list(SOUNDS_FOLDER).toList()
        } catch (e: IOException) {
            Log.e(TAG, e.toString())
        }
        return fileNames
    }

    fun initSounds(assetManager: AssetManager) {
        val fileNames = getFileNames(assetManager)
        for (fileName in fileNames) {
            try {
                val path = SOUNDS_FOLDER + "/" + fileName
                val sound = loadFileToSoundPool(assetManager, path)
                        ?: continue
                soundList.add(sound)
            } catch (e: IOException) {
                Log.e(TAG, e.toString())
            }
        }
    }

    fun play() {
        val random = Random()
        play(soundList[random.nextInt(soundList.size)])
    }

    private fun play(sound: Sound) {
        soundPool.playDefault(sound.soundId)
    }

    private fun loadFileToSoundPool(assetManager: AssetManager, path: String): Sound? {
        try {
            val fd = assetManager.openFd(path)
            val soundId = soundPool.load(fd, DEFAULT_PRIORITY)
            return Sound(soundId, path)
        } catch (e: IOException) {
            Log.e(TAG, e.toString())
        }
        return null
    }

    fun release() {
        soundPool.release()
    }
}

fun SoundPool.playDefault(soundId: Int) {
    play(soundId, SoundPlayer.DEFAULT_VOLUME, SoundPlayer.DEFAULT_VOLUME,
            SoundPlayer.DEFAULT_PRIORITY, SoundPlayer.DEFAULT_LOOP, SoundPlayer.DEFAULT_SPEED)
}