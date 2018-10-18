package com.bin.auth.dao;

import com.bin.auth.dao.entity.User;
import com.bin.auth.dao.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleDao extends JpaRepository<UserRole, Integer>{

	public List<UserRole> findUserRoleByUid(Integer uid);
}
