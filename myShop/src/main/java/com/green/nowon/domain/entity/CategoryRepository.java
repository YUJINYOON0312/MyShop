package com.green.nowon.domain.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{

	Optional<CategoryEntity> findByName(String categoryName); //test에서 탑다운 클릭 생성했음

	//상위카테고리가 null인 카테고리: 1차 카테고리
	List<CategoryEntity> findAllByParentNoIsNull(); //serviceprocess에서 탑다운

	List<CategoryEntity> findAllByParentNo(long parentNo); //serviceprocess에서 탑다운

}
