package com.study.itsum.oauth.client;


import com.study.itsum.domain.MemberProvider;
import com.study.itsum.domain.Members;
import com.study.itsum.oauth.dto.KakaoUserResponse;
import com.study.itsum.oauth.enumerate.RoleType;
import com.study.itsum.oauth.exception.TokenValidFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClientKakao implements ClientProxy{

    private final WebClient webClient;

    @Override
    public Members getUserData(String accessToken){
        KakaoUserResponse kakaoUserResponse = webClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .headers(h-> h.setBearerAuth(accessToken))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,response -> Mono.error(new TokenValidFailedException("Social Acess Token is unauthorized")))
                .onStatus(HttpStatus::is5xxServerError,response -> Mono.error(new TokenValidFailedException("Internal Server Error")))
                .bodyToMono(KakaoUserResponse.class)
                .block();


        return Members.builder()
                .socialId(String.valueOf(kakaoUserResponse.getId()))
                .username(kakaoUserResponse.getProperties().getNickname())
                .email(kakaoUserResponse.getKakaoAccount().getEmail())
                .memberProvider(MemberProvider.KAKAO)
                .roleType(RoleType.USER)
                .build();

    }




}
