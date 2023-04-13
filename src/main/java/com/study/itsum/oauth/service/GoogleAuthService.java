package com.study.itsum.oauth.service;


import com.study.itsum.domain.MemberQuerydslRepository;
import com.study.itsum.domain.MemberRepository;
import com.study.itsum.domain.Members;
import com.study.itsum.oauth.client.ClientGoogle;
import com.study.itsum.oauth.dto.AuthRequest;
import com.study.itsum.oauth.dto.AuthResponse;
import com.study.itsum.oauth.jwt.AuthToken;
import com.study.itsum.oauth.jwt.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {
    //로그인 기능 구현


    private final ClientGoogle clientGoogle;
    private final MemberQuerydslRepository memberQuerydslRepository;
    private final AuthTokenProvider authTokenProvider;
    private final MemberRepository memberRepository;

    @Transactional
    public AuthResponse login(AuthRequest authRequest){
        Members googleMember = clientGoogle.getUserData(authRequest.getAccessToken());
        String socialId = googleMember.getSocialId();
        Members member = memberQuerydslRepository.findBySocialId(socialId);

        AuthToken appToken = authTokenProvider.createUserAppToken(socialId);

        if(member == null){
            //없으면 우리 DB에 저장
            memberRepository.save(googleMember);
            return AuthResponse.builder()
                    .appToken(appToken.getToken())
                    .isNewMember(Boolean.TRUE)
                    .build();
        }

        return AuthResponse.builder()
                .appToken(appToken.getToken())
                .isNewMember(Boolean.FALSE)
                .build();


    }

}
