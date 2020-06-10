package io.agora.highqualityaudio.utils;

import io.agora.rtc.RtcEngine;

import static io.agora.rtc.Constants.AUDIO_REVERB_FX_KTV;
import static io.agora.rtc.Constants.AUDIO_REVERB_FX_PHONOGRAPH;
import static io.agora.rtc.Constants.AUDIO_REVERB_FX_POPULAR;
import static io.agora.rtc.Constants.AUDIO_REVERB_FX_RNB;
import static io.agora.rtc.Constants.AUDIO_REVERB_FX_SISTER;
import static io.agora.rtc.Constants.AUDIO_REVERB_FX_STUDIO;
import static io.agora.rtc.Constants.AUDIO_REVERB_FX_UNCLE;
import static io.agora.rtc.Constants.AUDIO_REVERB_FX_VOCAL_CONCERT;
import static io.agora.rtc.Constants.AUDIO_REVERB_HIPHOP;
import static io.agora.rtc.Constants.AUDIO_REVERB_KTV;
import static io.agora.rtc.Constants.AUDIO_REVERB_OFF;
import static io.agora.rtc.Constants.AUDIO_REVERB_POPULAR;
import static io.agora.rtc.Constants.AUDIO_REVERB_RNB;
import static io.agora.rtc.Constants.AUDIO_REVERB_ROCK;
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
import static io.agora.rtc.Constants.VOICE_CHANGER_OFF;
import static io.agora.rtc.Constants.VOICE_CHANGER_OLDMAN;
import static io.agora.rtc.Constants.VOICE_CHANGER_ZHUBAJIE;

public class SoundEffectUtil
{
    public static final int EFFECT_NONE = 0;

    private static final int[] BEAUTIFYVOICES = new int[]{
            VOICE_CHANGER_OFF,
            GENERAL_BEAUTY_VOICE_FEMALE_VITALITY,
            GENERAL_BEAUTY_VOICE_FEMALE_FRESH,
            GENERAL_BEAUTY_VOICE_MALE_MAGNETIC,
            VOICE_BEAUTY_VIGOROUS,
            VOICE_BEAUTY_DEEP,
            VOICE_BEAUTY_MELLOW,
            VOICE_BEAUTY_FALSETTO,
            VOICE_BEAUTY_FULL,
            VOICE_BEAUTY_CLEAR,
            VOICE_BEAUTY_RESOUNDING,
            VOICE_BEAUTY_RINGING,
    };

    private static final int[] VOICEEFFECTS = new int[]{
            AUDIO_REVERB_OFF,
            AUDIO_REVERB_KTV,
            AUDIO_REVERB_FX_KTV,
            AUDIO_REVERB_VOCAL_CONCERT,
            AUDIO_REVERB_FX_VOCAL_CONCERT,
            AUDIO_REVERB_STUDIO,
            AUDIO_REVERB_FX_STUDIO,
            AUDIO_REVERB_FX_PHONOGRAPH,
            AUDIO_VIRTUAL_STEREO,
            AUDIO_REVERB_FX_UNCLE,
            AUDIO_REVERB_FX_SISTER,
            AUDIO_REVERB_RNB,
            AUDIO_REVERB_FX_RNB,
            AUDIO_REVERB_POPULAR,
            AUDIO_REVERB_FX_POPULAR,
            AUDIO_REVERB_ROCK,
            AUDIO_REVERB_HIPHOP,
            VOICE_BEAUTY_SPACIAL,
            VOICE_CHANGER_ETHEREAL,
            VOICE_CHANGER_OLDMAN,
            VOICE_CHANGER_BABYBOY,
            VOICE_CHANGER_BABYGIRL,
            VOICE_CHANGER_ZHUBAJIE,
            VOICE_CHANGER_HULK,
    };

    public static void beautifyVoice(RtcEngine engine, int index)
    {
        if (index < 0 || index >= BEAUTIFYVOICES.length)
        {
            return;
        }
        engine.setLocalVoiceChanger(BEAUTIFYVOICES[index]);
    }

    public static void voiceEffect(RtcEngine engine, int index)
    {
        if (index < 0 || index >= VOICEEFFECTS.length)
        {
            return;
        }
        else if(index > 16)
        {
            engine.setLocalVoiceChanger(VOICEEFFECTS[index]);
        }
        else
        {
            engine.setLocalVoiceReverbPreset(VOICEEFFECTS[index]);
        }
    }
}
