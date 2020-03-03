package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Qrcode;
import com.alphasta.model.QrcodeUser;

public interface QrcodeMapper {

	List<Qrcode> findQrcodelist(Qrcode qrcode);

	void delectQrcode(String id);

	void insertQrcode(Qrcode qrcode);

	void updateQrcode(Qrcode qrcode);

	List<QrcodeUser> findHasQrcodeUsers(Map<String, Object> map);

	void insertQrcodeUser(QrcodeUser qrcodeUser);
}
