package com.alphasta.service;

import java.util.List;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Catalog;
import com.alphasta.model.User;

public interface CatalogService {

	public List<Catalog> getAllCatalog();

	public List<Catalog> getIndexCatalog(PageInfo pageInfo);

	public void updateByPrimaryKey(Catalog cata);

	public void insertSelective(Catalog cata);

	public void deleteByID(String id);

	public Catalog getByID(String id);

	public List<Catalog> tree(String id);

	public List<Catalog> getByCondition(Catalog cata);

	/**
	 * 根据专题活动查询
	 * 
	 * @param activities
	 * @return
	 */
	public List<Catalog> getByactivities(String activities);

	/**
	 * 笔记列表
	 * 
	 * @return
	 */
	public List<Catalog> getNoteCata();

	public List<User> getUserNoteNum(String oid);
	/**
	 * 通过部门id查找栏目
	 */
	public Catalog findCatalogByoid(String oid);
}
