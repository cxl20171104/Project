package com.alphasta.commons.shiro;

import com.alphasta.model.User;
import com.alphasta.service.RoleService;
import com.alphasta.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description：shiro权限认证
 * @author：alphasta
 * @date：2015/10/1 14:51
 */
public class ShiroDbRealm extends AuthorizingRealm {

	private static Logger LOGGER = LoggerFactory.getLogger(ShiroDbRealm.class);

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	public static   Map<String,String> map = new HashMap<String, String>();

	/**
	 * Shiro登录认证(原理：用户提交 用户名和密码  --- shiro 封装令牌 ---- realm 通过用户名将密码查询返回 ---- shiro 自动去比较查询出密码和用户输入密码是否一致---- 进行登陆控制 )
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		LOGGER.info("Shiro开始登录认证");
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = userService.findUserByLoginName(token.getUsername());
		// 账号不存在
		if (user == null) {
			return null;
		}
		//账号被锁定
		if(user.getStatus()==1){
			throw  new LockedAccountException(); 
		}else if(map.get(user.getName())!=null){
			if(Integer.parseInt(map.get(user.getName()))>=5){
				user.setStatus(1);
				userService.updateUserInfo(user);
				//调用存储过程5分钟后还原statua的值
				//userService.updateState(user);
				map.clear();
				 throw new DisabledAccountException();
			}else{
				//加1
				int num =Integer.parseInt(map.get(user.getName()))+1;
				String nums = String.valueOf(num);
				//将用户存放到map 
				map.put(user.getName(), nums);
			}
		}else{
			//存入
			map.put(user.getName(), "1");
		}
		List<Long> roleList = roleService.findRoleIdListByUserId(user.getId());
		ShiroUser shiroUser = new ShiroUser(user.getId(), user.getLoginname(), user.getName(), roleList);
		// 认证缓存信息
		return new SimpleAuthenticationInfo(shiroUser, user.getPassword().toCharArray(), getName());

	}

	/**
	 * Shiro权限认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {

		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		List<Long> roleList = shiroUser.roleList;

		Set<String> urlSet = new HashSet<String>();
		for (Long roleId : roleList) {
			List<Map<Long, String>> roleResourceList = roleService.findRoleResourceListByRoleId(roleId);
			if (roleResourceList != null) {
				for (Map<Long, String> map : roleResourceList) {
					if (map!=null&&StringUtils.isNoneBlank(map.get("url"))) {
						urlSet.add(map.get("url"));
					}
				}
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(urlSet);
		return info;
	}
}
