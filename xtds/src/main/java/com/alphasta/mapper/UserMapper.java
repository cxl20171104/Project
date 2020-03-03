package com.alphasta.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;

import com.alphasta.commons.result.BorthMsg;
import com.alphasta.commons.result.UserVo;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Organization;
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
	void updateUserPwdById(@Param("userId") Long userId,
			@Param("pwd") String pwd);

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
	 * 获取用户全部的权限
	 * 
	 * @param userId
	 * @return 权限的url 集合
	 */
	List<String> allResoureByUserid(String userId);

	/**
	 * 获取部门下的人员
	 * 
	 * @param id
	 * @return
	 */
	List<User> findUserByOrgid(Long id);

	/**
	 * 获取用户们的CIDs
	 */
	List<String> findCidlistByIds(String[] userIds);

	/**
	 * 所有用户
	 * 
	 * @return
	 */
	List<User> getAll(Map<String, String> map);

	/**
	 * 获取用户
	 * 
	 * @param map
	 * @return
	 */
	List<User> getUsers(Map<String, String> map);

	/**
	 * （根据角色）获取领导
	 * 
	 * @param map
	 * @return
	 */
	List<User> getCenterUsers(Map<String, String> map);

	/**
	 * 根据部门 获取管理员的Cids
	 * 
	 * @param depts
	 * @return
	 */
	List<String> getCidsBydepts(List<String> depts);

	/**
	 * 获取政治生日
	 * 
	 * @return
	 */
	public List<BorthMsg> getBothers(Map<String,String>  map);

	/**
	 * 获取政治生日
	 * 
	 * @return
	 */
	public List<BorthMsg> getBothersByMonth();

	/**
	 * 在线人数
	 * 
	 * @return
	 */
	public String getOnlineNum();

	/**
	 * 用户排名
	 */
	public User getUserPM(Map map);

	/**
	 * 年度排名或月度排名
	 */
	public List<User> getNdpm(Map map);

	/**
	 * 部门年度积分 或月度排名
	 * type 1 niandu  2 yuedu 
	 * @return
	 */
	public List<Organization> getNdpmByOrgan(Map map);
	/**
	 * 个人流动红旗列表
	 * @return
	 */
	public List<User> grldhqlist(Map<String,Object> map);
	/**
	 * 单位流动红旗列表
	 * @return
	 */
	public List<Organization> dwldhqlist(String level);
	/**
	 * geren某月流动排名
	 * @param level
	 * @param time
	 * @return
	 */
	public List<User> grldhqpm(Map<String,Object> map);
	/**
	 * 集体某月流动排名
	 * @param level
	 * @param time
	 * @return
	 */
	public List<Organization> dwldhqpm(String level,String time);

	public User findUserByUserNameAndOrganizationName(Map<String, String> data);
	/**
	 * 各部门用户总分排名
	 * @param level
	 * @param time
	 * @param oid
	 * @return
	 */
	List<User>getZongji(Map map);
	
	/**
	 * 各部门总计的分
	 * @param level
	 * @return
	 */
	List<Organization>getOzon(String level);
	
	
	/**
	 * 用户统计
	 */
	
	List<Map<String, Object>> groupUser();
	
	/**
	 * 数量
	 */
	
	int countUser();
}

