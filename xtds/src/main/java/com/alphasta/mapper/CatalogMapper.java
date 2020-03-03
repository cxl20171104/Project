package com.alphasta.mapper;

import java.util.List;

import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Catalog;
import com.alphasta.model.User;

/**
 * 栏目管理
 * 
 * @author sjb
 * 
 */
public interface CatalogMapper {

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
	 * 学习笔记
	 */
	public List<Catalog> getCataNote();

	/**
	 * 个人学习笔记数量
	 * 
	 * @param cid
	 * @return
	 */

	public List<User> getUserNote(String oid);
	/**
	 * 通过部门id查找栏目
	 */
	public Catalog findCatalogByoid(String oid);
}
