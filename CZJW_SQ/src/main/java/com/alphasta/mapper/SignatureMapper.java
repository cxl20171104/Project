package com.alphasta.mapper;

import java.util.List;

import com.alphasta.model.Signature;

/**
 * 签名mapper
 * 
 * @author LiYunhao
 *
 */
public interface SignatureMapper {
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
	 * 插入签名信息
	 * 
	 * @param Company
	 * @return
	 */
	int insert(Signature signature);

	/**
	 * 更新签名信息
	 * 
	 * @param Company
	 * @return
	 */
	int update(Signature signature);

	/**
	 * 通过id删除签名信息
	 * 
	 * @param id
	 * @return
	 */
	int deleteByIds(String ids);

}
