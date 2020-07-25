package io.agora.highqualityaudio.activities;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import io.agora.highqualityaudio.R;
import io.agora.highqualityaudio.adapters.ChannelListAdapter;
import io.agora.highqualityaudio.data.UserAccountManager;
import io.agora.highqualityaudio.dialog.AINoiseDialog;
import io.agora.highqualityaudio.ui.ChannelListRecyclerView;
import io.agora.highqualityaudio.ui.ScreenHeightDialog;
import io.agora.highqualityaudio.utils.Constants;
import io.agora.highqualityaudio.utils.SoundSettingUtil;

/**
 * @author cjw
 */
public class MainActivity extends BaseActivity implements
        ChannelListAdapter.ChannelItemClickListener,
        SwipeRefreshLayout.OnRefreshListener,
        View.OnClickListener,
        AINoiseDialog.AINoiseListener{
    private static final String TAG = "MainActivity";
    private static final int SWIPE_TIMEOUT = 500;

    private SwipeRefreshLayout mSwipeList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        ChannelListRecyclerView list = findViewById(R.id.main_channel_list);
        list.setItemClickListener(this);

        mSwipeList = findViewById(R.id.main_channel_layout);
        mSwipeList.setOnRefreshListener(this);

        findViewById(R.id.chat_room_settings).setOnClickListener(this);
    }

    @Override
    protected void onAllPermissionGranted() {
        // Nothing to be done here.
    }

    @Override
    public void onChannelItemClicked(int pos, ChannelListAdapter.ChannelItem item) {
        UserAccountManager.UserAccount account = myAccount();
        Intent intent = new Intent();
        intent.setClass(this, ChatActivity.class);
        intent.putExtra(Constants.BUNDLE_KEY_UID, account.getUid());
        intent.putExtra(Constants.BUNDLE_KEY_PORTRAIT_RES, account.getAvatarRes());
        intent.putExtra(Constants.BUNDLE_KEY_CHANNEL_NAME, item.getName());
        intent.putExtra(Constants.BUNDLE_KEY_BG_RES, item.getRoomBgRes());
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        mSwipeList.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeList.setRefreshing(false);
            }
        }, SWIPE_TIMEOUT);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.chat_room_settings)
        {
            onSettingClicked(v);
        }
    }

    private void onSettingClicked(View view)
    {
        ScreenHeightDialog dialog = new ScreenHeightDialog(this);
        dialog.show(R.layout.dialog_room_config1, ScreenHeightDialog.DIALOG_WIDE,
                Gravity.END, new ScreenHeightDialog.OnDialogListener() {
                    @Override
                    public void onDialogShow(final AlertDialog dialog) {
                        if (dialog.getWindow() == null) {
                            return;
                        }

                        View.OnClickListener listener = new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                switch (view.getId()) {
                                    case R.id.config_room_ai_ns_point:
                                        openANCDialog();
                                        break;
                                    case R.id.config_room_btn_quit:
                                        finish();
                                        break;
                                    default:
                                        break;
                                }
                            }
                        };
                        dialog.findViewById(R.id.config_room_ai_ns_point).setOnClickListener(listener);
                    }
                });
    }

    private void openANCDialog() {
        AINoiseDialog aiNoiseDialog = new AINoiseDialog(this);
        aiNoiseDialog.setAiNoiseListener(this);
        aiNoiseDialog.show();
    }

    @Override
    public void onAction(boolean ANCSwitch, int archIndex, int ANCRange) {
        Log.e(TAG, "archIndex:" + archIndex + ", ANCRange:" + ANCRange);
        SoundSettingUtil.setANC(rtcEngine(), ANCSwitch, archIndex, ANCRange);
    }
}
