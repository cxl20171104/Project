package com.alphasta.service;

import com.alphasta.commons.result.UserVo;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.User;

import java.util.List;
import java.util.Map;


/**
 * @description：用户管理
 * @author：liyunhao @date：2015/10/1 14:51
 */
public interface UserService {
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
	 */
	void findDataGrid(PageInfo pageInfo);

	/**
	 * 添加用户
	 *
	 * @param userVo
	 */
	void addUser(UserVo userVo);

	/**
	 * 修改密码
	 *
	 * @param userId
	 * @param pwd
	 */
	void updateUserPwdById(Long userId, String pwd);

	/**
	 * 根据用户id查询用户带部门
	 *
	 * @param id
	 * @return
	 */
	UserVo findUserVoById(Long id);

	/**
	 * 修改用户
	 *
	 * @param userVo
	 */
	void updateUser(UserVo userVo);
	
	void updateUser(User user);
	/**
	 * 删除用户
	 *
	 * @param id
	 */
	void deleteUserById(Long id);

	/**
	 * 查询可查看用户
	 * 
	 * @param id
	 * @return
	 */
	public List<User> findViewUser(Long id);

	/**
	 * 查询可查看用户id
	 * 
	 * @param id
	 * @return
	 */
	public String findViewUserIds(Long id);
	/**
	 * 根据token查询
	 * @param token
	 * @return
	 */
	User getUserByToken(String token);
	/**
	 * 用户基本信息修改
	 */
	public void updateUserInfo(User user);
	/**
	 * 根据部门ID 查询人员
	 * @param orgId
	 * @return
	 */
	
	public List<User>  getUsersByOrgId(Long orgId);
	public List<String>  getCidsByUids(String[] userIds);
	public List<User> getAll();
	public List<User> getUsers(Map<String,String> map);
 	/**
 	 * 更改user的状态
 	 */
	public void updateState(User user);
	
}
