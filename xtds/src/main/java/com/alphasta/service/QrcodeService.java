package com.alphasta.service;

import java.util.List;
import java.util.Map;

import com.alphasta.model.Qrcode;
import com.alphasta.model.QrcodeUser;
import com.alphasta.model.Score;

public interface QrcodeService {

	public List<Qrcode> findQrcodelist(Qrcode qrcode);

	public void delectQrcode(String id);

	public void insertQrcode(Qrcode qrcode);

	public void updateQrcode(Qrcode qrcode);

	public List<QrcodeUser> findHasQrcodeUsers(Map<String, Object> map);

	public void insertQrcodeUser(QrcodeUser qrcodeUser, Score score);

}
