package com.aldoapps.ravegroovyadventure

import android.app.Application
import android.content.res.AssetManager
import com.aldoapps.ravegroovyadventure.playsound.SoundPlayer
import groovy.transform.CompileStatic
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

@CompileStatic
class MainApplication extends Application {

    @Override
    void onCreate() {
        super.onCreate()
        initCaligraphy()
        initSoundPlayer()
    }

    private void initSoundPlayer() {
        def thread = new Thread( {
            SoundPlayer.INSTANCE.initSounds(getAssets())
        })
        thread.start()
    }

    private static void initCaligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/itc_fenice.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }
}