package io.agora.highqualityaudio.dialog;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import io.agora.highqualityaudio.R;
import io.agora.highqualityaudio.adapters.VoiceChangeAdapter;
import io.agora.highqualityaudio.adapters.VoiceItemClickListener;
import io.agora.highqualityaudio.ui.VoiceChangeRecyclerView;
import io.agora.highqualityaudio.utils.SoundEffectUtil;

public class AINoiseDialog extends AlertDialog implements View.OnClickListener {
    public static final int DIALOG_FULL_WIDTH = 0;
    public static final int DIALOG_WIDE = -1;
    // The maximum dialog width in dp
    private static final int WIDE_SCREEN_DP = 240;

    private int firstCategoryId = -1;

    public AINoiseDialog(@NonNull Context context, int firstCategoryId) {
        super(context);
        this.firstCategoryId = firstCategoryId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        if (window == null)
        {
            return;
        }
//        WindowUtil.hideWindowStatusBar(window);
        window.setLayout(getWidth(window, DIALOG_FULL_WIDTH),
                WindowManager.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawable(null);
        window.setGravity(Gravity.END);
        window.setContentView(R.layout.dialog_voice_effect_layout);
        findViewById(R.id.change_voice_back).setOnClickListener(this);
        findViewById(R.id.change_voice_btn_confirm).setOnClickListener(this);
        findViewById(R.id.change_voice_btn_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.change_voice_back:
            case R.id.change_voice_btn_cancel:
                cancel();
                break;
            case R.id.change_voice_btn_confirm:
                cancel();
                break;
            default:
                break;
        }
    }

    /**
     * Set the dialog full screen wide if the type is DIALOG_FULL_WIDTH
     * or the target width is larger than the actual screen width;
     * Otherwise, the dialog is set to target width, with the maximum
     * width WIDE_SCREEN_DP
     * @param window dialog window
     * @param width target width
     * @return MATCH_PARENT if full screen wide, otherwise the actual width
     *         of the dialog in pixels
     */
    private int getWidth(Window window, int width) {
        if (width == DIALOG_FULL_WIDTH) return WindowManager.LayoutParams.MATCH_PARENT;

        WindowManager manager = window.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);

        if (width >= outMetrics.widthPixels) {
            return WindowManager.LayoutParams.MATCH_PARENT;
        } else if (width == DIALOG_WIDE) {
            int w = (int) (WIDE_SCREEN_DP * outMetrics.density + 0.5);
            return Math.min(w, outMetrics.widthPixels);
        } else return width;
    }
}
