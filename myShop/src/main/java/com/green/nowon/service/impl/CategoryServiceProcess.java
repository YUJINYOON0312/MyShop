package com.green.nowon.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.entity.CategoryEntity;
import com.green.nowon.domain.entity.CategoryEntityRepository;
import com.green.nowon.service.CategoryService;

@Service
public class CategoryServiceProcess implements CategoryService {

	@Autowired
	private CategoryEntityRepository repo;
	
	@Override
	public void save(String[] names) {
		
		CategoryEntity cate1=repo.findByParentNoNullAndName(names[0])
				.orElseGet(()->repo.save(CategoryEntity.builder().name(names[0]).build()));
		//존재하면 가져오고 존재안하면 만들어서 저장한다
		
		CategoryEntity cate2=repo.findByParentNoAndName(cate1.getNo(), names[1])
			.orElseGet(()->repo.save(CategoryEntity.builder().name(names[1]).parent(cate1).build()));
		
		CategoryEntity cate3=repo.findByParentNoAndName(cate2.getNo(), names[2])
				.orElseGet(()->repo.save(CategoryEntity.builder().name(names[2]).parent(cate2).build()));
		
		CategoryEntity cate4=repo.findByParentNoAndName(cate3.getNo(), names[3])
				.orElseGet(()->repo.save(CategoryEntity.builder().name(names[3]).parent(cate3).build()));
			
		
	
	}

	@Override
	public String isReg(String text) {
		Optional<CategoryEntity> result = repo.findByParentNoNullAndName(text);
		if(result.isEmpty())
			return "등록가능";
		return "존재하는 카테고리";
	}

}
