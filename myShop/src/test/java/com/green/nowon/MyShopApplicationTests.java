package com.green.nowon;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

import com.green.nowon.domain.entity.CategoryEntity;
import com.green.nowon.domain.entity.CategoryRepository;
import com.green.nowon.domain.entity.MemberEntity;
import com.green.nowon.domain.entity.MemberEntityRepository;
import com.green.nowon.security.MyRole;

@SpringBootTest
class MyShopApplicationTests {
	
	@Autowired
	MemberEntityRepository mrepo;
	@Autowired
	PasswordEncoder pe;
	//@Test
	void 어드민계정() {
		
		mrepo.save(
				MemberEntity.builder()
				.email("admin@test.com")
				.pass(pe.encode("1234"))
				.name("관리자").nickName("관리자")
				.build() //엔티티생성
				.addRole(MyRole.USER)
				.addRole(MyRole.ADMIN)
				);
		
	}
	
	@Autowired
	CategoryRepository categoryRepository;
	
	//@Test
	void first_차카테고리생성_테스트() {
		
		categoryRepository.save(
				CategoryEntity.builder()
				.name("의류")
				.build());
	}
	
	@Commit
	@Transactional
	//@Test
	void second_카테고리생성_테스트() {
		CategoryEntity cate=categoryRepository.findByName("컴퓨터").orElseThrow(); //이름(Name은 필드의 name의미)으로 "도서" 찾는다
		cate.addChildCategory(CategoryEntity.builder()
				.name("모니터")
				.build());
		cate.addChildCategory(CategoryEntity.builder()
				.name("본체")
				.build());
	
		categoryRepository.save(cate);
	}
	
	//@Test
	void sub_카테고리생성() {
		categoryRepository.save(
				CategoryEntity.builder()
				.name("농산물")
				//상위카테고리설정
				.parent(CategoryEntity.builder().no(1).build()) //1(식품)의 하위 카테로 농산물 넣겠다
				.build());
	}
	
}
