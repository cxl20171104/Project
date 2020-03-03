package com.alphasta.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.exception.ServiceException;
import com.alphasta.mapper.DictMapper;
import com.alphasta.model.Dict;
import com.alphasta.service.DictService;

/**
 * 数据字典service
 * 
 * @author LiYunhao
 *
 */
@Service("dictService")
public class DictServiceImpl implements DictService {
	private static Logger LOGGER = LoggerFactory.getLogger(DictServiceImpl.class);
	@Autowired
	private DictMapper dictMapper;

	@Override
	public List<Dict> findDictByDictPid(Dict dict) {
		return dictMapper.findDictByDictPid(dict);
	}
	@Override
	public Dict findDictById(Long id) {
		return dictMapper.findDictById(id);
	}

	@Override
	public int save(Dict dict) {
		int result;
		if(dict.getId() == null){
			String dictPid = dict.getDictPid();
			Integer count = dictMapper.findDictCountByDictPid(dictPid);
			if(dict.getDictPid().equals("0")){
				dictPid = "";
			}
			if(count<10){
				dict.setDictId(dictPid+"0"+(count+1));
			}else{
				dict.setDictId(dictPid+(count+1));
			}
			result = dictMapper.insert(dict);
		}else{
			result = dictMapper.update(dict);
		}
		if (result != 1) {
			LOGGER.warn("保存数据字典失败");
			throw new ServiceException("保存失败");
		}
		return result;
	}

	@Override
	public int deleteByIds(String ids) {
		return dictMapper.deleteByIds(ids);
	}

	@Override
	public List<Dict> findByDictPid(String dictPid) {
		return dictMapper.findByDictPid(dictPid);
	}
	@Override
	public List<Dict> findByDict(Dict dict) {
		return dictMapper.findByDict(dict);
	}



}
