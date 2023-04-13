package com.study.itsum.oauth.service;


import com.study.itsum.domain.MemberQuerydslRepository;
import com.study.itsum.domain.MemberRepository;
import com.study.itsum.domain.Members;
import com.study.itsum.oauth.client.ClientKakao;
import com.study.itsum.oauth.dto.AuthRequest;
import com.study.itsum.oauth.dto.AuthResponse;
import com.study.itsum.oauth.jwt.AuthToken;
import com.study.itsum.oauth.jwt.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {

    private final ClientKakao clientKakao;
    private final MemberQuerydslRepository memberQuerydslRepository;
    private final AuthTokenProvider authTokenProvider;
    private final MemberRepository memberRepository;

    @Transactional
    public AuthResponse login(AuthRequest authRequest){
        Members kakaoMember = clientKakao.getUserData(authRequest.getAccessToken());
        String socialId = kakaoMember.getSocialId();
        Members member = memberQuerydslRepository.findBySocialId(socialId);

        AuthToken appToken = authTokenProvider.createUserAppToken(socialId);


        if(member==null){
            memberRepository.save(kakaoMember);
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
