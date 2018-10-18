package com.bin.auth.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bin.auth.dao.RoleDao;
import com.bin.auth.dao.UserRoleDao;
import com.bin.auth.dao.entity.Role;
import com.bin.auth.dao.entity.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bin.auth.dao.UserDao;
import com.bin.auth.dao.entity.User;
import com.bin.auth.dto.CustomUserDetails;
import com.bin.auth.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.info("===================获取到token已进入自定义验证：" + username);

		Set<GrantedAuthority> dbAuthsSet = new HashSet<GrantedAuthority>();
		User user = userDao.findUserByUsername(username);

		List<UserRole> userRoleList = userRoleDao.findUserRoleByUid(user.getId());
		for(UserRole userRoleFor : userRoleList){
			Role role = roleDao.findRoleById(userRoleFor.getRid());
			dbAuthsSet.add(new SimpleGrantedAuthority(role.getRoleName()));
		}

		List<GrantedAuthority> dbAuths = new ArrayList<GrantedAuthority>(dbAuthsSet);

		if (user == null) {
			log.info("===================" + username);
			throw new UsernameNotFoundException("Could not find the user '" + username + "'");
		}

		return new org.springframework.security.core.userdetails.User(username,user.getPassword(),dbAuths);
	}

}
