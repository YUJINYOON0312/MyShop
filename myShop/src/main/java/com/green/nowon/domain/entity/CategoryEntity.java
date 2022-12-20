package com.green.nowon.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "category")
@Entity
public class CategoryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto 인크리먼트 설정 위해서
	private long no;
	private String name;
	
	@JoinColumn //parent_no
	@ManyToOne(fetch = FetchType.LAZY)
	private CategoryEntity parent; //상위카테고리
	
	@Builder.Default
	@OneToMany(mappedBy = "parent") // 양방향 설정
	private List<CategoryEntity> child=new ArrayList<>();  //자식 카테고리, 셀프조인 하겠다는 뜻

	//편의메서드
	public CategoryEntity addChildCategory(CategoryEntity cate) {
		child.add(cate);
		//parent=this;
		return this;
	}
}
