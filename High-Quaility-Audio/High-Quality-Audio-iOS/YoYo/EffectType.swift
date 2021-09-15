//
//  EffectType.swift
//   Agora-RTC-With-Voice-Changer-iOS
//
//  Created by CavanSu on 14/03/2018.
//  Copyright © 2018 Agora. All rights reserved.
//

import UIKit
import AgoraRtcKit

protocol CSDescriptable {
    func description() -> String
}

class EffectType {
    static func rolesList() -> [AgoraAudioEffectPreset] {
        return [.audioEffectOff,
                .voiceChangerEffectBoy,
                .voiceChangerEffectOldMan,
                .voiceChangerEffectGirl,
                .voiceChangerEffectUncle,
                .voiceChangerEffectSister,
                .voiceChangerEffectPigKing,
                .voiceChangerEffectHulk,
                .roomAcousticsKTV,
                .roomAcousticsVocalConcert,
                .roomAcousticsStudio,
                .roomAcousticsPhonograph,
                .roomAcousticsSpacial,
                .roomAcousticsEthereal,
                .styleTransformationPopular,
                .styleTransformationRnB]
    }
    static func beautyVoiceList() -> [AgoraVoiceBeautifierPreset] {
        return [.voiceBeautifierOff,
                .chatBeautifierMagnetic,
                .chatBeautifierFresh,
                .chatBeautifierVitality,
                .timbreTransformationVigorous,
                .timbreTransformationDeep,
                .timbreTransformationMellow,
                .timbreTransformationFalsetto,
                .timbreTransformationFull,
                .timbreTransformationClear,
                .timbreTransformationResounding,
                .timbreTransformationRinging]
    }
    
    static func conversionList() -> [AgoraVoiceConversionPreset] {
        return [.conversionOff,
                .changerBass,
                .changerNeutral,
                .changerSolid,
                .changerSweet]
    }
}

extension AgoraVoiceConversionPreset {
    func description() -> String {
        switch self {
        case .conversionOff:    return "原声"
        case .changerBass:      return "低沉"
        case .changerNeutral:   return "中性"
        case .changerSolid:     return "稳重"
        case .changerSweet:     return "甜美"
        default:
            return "原声"
        }
    }
    
    static func fmDefault(with agoraKit: AgoraRtcEngineKit) {
        conversion(with: agoraKit, type: .conversionOff)
    }

    static func conversion(with agoraKit: AgoraRtcEngineKit, type:AgoraVoiceConversionPreset) {
        agoraKit.setVoiceConversionPreset(type)
    }

    func character(with agoraKit: AgoraRtcEngineKit) {
        AgoraVoiceConversionPreset.conversion(with: agoraKit, type: self)
    }
}

extension AgoraVoiceBeautifierPreset {
    func description() -> String {
        switch self {
        case .voiceBeautifierOff:               return "原声"
        case .chatBeautifierMagnetic:           return "磁性"
        case .chatBeautifierFresh:              return "清新"
        case .chatBeautifierVitality:           return "活力"
        case .timbreTransformationVigorous:     return "浑厚"
        case .timbreTransformationDeep:         return "低沉"
        case .timbreTransformationMellow:       return "圆润"
        case .timbreTransformationFalsetto:     return "假音"
        case .timbreTransformationFull:         return "饱满"
        case .timbreTransformationClear:        return "清澈"
        case .timbreTransformationResounding:   return "高亢"
        case .timbreTransformationRinging:      return "嘹亮"
        default:
            return "原声"
        }
    }
    
    static func fmDefault(with agoraKit: AgoraRtcEngineKit) {
        beautifulVoice(with: agoraKit, type: .voiceBeautifierOff)
    }

    static func beautifulVoice(with agoraKit: AgoraRtcEngineKit, type:AgoraVoiceBeautifierPreset) {
        agoraKit.setVoiceBeautifierPreset(type)
    }

    func character(with agoraKit: AgoraRtcEngineKit) {
        AgoraVoiceBeautifierPreset.beautifulVoice(with: agoraKit, type: self)
    }
}

extension AgoraAudioEffectPreset {
    func description() -> String {
        switch self {
        case .audioEffectOff:                   return "原声"
        case .voiceChangerEffectBoy:            return "男孩"
        case .voiceChangerEffectOldMan:         return "老年男性"
        case .voiceChangerEffectGirl:           return "女孩"
        case .voiceChangerEffectUncle:          return "大叔"
        case .voiceChangerEffectSister:         return "少女"
        case .voiceChangerEffectPigKing:        return "猪八戒"
        case .voiceChangerEffectHulk:           return "绿巨人"
        case .roomAcousticsKTV:                 return "KTV"
        case .roomAcousticsVocalConcert:        return "演唱会"
        case .roomAcousticsStudio:              return "录音棚"
        case .roomAcousticsPhonograph:          return "留声机"
        case .roomAcousticsSpacial:             return "空旷"
        case .roomAcousticsEthereal:            return "空灵"
        case .styleTransformationPopular:       return "流行"
        case .styleTransformationRnB:           return "R&B"
        default:
            return "原声"
        }
    }
    
    static func fmDefault(with agoraKit: AgoraRtcEngineKit) {
        changeVoice(with: agoraKit, type: .audioEffectOff)
    }

    static func changeVoice(with agoraKit: AgoraRtcEngineKit, type:AgoraAudioEffectPreset) {
        agoraKit.setAudioEffectPreset(type)
    }

    func character(with agoraKit: AgoraRtcEngineKit) {
        AgoraAudioEffectPreset.changeVoice(with: agoraKit, type: self)
    }
}
