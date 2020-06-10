package io.agora.highqualityaudio;

import android.app.Application;

import io.agora.highqualityaudio.data.UserAccountManager;
import io.agora.highqualityaudio.rtc.AgoraEventHandler;
import io.agora.rtc.RtcEngine;

public class AgoraApplication extends Application
{

    private final UserAccountManager mAccountManager = UserAccountManager.INSTANCE;
    private RtcEngine mRtcEngine;
    private final AgoraEventHandler mHandler = new AgoraEventHandler();

    @Override
    public void onCreate()
    {
        super.onCreate();
        try
        {
            mRtcEngine = RtcEngine.create(getApplicationContext(), getString(R.string.app_id), mHandler);
            /**启用说话者音量提示;
             * @param 指定音量提示的时间间隔;@param 指定音量提示的灵敏度; @param 是否开启本地人声检测功能*/
            mRtcEngine.enableAudioVolumeIndication(
                    io.agora.highqualityaudio.utils.Constants.VOLUME_INDICATE_INTERVAL,
                    io.agora.highqualityaudio.utils.Constants.VOLUME_INDICATE_SMOOTH, true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
        RtcEngine.destroy();
    }

    public UserAccountManager.UserAccount myAccount()
    {
        return mAccountManager.account();
    }

    public RtcEngine engine()
    {
        return mRtcEngine;
    }

    public AgoraEventHandler handler()
    {
        return mHandler;
    }
}
