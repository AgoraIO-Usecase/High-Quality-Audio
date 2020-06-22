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
import io.agora.highqualityaudio.utils.WindowUtil;

public class VoiceEffectDialog extends AlertDialog implements View.OnClickListener, VoiceItemClickListener {
    public static final int DIALOG_FULL_WIDTH = 0;
    public static final int DIALOG_WIDE = -1;
    // The maximum dialog width in dp
    private static final int WIDE_SCREEN_DP = 240;

    private VoiceChangeRecyclerView spaceRecyclerView, changeRecyclerView, qufengRecyclerView;
    private VoiceChangeAdapter spaceAdapter, changeAdapter, qufengAdapter;
    private int firstCategoryId = -1, secondCategoryId = -1, index = -1;
    private VoiceEffectListener voiceEffectListener;

    public VoiceEffectDialog(@NonNull Context context, int firstCategoryId) {
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
        spaceRecyclerView = findViewById(R.id.space_voice_recycler_options);
        spaceAdapter = new VoiceChangeAdapter(getContext(), R.array.space_voice_effect_items, SoundEffectUtil.SECONDCATEGORY[3]);
        spaceAdapter.setVoiceItemClickListener(this);
        spaceRecyclerView.setAdapter(spaceAdapter);
        changeRecyclerView = findViewById(R.id.change_voice_recycler_options);
        changeAdapter = new VoiceChangeAdapter(getContext(), R.array.change_voice_effect_items, SoundEffectUtil.SECONDCATEGORY[4]);
        changeAdapter.setVoiceItemClickListener(this);
        changeRecyclerView.setAdapter(changeAdapter);
        qufengRecyclerView = findViewById(R.id.qufeng_change_recycler_options);
        qufengAdapter = new VoiceChangeAdapter(getContext(), R.array.qufeng_voice_effect_items, SoundEffectUtil.SECONDCATEGORY[5]);
        qufengAdapter.setVoiceItemClickListener(this);
        qufengRecyclerView.setAdapter(qufengAdapter);
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
                if(voiceEffectListener != null)
                {
                    voiceEffectListener.onVoiceSelect(firstCategoryId, secondCategoryId, index);
                }
                cancel();
                break;
            default:
                break;
        }
    }

    @Override
    public void onVoiceItemClick(int categoryId, int index) {
        spaceAdapter.clearSelected();
        changeAdapter.clearSelected();
        qufengAdapter.clearSelected();
        /**For questions about category values, please see {@link SoundEffectUtil#SECONDCATEGORY}*/
        switch (categoryId)
        {
            case 3:
                spaceAdapter.setSelectedPosition(index);
                break;
            case 4:
                changeAdapter.setSelectedPosition(index);
                break;
            case 5:
                qufengAdapter.setSelectedPosition(index);
                break;
            default:
                break;
        }
        this.secondCategoryId = categoryId;
        this.index = index;
    }

    public void setVoiceEffectListener(VoiceEffectListener voiceEffectListener) {
        this.voiceEffectListener = voiceEffectListener;
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
