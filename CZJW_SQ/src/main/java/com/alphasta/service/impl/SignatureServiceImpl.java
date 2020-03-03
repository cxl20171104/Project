package com.alphasta.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.mapper.SignatureMapper;
import com.alphasta.model.Signature;
import com.alphasta.service.SignatureService;

@Service("signatureService")
public class SignatureServiceImpl implements SignatureService {
private static Logger LOGGER = LoggerFactory.getLogger(SignatureServiceImpl.class);
	
	@Autowired
	private SignatureMapper signatureMapper;

	@Override
	public List<Signature> findSignature(Signature signature) {
		return signatureMapper.findSignature(signature);
	}

	@Override
	public Signature findSignatureById(Long id) {
		return signatureMapper.findSignatureById(id);
	}

	@Override
	public void save(Signature signature) {
		if(signature.getId()==null){
			signatureMapper.insert(signature);
		}else{
			signatureMapper.update(signature);
		}
		
	}

	@Override
	public int deleteByIds(String ids) {
		return signatureMapper.deleteByIds(ids);
	}

}
