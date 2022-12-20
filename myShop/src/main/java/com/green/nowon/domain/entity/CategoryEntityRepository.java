package com.green.nowon.domain.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, Long>{

	Optional<CategoryEntity> findByName(String name);

	//1차 카테고리 조회
	Optional<CategoryEntity> findByParentNoNullAndName(String name);

	//서브카테고리
	Optional<CategoryEntity> findByParentNoAndName(long parentNo, String name);

}
