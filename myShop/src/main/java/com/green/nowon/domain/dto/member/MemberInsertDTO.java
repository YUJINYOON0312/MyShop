package com.green.nowon.domain.dto.member;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.nowon.domain.entity.MemberEntity;

import lombok.Setter;

@Setter
public class MemberInsertDTO {

	private String email; //signup파일의 input name들
	private String pass;
	private String name;
	
	//최종족으로 DB에 저장될때는 dto의 필드가 MemberEntity클래스에 매핑
	public MemberEntity toEntity(PasswordEncoder pe) {
		return MemberEntity.builder()
				.email(email).pass(pe.encode(pass)).name(name)
				.build();
	}
}
