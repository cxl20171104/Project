package com.alphasta.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.mapper.QrcodeMapper;
import com.alphasta.model.Dict;
import com.alphasta.model.Qrcode;
import com.alphasta.model.QrcodeUser;
import com.alphasta.model.Score;
import com.alphasta.service.DictService;
import com.alphasta.service.QrcodeService;
import com.alphasta.service.ScoreService;

@Service
public class QrcodeServicelmpl implements QrcodeService {

	@Autowired
	private QrcodeMapper qrcodeMapper;
	@Autowired
	private DictService dictService;
	@Autowired
	private ScoreService scoreService;

	@Override
	public List<Qrcode> findQrcodelist(Qrcode qrcode) {
		// TODO Auto-generated method stub
		return qrcodeMapper.findQrcodelist(qrcode);
	}

	@Override
	public void delectQrcode(String id) {
		// TODO Auto-generated method stub
		qrcodeMapper.delectQrcode(id);
	}

	@Override
	public void insertQrcode(Qrcode qrcode) {
		// TODO Auto-generated method stub
		qrcodeMapper.insertQrcode(qrcode);
	}

	@Override
	public void updateQrcode(Qrcode qrcode) {
		// TODO Auto-generated method stub
		qrcodeMapper.updateQrcode(qrcode);
	}

	@Override
	public List<QrcodeUser> findHasQrcodeUsers(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return qrcodeMapper.findHasQrcodeUsers(map);
	}

	@Override
	public void insertQrcodeUser(QrcodeUser qrcodeUser, Score score) {
		// TODO Auto-generated method stub
		scoreService.addScore(score);
		qrcodeMapper.insertQrcodeUser(qrcodeUser);
	}

}
