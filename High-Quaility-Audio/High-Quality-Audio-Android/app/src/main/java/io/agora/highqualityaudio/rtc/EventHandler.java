package io.agora.highqualityaudio.rtc;

import io.agora.rtc.IRtcEngineEventHandler;

public interface EventHandler {
    void onJoinChannelSuccess(String channel, int uid, int elapsed);
    void onLeaveChannel(IRtcEngineEventHandler.RtcStats stats);
    void onUserJoined(int uid, int elapsed);
    void onUserOffline(int uid, int reason);
    void onAudioVolumeIndication(IRtcEngineEventHandler.AudioVolumeInfo[] speakers, int totalVolume);
    void onRemoteAudioStateChanged(int uid, int state, int reason, int elapsed);
    void onRemoteAudioStats(IRtcEngineEventHandler.RemoteAudioStats stats);
    void onClientRoleChanged(int oldRole, int newRole);
}
