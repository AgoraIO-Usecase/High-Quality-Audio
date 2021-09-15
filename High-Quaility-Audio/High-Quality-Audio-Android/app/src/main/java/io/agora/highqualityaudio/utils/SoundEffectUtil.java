package io.agora.highqualityaudio.utils;

import io.agora.rtc.Constants;
import io.agora.rtc.RtcEngine;

public class SoundEffectUtil {
    public static ExampleVoiceConversion currentConversion = ExampleVoiceConversion.ORIGINAL;
    public static ExampleVoiceEffect currentEffect = ExampleVoiceEffect.ORIGINAL;
    public static ExampleVoiceBeautifier currentBeautifier = ExampleVoiceBeautifier.ORIGINAL;

    // 音效
    public static enum ExampleVoiceEffect {
        // 原生
        ORIGINAL(Constants.AUDIO_EFFECT_OFF),
        // 男孩
        BOY(Constants.VOICE_CHANGER_EFFECT_BOY),
        // 爷爷
        OLD_MAN(Constants.VOICE_CHANGER_EFFECT_OLDMAN),
        // 女孩
        GIRL(Constants.VOICE_CHANGER_EFFECT_GIRL),
        // 大叔
        UNCLE(Constants.VOICE_CHANGER_EFFECT_UNCLE),
        // 少女
        SISTER(Constants.VOICE_CHANGER_EFFECT_SISTER),
        // 猪八戒
        PIG_KING(Constants.VOICE_CHANGER_EFFECT_PIGKING),
        // 绿巨人
        HULK(Constants.VOICE_CHANGER_EFFECT_HULK),
        // KTV
        KTV(Constants.ROOM_ACOUSTICS_KTV),
        // 演唱会
        CONCERT(Constants.ROOM_ACOUSTICS_VOCAL_CONCERT),
        // 录音棚
        STUDIO(Constants.ROOM_ACOUSTICS_STUDIO),
        // 留声机
        PHONOGRAPH(Constants.ROOM_ACOUSTICS_PHONOGRAPH),
        // 空旷
        SPACIAL(Constants.ROOM_ACOUSTICS_SPACIAL),
        // 空灵
        ETHEREAL(Constants.ROOM_ACOUSTICS_ETHEREAL),
        // 流行
        POPULAR(Constants.STYLE_TRANSFORMATION_POPULAR),
        // R&B
        RNB(Constants.STYLE_TRANSFORMATION_RNB);

        int value;

        private ExampleVoiceEffect(int value) {
            this.value = value;
        }
    }

    // 美声
    public static enum ExampleVoiceBeautifier {
        // 原声
        ORIGINAL(Constants.VOICE_BEAUTIFIER_OFF),
        // 磁性
        MAGNETIC(Constants.CHAT_BEAUTIFIER_MAGNETIC),
        // 清新
        FRESH(Constants.CHAT_BEAUTIFIER_FRESH),
        // 活力
        VITALITY(Constants.CHAT_BEAUTIFIER_VITALITY),
        // 浑厚
        VIGOROUS(Constants.TIMBRE_TRANSFORMATION_VIGOROUS),
        // 低沉
        DEEP(Constants.TIMBRE_TRANSFORMATION_DEEP),
        // 圆润
        MELLOW(Constants.TIMBRE_TRANSFORMATION_MELLOW),
        // 假音
        FALSETTO(Constants.TIMBRE_TRANSFORMATION_FALSETTO),
        // 饱满
        FULL(Constants.TIMBRE_TRANSFORMATION_FULL),
        // 清澈
        CLEAR(Constants.TIMBRE_TRANSFORMATION_CLEAR),
        // 高亢
        RESOUNDING(Constants.TIMBRE_TRANSFORMATION_RESOUNDING),
        // 嘹亮
        RINGING(Constants.TIMBRE_TRANSFORMATION_RINGING);

        int value;

        private ExampleVoiceBeautifier(int value) {
            this.value = value;
        }
    }

    // 变声
    public static enum ExampleVoiceConversion {
        // 原声
        ORIGINAL(Constants.VOICE_CONVERSION_OFF),
        // 低沉
        BASS(Constants.VOICE_CHANGER_BASS),
        // 中性
        NEUTRAL(Constants.VOICE_CHANGER_NEUTRAL),
        // 稳重
        SOLID(Constants.VOICE_CHANGER_SOLID),
        // 甜美
        SWEET(Constants.VOICE_CHANGER_SWEET);

        int value;

        private ExampleVoiceConversion(int value) {
            this.value = value;
        }
    }

    /**
     * 音效
     *
     * <p>
     * 设置 SDK 预设的人声音效。
     * 自从 v3.2.0
     * 调用该方法可以为本地发流用户设置 SDK 预设的人声音效，且不会改变原声的性别特征。设置音效后，频道 内所有用户都能听到该效果。
     * <p>
     * 根据不同的场景，你可以为用户设置不同的音效，各音效的适用场景可参考《设置人声效果》。
     * <p>
     * 为获取更好的人声效果，Agora 推荐你在调用该方法前将 setAudioProfile 的 scenario 设为 AUDIO_SCENARIO_GAME_STREAMING(3)。
     */
    public static void changeVoiceEffect(RtcEngine engine, ExampleVoiceEffect effect) {
        engine.setAudioEffectPreset(effect.value);
    }

    /**
     * 设置 SDK 预设的美声效果。
     * <p>
     * 自从
     * v3.2.0
     * 调用该方法可以为本地发流用户设置 SDK 预设的人声美化效果。
     * 设置美声效果后，频道内所有用户都能听到该效果。根据不同的场景，你可以为用户设置不同的美声效果，各美声效果的适用场景可参考《设置人声效果》。
     * <p>
     * 为获取更好的人声效果
     * Agora 推荐你在调用该方法前将 setAudioProfile 的 scenario 设为 AUDIO_SCENARIO_GAME_STREAMING(3)
     * 并将 profile 设为 AUDIO_PROFILE_MUSIC_HIGH_QUALITY(4) 或 AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO(5)。
     */
    public static void changeVoiceBeautifier(RtcEngine engine, ExampleVoiceBeautifier beautifier) {
        engine.setVoiceBeautifierPreset(beautifier.value);
    }

    /**
     *
     * 设置 SDK 预设的变声效果。
     *
     * 自从
     * v3.3.1
     * 调用该方法可以为本地发流用户设置 SDK 预设的变声效果。
     * 设置变声效果后，频道内所有用户都能听到该效果。
     * 根据不同的场景，你可以为用户设置不同的变声效果，各变声效果的适用场景可参考《设置人声效果》。
     *
     * 为获取更好的人声效果，Agora 推荐你在调用该方法前将 setAudioProfile 的 scenario 设为 AUDIO_SCENARIO_GAME_STREAMING(3)
     * 并将 profile 设为 AUDIO_PROFILE_MUSIC_HIGH_QUALITY(4) 或 AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO(5)。
     */
    public static void changeVoiceConversion(RtcEngine engine, ExampleVoiceConversion conversion) {
        engine.setVoiceConversionPreset(conversion.value);
    }
}
