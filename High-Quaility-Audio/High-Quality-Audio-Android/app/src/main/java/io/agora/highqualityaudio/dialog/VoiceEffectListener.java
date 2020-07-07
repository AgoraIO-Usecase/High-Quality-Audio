package io.agora.highqualityaudio.dialog;

public interface VoiceEffectListener {
    /**@param firstCategoryId Large category ID
     * @param secondCategoryId second Category ID
     * @param index the index of voiceEffects*/
    void onVoiceSelect(int firstCategoryId, int secondCategoryId, int index);
}
