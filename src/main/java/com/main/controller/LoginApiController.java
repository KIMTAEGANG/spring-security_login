package com.main.controller;

import com.main.entity.Member;
import com.main.jwt.JwtTokenProvider;
import com.main.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/main")
@RequiredArgsConstructor
public class LoginApiController {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public String login(@RequestBody Map<String,String> map) {
        Member member = memberRepository.findByUserId(map.get("id"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 id 입니다."));

        return jwtTokenProvider.createToken(map.get("id"),member.getRoles());
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST, produces = "application/json")
    public String test() {
        return "success";
    }


    //아이디 중복 체크

    //로그인

    //로그아웃
}
