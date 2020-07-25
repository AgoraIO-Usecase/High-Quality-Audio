package io.agora.highqualityaudio.utils;

import android.text.TextUtils;

import io.agora.rtc.RtcEngine;

import static io.agora.highqualityaudio.utils.Constants.ANC_ARCH;
import static io.agora.highqualityaudio.utils.Constants.ANC_RANGE;
import static io.agora.highqualityaudio.utils.Constants.ANC_SWITCH;
import static io.agora.highqualityaudio.utils.Constants.SECOND_CATEGORY;
import static io.agora.highqualityaudio.utils.Constants.VOICE_INDEX;
import static io.agora.rtc.Constants.AUDIO_REVERB_FX_KTV;
import static io.agora.rtc.Constants.AUDIO_REVERB_FX_PHONOGRAPH;
import static io.agora.rtc.Constants.AUDIO_REVERB_FX_POPULAR;
import static io.agora.rtc.Constants.AUDIO_REVERB_FX_RNB;
import static io.agora.rtc.Constants.AUDIO_REVERB_FX_SISTER;
import static io.agora.rtc.Constants.AUDIO_REVERB_FX_STUDIO;
import static io.agora.rtc.Constants.AUDIO_REVERB_FX_UNCLE;
import static io.agora.rtc.Constants.AUDIO_REVERB_FX_VOCAL_CONCERT;
import static io.agora.rtc.Constants.AUDIO_REVERB_KTV;
import static io.agora.rtc.Constants.AUDIO_REVERB_POPULAR;
import static io.agora.rtc.Constants.AUDIO_REVERB_RNB;
import static io.agora.rtc.Constants.AUDIO_REVERB_STUDIO;
import static io.agora.rtc.Constants.AUDIO_REVERB_VOCAL_CONCERT;
import static io.agora.rtc.Constants.AUDIO_VIRTUAL_STEREO;
import static io.agora.rtc.Constants.GENERAL_BEAUTY_VOICE_FEMALE_FRESH;
import static io.agora.rtc.Constants.GENERAL_BEAUTY_VOICE_FEMALE_VITALITY;
import static io.agora.rtc.Constants.GENERAL_BEAUTY_VOICE_MALE_MAGNETIC;
import static io.agora.rtc.Constants.VOICE_BEAUTY_CLEAR;
import static io.agora.rtc.Constants.VOICE_BEAUTY_DEEP;
import static io.agora.rtc.Constants.VOICE_BEAUTY_FALSETTO;
import static io.agora.rtc.Constants.VOICE_BEAUTY_FULL;
import static io.agora.rtc.Constants.VOICE_BEAUTY_MELLOW;
import static io.agora.rtc.Constants.VOICE_BEAUTY_RESOUNDING;
import static io.agora.rtc.Constants.VOICE_BEAUTY_RINGING;
import static io.agora.rtc.Constants.VOICE_BEAUTY_SPACIAL;
import static io.agora.rtc.Constants.VOICE_BEAUTY_VIGOROUS;
import static io.agora.rtc.Constants.VOICE_CHANGER_BABYBOY;
import static io.agora.rtc.Constants.VOICE_CHANGER_BABYGIRL;
import static io.agora.rtc.Constants.VOICE_CHANGER_ETHEREAL;
import static io.agora.rtc.Constants.VOICE_CHANGER_HULK;
import static io.agora.rtc.Constants.VOICE_CHANGER_OLDMAN;
import static io.agora.rtc.Constants.VOICE_CHANGER_ZHUBAJIE;

/**
 * @author cjw
 */
public class SoundSettingUtil
{
    public static final int EFFECT_NONE = 0;
    /**Large category ID,Corresponding to Beautify Voice、Voice Effect、Voice Changer.*/
    public static final int[] FIRSTCATEGORYID = new int[]{0, 1, 2};
    /**Second category ID,Corresponding to Chat、Sing、Timbre、Space、Change、Qufeng、Electric、Magic*/
    public static final int[] SECONDCATEGORYID = new int[]{0, 1, 2, 3, 4, 5, 6, 7};

    private static final int[] CHATVOICES = new int[]{
            GENERAL_BEAUTY_VOICE_FEMALE_VITALITY,
            GENERAL_BEAUTY_VOICE_FEMALE_FRESH,
            GENERAL_BEAUTY_VOICE_MALE_MAGNETIC
    };

    private static final int[] SINGVOICES = new int[]{
    };

    private static final int[] TIMBREVOICES = new int[]{
            VOICE_BEAUTY_VIGOROUS,
            VOICE_BEAUTY_DEEP,
            VOICE_BEAUTY_MELLOW,
            VOICE_BEAUTY_FALSETTO,
            VOICE_BEAUTY_FULL,
            VOICE_BEAUTY_CLEAR,
            VOICE_BEAUTY_RESOUNDING,
            VOICE_BEAUTY_RINGING
    };

    private static final int[] SPACEVOICES = new int[]{
            AUDIO_REVERB_KTV,
            AUDIO_REVERB_FX_KTV,
            AUDIO_REVERB_VOCAL_CONCERT,
            AUDIO_REVERB_FX_VOCAL_CONCERT,
            AUDIO_REVERB_STUDIO,
            AUDIO_REVERB_FX_STUDIO,
            AUDIO_REVERB_FX_PHONOGRAPH,
            AUDIO_VIRTUAL_STEREO,
            VOICE_BEAUTY_SPACIAL,
            VOICE_CHANGER_ETHEREAL
    };

    private static final int[] CHANGEVOICES = new int[]{
            AUDIO_REVERB_FX_UNCLE,
            AUDIO_REVERB_FX_SISTER,
            VOICE_CHANGER_OLDMAN,
            VOICE_CHANGER_BABYBOY,
            VOICE_CHANGER_BABYGIRL,
            VOICE_CHANGER_ZHUBAJIE,
            VOICE_CHANGER_HULK,
            AUDIO_REVERB_RNB,
            AUDIO_REVERB_FX_RNB,
            AUDIO_REVERB_POPULAR,
            AUDIO_REVERB_FX_POPULAR,
    };

    private static final int[] QUFENGVOICES = new int[]{
            AUDIO_REVERB_RNB,
            AUDIO_REVERB_FX_RNB,
            AUDIO_REVERB_POPULAR,
            AUDIO_REVERB_FX_POPULAR,
    };

    public static final int[][] SECONDCATEGORYVALUES = new int[][]{CHANGEVOICES, SINGVOICES, TIMBREVOICES, SPACEVOICES, CHANGEVOICES, QUFENGVOICES};

    public static void setVoice(RtcEngine engine, int firstCategoryId, int secondCategoryId, int index) {
        /**保存最终选择的二级分类*/
        PreferenceManager.put(SECOND_CATEGORY, secondCategoryId);
        if (secondCategoryId == SECONDCATEGORYID[0]) {
            engine.setLocalVoiceChanger(CHATVOICES[index]);
        } else if (secondCategoryId == SECONDCATEGORYID[1]) {
        } else if (secondCategoryId == SECONDCATEGORYID[2]) {
            engine.setLocalVoiceChanger(TIMBREVOICES[index]);
        } else if (secondCategoryId == SECONDCATEGORYID[3]) {
            if (index < 8) {
                engine.setLocalVoiceReverbPreset(SPACEVOICES[index]);
            } else {
                engine.setLocalVoiceChanger(SPACEVOICES[index]);
            }
        } else if (secondCategoryId == SECONDCATEGORYID[4]) {
            if (index < 2) {
                engine.setLocalVoiceReverbPreset(CHANGEVOICES[index]);
            } else if (index > 1 && index < 7) {
                engine.setLocalVoiceChanger(CHANGEVOICES[index]);
            } else {
                engine.setLocalVoiceReverbPreset(CHANGEVOICES[index]);
            }
        } else if (secondCategoryId == SECONDCATEGORYID[5]) {
            engine.setLocalVoiceReverbPreset(QUFENGVOICES[index]);
        } else if (secondCategoryId == SECONDCATEGORYID[6]) {
        } else if (secondCategoryId == SECONDCATEGORYID[7]) {
        }
        /**保存最终选择的音效（VoiceChanger或VoiceReverbPreset）下标*/
        PreferenceManager.put(VOICE_INDEX, index);
    }

    /**设置降噪属性*/
    public static void setANC(RtcEngine engine, boolean ANCSwitch, int arch, int ANCRange)
    {
        PreferenceManager.put(ANC_SWITCH, ANCSwitch);
        PreferenceManager.put(ANC_ARCH, arch);
        PreferenceManager.put(ANC_RANGE, ANCRange);
        /**关闭AI降噪相当于只开传统降噪*/
        if(!ANCSwitch)
        {
            engine.setParameters("{\"che.audio.ns.mode\":0}");
        }
        else
        {
            engine.setParameters("{\"che.audio.ns.mode\":" + arch + "}");
//            engine.setParameters("{\"che.audio.ains.hl\":" + ANCRange + "}");
        }
    }

    /**尝试读取持久化存储的设置数据*/
    public static void initEngineSoundSetting(RtcEngine engine)
    {
        int secondCategory = PreferenceManager.get(SECOND_CATEGORY, -1);
        int voiceIndex = PreferenceManager.get(VOICE_INDEX, -1);
        setVoice(engine, -1, secondCategory, voiceIndex);
        boolean ANCSwitch = PreferenceManager.get(ANC_SWITCH, false);
        int arch = PreferenceManager.get(ANC_ARCH, -1);
        int ANCRange = PreferenceManager.get(ANC_RANGE, -1);
        setANC(engine, ANCSwitch, arch, ANCRange);
    }
}
