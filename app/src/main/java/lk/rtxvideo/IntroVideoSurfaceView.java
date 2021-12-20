package lk.rtxvideo;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import lk.devildeveloper.sanoj.sakmadalaconvert.R;

import java.io.IOException;

public class IntroVideoSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private MediaPlayer mp;
    private boolean has_started = false;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) public IntroVideoSurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public IntroVideoSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public IntroVideoSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IntroVideoSurfaceView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mp = new MediaPlayer();
        getHolder().addCallback(this);
    }

    @Override public void surfaceCreated(SurfaceHolder holder) {
        AssetFileDescriptor afd = getResources().openRawResourceFd(R.raw.slideshow);
        try {
            if (!has_started) {
                has_started = true;
                mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getDeclaredLength());
            }

            mp.prepare();
            android.view.ViewGroup.LayoutParams lp = getLayoutParams();
            lp.height = getMeasuredHeight();
            lp.width = getMeasuredWidth();

            setLayoutParams(lp);
            mp.setDisplay(getHolder());
            mp.setLooping(true);
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override public void surfaceDestroyed(SurfaceHolder holder) {
        mp.stop();
    }
}