//
//  VoiceConversionViewController.swift
//  YoYo
//
//  Created by xianing on 2021/9/5.
//  Copyright © 2021 CavanSu. All rights reserved.
//

import Foundation
import AgoraRtcKit


protocol VoiceConversionVCDelegate: NSObjectProtocol {
    func VoiceConversionVC(_ vc: VoiceConversionViewController, didSelected role: AgoraVoiceConversionPreset, roleIndex:Int)
    func VoiceConversionVCDidCancel(_ vc: VoiceConversionViewController)
}

class VoiceConversionViewController: UIViewController {
    @IBOutlet weak var collectionView: UICollectionView!
    
    private lazy var rolesList: [AgoraVoiceConversionPreset] = EffectType.conversionList()
    weak var delegate: VoiceConversionVCDelegate?
    var selectedIndex: Int?
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: true)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        updateViews()
        updateCollectionViewLayout()
    }
    
    @IBAction func doConfirmPressed(_ sender: UIButton) {
        if let selectedIndex = selectedIndex {
            let role = rolesList[selectedIndex]
            delegate?.VoiceConversionVC(self, didSelected: role, roleIndex: selectedIndex)
        } else {
            delegate?.VoiceConversionVCDidCancel(self)
        }
    }
    
    @IBAction func doCancelPressed(_ sender: UIButton) {
        delegate?.VoiceConversionVCDidCancel(self)
    }
}

private extension VoiceConversionViewController {
    func updateViews() {
        self.navigationItem.title = "变声"
    }
    
    func updateCollectionViewLayout() {
        let itemWidth = UIScreen.main.bounds.size.width * 0.25 * DeviceAdapt.getWidthCoefficient()
        let itemHeight = CGFloat(30)
        let space = 36.0 * DeviceAdapt.getWidthCoefficient()
        
        let layout = UICollectionViewFlowLayout()
        layout.itemSize = CGSize(width: itemWidth, height: itemHeight)
        layout.sectionInset = UIEdgeInsets(top: 0, left: space, bottom: 0, right: space)
        collectionView.setCollectionViewLayout(layout, animated: false)
    }
}

extension VoiceConversionViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return rolesList.count
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "VoiceChangerCell", for: indexPath) as! VoiceChangerCell
        let role = rolesList[indexPath.item]
        cell.roleLabel.text = role.description()
       
        if let _ = selectedIndex, selectedIndex == indexPath.item {
            cell.isRoleSelected = true
        } else {
            cell.isRoleSelected = false
        }
        return cell
    }
}

extension VoiceConversionViewController: UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        if let selected = selectedIndex, selected == indexPath.item {
            let index = IndexPath(item: selected, section: 0)
            selectedIndex = indexPath.item
            selectedIndex = nil
            collectionView.reloadItems(at: [index])
            return
        }
        
        if let selected = selectedIndex {
            let index = IndexPath(item: selected, section: 0)
            selectedIndex = indexPath.item
            collectionView.reloadItems(at: [index])
        } else {
            selectedIndex = indexPath.item
        }
        
        collectionView.reloadItems(at: [indexPath])
    }
}

