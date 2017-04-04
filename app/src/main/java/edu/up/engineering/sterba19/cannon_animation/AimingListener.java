package edu.up.engineering.sterba19.cannon_animation;

import android.view.View;
import android.widget.SeekBar;

/**
 * Created by esterba on 4/3/17.
 */

public class AimingListener implements SeekBar.OnSeekBarChangeListener {
    int count;

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        count = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public int getCount(){return count;}
}
