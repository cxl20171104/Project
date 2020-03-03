package com.alphasta.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.base.SqlService;
import com.alphasta.commons.result.UserVo;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.PositionMapper;
import com.alphasta.mapper.UserMapper;
import com.alphasta.mapper.UserRoleMapper;
import com.alphasta.model.Position;
import com.alphasta.model.User;
import com.alphasta.model.UserRole;
import com.alphasta.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private PositionMapper positionMapper;
	@Autowired
	private SqlService sqlService;
    @Override
    public User findUserByLoginName(String username) {
    	
    	
        return userMapper.findUserByLoginName(username);
    }

    @Override
    public User findUserById(Long id) {
        return userMapper.findUserById(id);
    }
    @Override
    public User findUserByOId(Long oId) {
        return userMapper.findUserByOId(oId);
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
    public List<User> findViewUser(Long id){
    	List<User> list = new ArrayList<User>();
    	User user = userMapper.findUserById(id);
    	Position position = positionMapper.findPositionById(user.getPositionId());
    	if(position.getIsView()==0){
    		list.add(user);
    	}else{
    		list = userMapper.findUser(user);
    	}
    	return list;
    }
    
    @Override
	public String findViewUserIds(Long id){
    	List<User> list = this.findViewUser(id);
    	String ids = "";
    	for (User user : list) {
    		ids = ids + user.getId() + ",";
		}
    	ids = ids.substring(0,ids.length()-1);
    	return ids;
    }
    
    @Override
    public User getUserByToken(String token){
    	
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
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userMapper.getAll();
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
	public void updateState(User user) {
			System.out.println("》》》》》》》》》》》》》》》》》》》》》》》开始执行存储过程");
  		  //执行存储过程	
            SqlSessionFactory SqlSessionFactory= sqlService.getSqlSessionFactory();
			 org.apache.ibatis.session.SqlSession openSession = SqlSessionFactory.openSession();
					         String statement = "com.alphasta.mapper.UserMapper.getUserCount";//映射sql的标识字符串
					         Map<String, Object> parameterMap = new HashMap<String,Object>();
					         user.setStatus(0);
					         parameterMap.put("ida", user.getId());
					         openSession.selectOne(statement, parameterMap);
					         openSession.close();
	
			
	}

}
