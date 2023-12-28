package com.yoonho.holostats.configs.security;

import com.yoonho.holostats.models.Member;
import com.yoonho.holostats.repositories.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * packageName    : com.yoonho.holostats.configs.security
 * fileName       : CustomUserDetailService
 * author         : kim-yoonho
 * date           : 12/27/23
 * description    : spring security 커스텀 유저정보 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/27/23        kim-yoonho       최초 생성
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.getMemberByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("No User Found"));
        return new User(member.getMemberName(),
                        member.getMemberPassword(),
                        toGrantedAuthorities(member.getMemberRole()));
    }

    private Collection<? extends GrantedAuthority> toGrantedAuthorities(String memberRole) {
        return List.of(new SimpleGrantedAuthority(memberRole));
    }
}
