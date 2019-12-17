package com.tet.fleamarket.dao;

import com.tet.fleamarket.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Hou Weiying
 * @date 2019-12-03 12:27
 */
@Repository
public interface PictureDao extends JpaRepository<Picture,String> {
}
