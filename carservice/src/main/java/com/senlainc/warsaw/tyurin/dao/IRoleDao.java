package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.entity.Role;

public interface IRoleDao {

    Role findByName(String name);
}
