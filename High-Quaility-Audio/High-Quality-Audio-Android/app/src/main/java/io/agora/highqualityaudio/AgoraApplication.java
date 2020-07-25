package io.agora.highqualityaudio;

import android.app.Application;

import io.agora.highqualityaudio.data.UserAccountManager;
import io.agora.highqualityaudio.rtc.AgoraEventHandler;
import io.agora.highqualityaudio.utils.PreferenceManager;
import io.agora.highqualityaudio.utils.SoundSettingUtil;
import io.agora.rtc.RtcEngine;

public class AgoraApplication extends Application
{

    private RtcEngine mRtcEngine;
    private final AgoraEventHandler mHandler = new AgoraEventHandler();

    @Override
    public void onCreate()
    {
        super.onCreate();
        try
        {
            mRtcEngine = RtcEngine.create(getApplicationContext(), getString(R.string.app_id), mHandler);
            /**Enables the onAudioVolumeIndication callback at a set time interval to report on which
             *  users are speaking and the speakers' volume.;
             * @param interval Sets the time interval between two consecutive volume indications
             * @param smooth The smoothing factor sets the sensitivity of the audio volume indicator. ;
             * @param report_vad Whether to enable the local voice detection function*/
            mRtcEngine.enableAudioVolumeIndication(
                    io.agora.highqualityaudio.utils.Constants.VOLUME_INDICATE_INTERVAL,
                    io.agora.highqualityaudio.utils.Constants.VOLUME_INDICATE_SMOOTH, true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        PreferenceManager.init(this);
        /**取出之前可能存在的设置项并设置进Engine中*/
        SoundSettingUtil.initEngineSoundSetting(mRtcEngine);
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
        RtcEngine.destroy();
    }

    public UserAccountManager.UserAccount myAccount()
    {
        return UserAccountManager.account();
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
