package com.green.nowon.security;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.green.nowon.domain.entity.MemberEntity;
import com.green.nowon.domain.entity.MemberEntityRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
//SecurityConfig에서 만들어 옴 
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private MemberEntityRepository repo;
	
	
	//implements UserDetailsService 작성하고 클릭
	//DB의 테이블에서 인증처리하기위한 메서드
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/*
		log.info(">>>>> login page: email -> username"+username);
		//repo.findById(null);
		MemberEntity entity=repo.findByEmailAndSocialAndDeleted(username, false, false)
				.orElseThrow(()->new UsernameNotFoundException("존재하지 않는 이메일"));
		
		//email,pass,roles(Collection<? extends GrantedAuthority> authorities)
		// enum MyRole -> SimpleGrantedAuthority
		Set<SimpleGrantedAuthority> authorities = 
				entity.getRoles() //Set<MyRole> ---> Set<GrantedAuthority> : stream
				.stream() //Stream<MyRole>
				.map(myRole->new SimpleGrantedAuthority(myRole.getRole()) ) //Stream<GrantedAuthority>
				.collect(Collectors.toSet() );
				
		//return new MyUserDetails(username, entity.getPass(), authorities);
		*/
		return new MyUserDetails(repo.findByEmailAndSocialAndDeleted(username, false, false)
				.orElseThrow(()->new UsernameNotFoundException("존재하지 않는 이메일")));
	}

}
