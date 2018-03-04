package com.aldoapps.ravegroovyadventure.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.aldoapps.ravegroovyadventure.playsound.SoundPlayer;
import com.arasthel.swissknife.SwissKnife;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayout());
        SwissKnife.inject(this);
    }

    protected abstract int getLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SoundPlayer.INSTANCE.release();
    }
}
