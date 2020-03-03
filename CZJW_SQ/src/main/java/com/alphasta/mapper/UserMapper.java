package com.alphasta.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alphasta.commons.result.UserVo;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.User;

public interface UserMapper {
	/**
	 * 删除用户
	 *
	 * @param id
	 * @return
	 */
	int deleteById(Long id);

	/**
	 * 添加用户
	 *
	 * @param user
	 * @return
	 */
	int insert(User user);

	/**
	 * 修改用户
	 *
	 * @param user
	 * @return
	 */
	int updateUser(User user);

	/**
	 * 根据用户名查询用户
	 *
	 * @param username
	 * @return
	 */
	User findUserByLoginName(String username);

	/**
	 * 根据用户id查询用户
	 *
	 * @param id
	 * @return
	 */
	User findUserById(Long id);
	
	/**
	 * 根据用户oId查询用户
	 *
	 * @param oId
	 * @return
	 */
	User findUserByOId(Long oId);

	/**
	 * 用户列表
	 *
	 * @param pageInfo
	 * @return
	 */
	List findUserPageCondition(PageInfo pageInfo);

	/**
	 * 统计用户
	 *
	 * @param pageInfo
	 * @return
	 */
	int findUserPageCount(PageInfo pageInfo);

	/**
	 * 修改用户密码
	 *
	 * @param userId
	 * @param pwd
	 */
	void updateUserPwdById(@Param("userId") Long userId, @Param("pwd") String pwd);

	/**
	 * 根据用户id查询用户带部门
	 *
	 * @param id
	 * @return
	 */
	UserVo findUserVoById(Long id);

	/**
	 * 查询用户
	 * 
	 * @param user
	 * @return
	 */
	List<User> findUser(User user);
	
	/**
	 * 查询token
	 * 
	 * @param token
	 * @return
	 */
	User getUserByToken(String token);
	/**
	 *  获取用户全部的权限
	 * @param userId
	 * @return 权限的url 集合
	 */
	List<String> allResoureByUserid(String userId); 
	/**
	 * 获取部门下的人员
	 * @param id
	 * @return
	 */
	List<User>  findUserByOrgid(Long id);
	/**
	 * 
	 */
	List<String> findCidlistByIds(String[] userIds);
	
	List<User> getAll();
	List<User> getUsers(Map<String,String> map);
	
	User getUser(String id);
	
}