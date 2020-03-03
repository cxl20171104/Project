package com.alphasta.service;

import java.util.List;

import com.alphasta.model.Signature;

/**
 * 签名service
 * 
 * @author LiYunhao
 *
 */
public interface SignatureService {
	/**
	 * 根据查询签名信息
	 * 
	 * @param pageInfo
	 * @return
	 */
	List<Signature> findSignature(Signature signature);

	/**
	 * 通过id查询签名信息
	 * 
	 * @param id
	 * @return
	 */
	Signature findSignatureById(Long id);

	/**
	 * 保存签名信息
	 * 
	 * @param Company
	 * @return
	 */
	void save(Signature signature);

	/**
	 * 通过id删除签名信息
	 * 
	 * @param id
	 * @return
	 */
	int deleteByIds(String ids);

}
