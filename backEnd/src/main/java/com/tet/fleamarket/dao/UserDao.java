package com.tet.fleamarket.dao;


import com.tet.fleamarket.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Hou Weiying
 * @date 2019-10-22 19:14
 */

public interface UserDao extends JpaRepository<User, String> {
}
