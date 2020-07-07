package io.agora.highqualityaudio.dialog;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatSeekBar;

import io.agora.highqualityaudio.R;
import io.agora.highqualityaudio.adapters.VoiceChangeAdapter;
import io.agora.highqualityaudio.adapters.VoiceItemClickListener;
import io.agora.highqualityaudio.ui.VoiceChangeRecyclerView;
import io.agora.highqualityaudio.utils.PreferenceManager;

import static io.agora.highqualityaudio.utils.Constants.ANC_ARCH;
import static io.agora.highqualityaudio.utils.Constants.ANC_RANGE;
import static io.agora.highqualityaudio.utils.Constants.ANC_SWITCH;

public class AINoiseDialog extends AlertDialog implements View.OnClickListener, VoiceItemClickListener,
        CompoundButton.OnCheckedChangeListener {
    public static final int DIALOG_FULL_WIDTH = 0;
    public static final int DIALOG_WIDE = -1;
    // The maximum dialog width in dp
    private static final int WIDE_SCREEN_DP = 240;

    private Switch ANCSwitch;
    private VoiceChangeRecyclerView archRecyclerView;
    private VoiceChangeAdapter archAdapter;
    private AppCompatSeekBar ANCRangeSeekBar;

    private AINoiseListener aiNoiseListener;

    public AINoiseDialog(@NonNull Context context) {
        super(context);
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
        window.setContentView(R.layout.dialog_ai_noise_layout);

        initView();
    }

    private void initView()
    {
        ANCSwitch = findViewById(R.id.ANC_Switch);
        archRecyclerView = findViewById(R.id.arch_recycler_options);
        ANCRangeSeekBar = findViewById(R.id.ANCRange_SeekBar);
        findViewById(R.id.change_voice_back).setOnClickListener(this);
        ANCSwitch.setOnCheckedChangeListener(this);
        archAdapter = new VoiceChangeAdapter(getContext(), R.array.noise_reduce_arch_items, -1);
        archAdapter.setVoiceItemClickListener(this);
        findViewById(R.id.change_voice_btn_confirm).setOnClickListener(this);
        findViewById(R.id.change_voice_btn_cancel).setOnClickListener(this);
        boolean ANC = PreferenceManager.get(ANC_SWITCH, false);
        archAdapter.setClickEnable(ANC);
        ANCSwitch.setChecked(ANC);
        if(ANC)
        {
            int index = PreferenceManager.get(ANC_ARCH, -1);
            archAdapter.setSelectedPosition(index);
            int progress = PreferenceManager.get(ANC_RANGE, 1);
            ANCRangeSeekBar.setProgress(progress);
        }
        archRecyclerView.setAdapter(archAdapter);

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
                if(aiNoiseListener != null)
                {
                    aiNoiseListener.onAction(ANCSwitch.isChecked(), archAdapter.getSelectedPosition(),
                            ANCRangeSeekBar.getProgress());
                }
                cancel();
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        archAdapter.setClickEnable(isChecked);
        ANCRangeSeekBar.setEnabled(isChecked);
    }

    @Override
    public void onVoiceItemClick(int categoryId, int index) {

    }

    public void setAiNoiseListener(AINoiseListener aiNoiseListener) {
        this.aiNoiseListener = aiNoiseListener;
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
        if (width == DIALOG_FULL_WIDTH) {
            return WindowManager.LayoutParams.MATCH_PARENT;
        }

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

    public interface AINoiseListener
    {
        /**
         * @param ANCSwitch 是否打开降噪
         * @param archIndex 所选架构的索引
         * @param ANCRange  降噪幅度*/
        void onAction(boolean ANCSwitch, int archIndex, int ANCRange);
    }
}
