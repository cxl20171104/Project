package com.alphasta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.CatalogMapper;
import com.alphasta.model.Catalog;
import com.alphasta.model.User;
import com.alphasta.service.CatalogService;

/**
 * 栏目管理
 * 
 * @author sjb
 * 
 */
@Service
public class CatalogServiceImpl implements CatalogService {

	@Autowired
	private CatalogMapper catalogMapper;

	/**
	 * 根据专题活动查询
	 * 
	 * @param activities
	 * @return
	 */
	public List<Catalog> getByactivities(String activities) {

		return catalogMapper.getByactivities(activities);
	}

	/**
	 * 所有栏目
	 */
	@Override
	public List<Catalog> getAllCatalog() {

		return catalogMapper.getAllCatalog();
	}

	/**
	 * 首页栏目
	 */
	@Override
	public List<Catalog> getIndexCatalog(PageInfo pageInfo) {

		return catalogMapper.getIndexCatalog(pageInfo);
	}

	@Override
	public void updateByPrimaryKey(Catalog cata) {
		catalogMapper.updateByPrimaryKey(cata);

	}

	@Override
	public void insertSelective(Catalog cata) {
		Catalog parent = catalogMapper.getByID(cata.getParentcata());
		Catalog c = new Catalog();
		c.setParentcata(parent.getId());
		List<Catalog> catas = catalogMapper.getByCondition(c);
		if (catas.size() == 0) {
			parent.setShowNextColunm("1");
			catalogMapper.updateByPrimaryKey(parent);
		}
		catalogMapper.insertSelective(cata);

	}

	@Override
	public void deleteByID(String id) {
		Catalog parent = catalogMapper.getByID(catalogMapper.getByID(id)
				.getParentcata());
		Catalog c = new Catalog();
		c.setParentcata(parent.getId());
		List<Catalog> catas = catalogMapper.getByCondition(c);
		if (catas.size() == 1) {
			parent.setShowNextColunm("0");
			catalogMapper.updateByPrimaryKey(parent);
		}
		catalogMapper.deleteByID(id);

	}

	@Override
	public Catalog getByID(String id) {
		// TODO Auto-generated method stub
		return catalogMapper.getByID(id);
	}

	@Override
	public List<Catalog> tree(String id) {
		// TODO Auto-generated method stub
		return catalogMapper.tree(id);
	}

	@Override
	public List<Catalog> getByCondition(Catalog cata) {
		// TODO Auto-generated method stub
		return catalogMapper.getByCondition(cata);
	}

	@Override
	public List<Catalog> getNoteCata() {
		// TODO Auto-generated method stub
		return catalogMapper.getCataNote();
	}

	@Override
	public List<User> getUserNoteNum(String oid) {
		// TODO Auto-generated method stub
		return catalogMapper.getUserNote(oid);
	}
	/**
	 * 通过部门id查找栏目
	 */
	@Override
	public Catalog findCatalogByoid(String oid) {
		Catalog cl = catalogMapper.findCatalogByoid(oid);
		return cl;
	}

}
