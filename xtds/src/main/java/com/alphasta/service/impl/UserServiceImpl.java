package com.alphasta.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.result.BorthMsg;
import com.alphasta.commons.result.UserVo;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.PositionMapper;
import com.alphasta.mapper.UserMapper;
import com.alphasta.mapper.UserRoleMapper;
import com.alphasta.model.Organization;
import com.alphasta.model.Position;
import com.alphasta.model.User;
import com.alphasta.model.UserRole;
import com.alphasta.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static Logger LOGGER = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private PositionMapper positionMapper;

	@Override
	public User findUserByLoginName(String username) {

		return userMapper.findUserByLoginName(username);
	}

	@Override
	public User findUserById(Long id) {
		return userMapper.findUserById(id);
	}

	@Override
	public void findDataGrid(PageInfo pageInfo) {
		pageInfo.setRows(userMapper.findUserPageCondition(pageInfo));
		pageInfo.setTotal(userMapper.findUserPageCount(pageInfo));
	}

	@Override
	public void addUser(UserVo userVo) {
		User user = new User();
		try {
			PropertyUtils.copyProperties(user, userVo);
		} catch (Exception e) {
			LOGGER.error("类转换异常：{}", e);
			throw new RuntimeException("类型转换异常：{}", e);
		}
		userMapper.insert(user);

		Long id = user.getId();
		String[] roles = userVo.getRoleIds().split(",");
		UserRole userRole = new UserRole();

		for (String string : roles) {
			userRole.setUserId(id);
			userRole.setRoleId(Long.valueOf(string));
			userRoleMapper.insert(userRole);
		}
	}

	@Override
	public void updateUserPwdById(Long userId, String pwd) {
		userMapper.updateUserPwdById(userId, pwd);
	}

	@Override
	public UserVo findUserVoById(Long id) {
		return userMapper.findUserVoById(id);
	}

	@Override
	public void updateUser(UserVo userVo) {
		User user = new User();
		try {
			PropertyUtils.copyProperties(user, userVo);
		} catch (Exception e) {
			LOGGER.error("类转换异常：{}", e);
			throw new RuntimeException("类型转换异常：{}", e);
		}
		userMapper.updateUser(user);
		Long id = userVo.getId();
		List<UserRole> userRoles = userRoleMapper.findUserRoleByUserId(id);
		if (userRoles != null && (!userRoles.isEmpty())) {
			for (UserRole userRole : userRoles) {
				userRoleMapper.deleteById(userRole.getId());
			}
		}

		String[] roles = userVo.getRoleIds().split(",");
		UserRole userRole = new UserRole();
		for (String string : roles) {
			userRole.setUserId(id);
			userRole.setRoleId(Long.valueOf(string));
			userRoleMapper.insert(userRole);
		}

	}

	@Override
	public void deleteUserById(Long id) {
		userMapper.deleteById(id);
		List<UserRole> userRoles = userRoleMapper.findUserRoleByUserId(id);
		if (userRoles != null && (!userRoles.isEmpty())) {
			for (UserRole userRole : userRoles) {
				userRoleMapper.deleteById(userRole.getId());
			}
		}
	}

	@Override
	public List<User> findViewUser(Long id) {
		List<User> list = new ArrayList<User>();
		User user = userMapper.findUserById(id);
		Position position = positionMapper.findPositionById(user
				.getPositionId());
		if (position.getIsView() == 0) {
			list.add(user);
		} else {
			list = userMapper.findUser(user);
		}
		return list;
	}

	@Override
	public String findViewUserIds(Long id) {
		List<User> list = this.findViewUser(id);
		String ids = "";
		for (User user : list) {
			ids = ids + user.getId() + ",";
		}
		ids = ids.substring(0, ids.length() - 1);
		return ids;
	}

	@Override
	public User getUserByToken(String token) {

		return userMapper.getUserByToken(token);
	}

	@Override
	public void updateUserInfo(User user) {
		userMapper.updateUser(user);
	}

	@Override
	public List<User> getUsersByOrgId(Long orgId) {
		// TODO Auto-generated method stub
		return userMapper.findUserByOrgid(orgId);
	}

	@Override
	public List<String> getCidsByUids(String[] userIds) {
		// TODO Auto-generated method stub
		return userMapper.findCidlistByIds(userIds);
	}

	@Override
	public List<User> getAll(Map<String, String> map) {
		// TODO Auto-generated method stub
		return userMapper.getAll(map);
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}

	@Override
	public List<User> getUsers(Map<String, String> map) {
		return userMapper.getUsers(map);
	}

	@Override
	public List<User> getCenterUsers(Map<String, String> map) {
		// TODO Auto-generated method stub
		return userMapper.getCenterUsers(map);
	}

	@Override
	public List<BorthMsg> getBorthUsers(Map<String,String> map) {
		// TODO Auto-generated method stub

		return userMapper.getBothers(map);
	}

	@Override
	public String getOnlineNum() {
		// TODO Auto-generated method stub
		return userMapper.getOnlineNum();
	}

	@Override
	public User getPM(String userId,String type) {
		// TODO Auto-generated method stub
		if(type==null||"".equals(type)) type="1";
		Map<String, Object>  map=new HashMap<String, Object>();
		map.put("type", type);
		map.put("userid", userId);
		return userMapper.getUserPM(map);
	}

	@Override
	public List<User> getNdPm(String type, String level,String oid) {
		if(type==null||"".equals(type)) type="1";//默认为年度
		if(level==null||"".equals(level)) level="1";//默认为市局
		
		Map<String, Object>  map=new HashMap<String, Object>();
		map.put("type", type);
		map.put("level", level);
		//区县
		map.put("oid", oid);//如果为区县则  
		return userMapper.getNdpm(map);
	}

	@Override
	public List<Organization> getNdpmByOrgan(String type,String level) {
		// TODO Auto-generated method stub
		if(type==null||"".equals(type)) type="1";
		if(level==null||"".equals(level)) level="1";
		Map<String, Object>  map=new HashMap<String, Object>();
		map.put("type", type);
		map.put("level", level);
		return userMapper.getNdpmByOrgan(map);
	}

	@Override
	public List<BorthMsg> getBorthUsersByMonth() {
		return userMapper.getBothersByMonth();
	}

	@Override
	public List<User> grldhqList(String level,String oid){
		Map<String,Object> map=new HashMap<String, Object>();
		if(level!=null){
			map.put("level", level);
		};
		if(level!=null&&"2".equals(level)){ //区县
			map.put("oid", oid);
		};
		return userMapper.grldhqlist(map);
	}
	@Override
	public List<Organization> dwldhqList(String level){
		return userMapper.dwldhqlist(level);
		
	}
	@Override
	public List<User> grldhqpm(String level,String time,String oid){
		Map<String,Object> map=new HashMap<String, Object>();
		if(level!=null){
			map.put("level", level);
		};
		if(time!=null){
			map.put("time", time);
		};
		if(level!=null&&"2".equals(level)){
			map.put("oid", oid);
		};
		
		return userMapper.grldhqpm(map);
	}
	@Override
	public List<Organization> dwldhqpm(String level,String time){
		return userMapper.dwldhqpm(level,time);
		
	}

	@Override
	public List<User> getZongji(String level, String type,String oid) {
		// TODO Auto-generated method stub
		Map<String,Object>map=new HashMap<String,Object>();
		if(level!=null){
			map.put("level", level);
		};
		if(oid!=null){ //区县
			map.put("oid", oid);
		};
		if(type!=null){ //区县
			map.put("type", type);
		};
		return userMapper.getZongji(map);
	}

	@Override
	public List<Organization> getOzon(String level) {
		// TODO Auto-generated method stub
		return userMapper.getOzon(level);
	}

	@Override
	public List<Map<String, Object>> groupUser() {
		// TODO Auto-generated method stub
		return userMapper.groupUser();
	}

	@Override
	public int countUser() {
		// TODO Auto-generated method stub
		return userMapper.countUser() ;
	}
	
	
	
	
}
