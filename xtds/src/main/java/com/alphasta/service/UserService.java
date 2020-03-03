package com.alphasta.service;

import com.alphasta.commons.result.BorthMsg;
import com.alphasta.commons.result.UserVo;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Organization;
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
	 * 
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
	 * 
	 * @param orgId
	 * @return
	 */

	public List<User> getUsersByOrgId(Long orgId);

	public List<String> getCidsByUids(String[] userIds);

	public List<User> getAll(Map<String, String> map);

	public List<User> getUsers(Map<String, String> map);

	List<User> getCenterUsers(Map<String, String> map);

	/**
	 * 获取当天的政治生日人员
	 * 
	 * @return
	 */
	public List<BorthMsg> getBorthUsers(Map<String,String> map);

	/**
	 * 获取当月政治生日人员
	 * 
	 * @return
	 */
	public List<BorthMsg> getBorthUsersByMonth();

	public String getOnlineNum();

	public User getPM(String userId,String type);

	public List<User> getNdPm(String type,String level,String oid);

	public List<Organization> getNdpmByOrgan(String type,String level);
	/**
	 * 个人流动红旗
	 * @return
	 */
	public List<User> grldhqList(String level,String oid);
	/**
	 * 单位流动红旗
	 * @return
	 */
	public List<Organization> dwldhqList(String level);
	/**
	 * 个人流动红旗
	 * @return
	 */
	public List<User> grldhqpm(String level,String time,String oid);
	/**
	 * 单位流动红旗
	 * @return
	 */
	public List<Organization> dwldhqpm(String level,String time);
	/**
	 * 该部门用户总计得分
	 */
	public List<User>getZongji(String level,String type,String oid);
	/**
	 * 各部门总计得分
	 */
	public List<Organization>getOzon(String level);
	
	
	
	
	
	
	/**
	 * 统计使用用户
	 */
	
	List<Map<String,Object>>  groupUser();
	
	/**
	 * 总用户
	 */
	int  countUser();
}
