package com.green.nowon.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.green.nowon.security.MyRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SequenceGenerator(name = "gen_mem",
		sequenceName = "seq_mem", initialValue = 1, allocationSize = 1)
@Table(name = "my_member")
@Entity
public class MemberEntity extends BaseDateEntity{
	
	@GeneratedValue(generator = "gen_mem", strategy = GenerationType.SEQUENCE)
	@Id
	private long mno;//회원번호
	
	@Column(unique = true ,nullable = false)
	private String email; //id로 사용 할 것
	
	@Column(nullable = false)
	private String pass; //password로 사용 할 것
	
	@Column(nullable = false)
	private String name;
	
	@Column(unique = true)
	private String nickName;
	
	private boolean social; // 소셜유저확인
	private boolean deleted; // 탈퇴여부
	
	//ROLE--enum활용
	@Builder.Default
	@CollectionTable(name = "my_role")
	@Enumerated(EnumType.STRING)// 설정하지 않으면 ROLE 정보가 숫자(ORDINAL) 타입으로 저장됨(0,1,2...)
	@ElementCollection(fetch = FetchType.EAGER) //컬렉션을 내장 엔티티로 만들어줌, 1:N
	private Set<MyRole> roles=new HashSet<>();
	
	//role적용을 위한 편의메서드
	public MemberEntity addRole(MyRole role) {
		roles.add(role);
		return this;
		
	}
}
