package com.bin.auth.dao;

import com.bin.auth.dao.entity.Role;
import com.bin.auth.dao.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleDao extends JpaRepository<Role, Integer>{

	public Role findRoleById(Integer id);
}
