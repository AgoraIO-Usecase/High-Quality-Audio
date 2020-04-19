package io.agora.highqualityaudio.utils;

import io.agora.rtc.RtcEngine;

import static io.agora.rtc.Constants.*;

public class VoiceEffectUtil {
    public static final int[] VOICE_CHANGE_PRESET = {
            VOICE_CHANGER_OFF,
            VOICE_BEAUTY_VIGOROUS,
            VOICE_BEAUTY_DEEP,
            VOICE_BEAUTY_MELLOW,
            VOICE_BEAUTY_FALSETTO,
            VOICE_BEAUTY_FULL,
            VOICE_BEAUTY_CLEAR,
            VOICE_BEAUTY_RESOUNDING,
            VOICE_BEAUTY_RINGING,
            VOICE_BEAUTY_SPACIAL
    };

    public static final int[] VOICE_BEAUTY_PRESET = {
            VOICE_CHANGER_OFF,
            GENERAL_BEAUTY_VOICE_MALE_MAGNETIC,
            GENERAL_BEAUTY_VOICE_FEMALE_FRESH,
            GENERAL_BEAUTY_VOICE_FEMALE_VITALITY,
            GENERAL_BEAUTY_SING_MALE,
            GENERAL_BEAUTY_SING_FEMALE
    };

    public static final int[] SOUND_EFFECT_PRESET = {
            VOICE_CHANGER_OFF,
            AUDIO_REVERB_FX_KTV,
            AUDIO_REVERB_FX_VOCAL_CONCERT,
            AUDIO_REVERB_FX_UNCLE,
            AUDIO_REVERB_FX_SISTER,
            AUDIO_REVERB_FX_STUDIO,
            AUDIO_REVERB_FX_POPULAR,
            AUDIO_REVERB_FX_RNB,
            AUDIO_REVERB_FX_PHONOGRAPH
    };

    public static final int[] STEREO_PRESET = {
            VOICE_CHANGER_OFF,
            AUDIO_VIRTUAL_STEREO
    };

    public static void changeVoice(RtcEngine engine, int index) {
        if (index < 0 || index >= VOICE_CHANGE_PRESET.length) return;
        engine.setLocalVoiceChanger(VOICE_CHANGE_PRESET[index]);
    }

    public static void beautifyVoice(RtcEngine engine, int index) {
        if (index < 0 || index >= VOICE_BEAUTY_PRESET.length) return;
        engine.setLocalVoiceChanger(VOICE_BEAUTY_PRESET[index]);
    }

    public static void changeSoundEffect(RtcEngine engine, int index) {
        if (index < 0 || index >= SOUND_EFFECT_PRESET.length) return;
        engine.setLocalVoiceReverbPreset(SOUND_EFFECT_PRESET[index]);
    }

    public static void changeSoundStereo(RtcEngine engine, int index) {
        if (index < 0 || index >= STEREO_PRESET.length) return;
        engine.setLocalVoiceReverbPreset(STEREO_PRESET[index]);
    }
}
