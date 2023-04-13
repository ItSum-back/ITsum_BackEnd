package com.study.itsum.oauth.client;


import com.study.itsum.domain.MemberProvider;
import com.study.itsum.domain.Members;
import com.study.itsum.oauth.dto.GoogleUserResponse;
import com.study.itsum.oauth.enumerate.RoleType;
import com.study.itsum.oauth.exception.TokenValidFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClientGoogle implements ClientProxy{

    private final WebClient webClient;

    @Override
    public Members getUserData(String accessToken){
        GoogleUserResponse googleUserResponse = webClient.get()
                .uri("https://oauth2.googleapis.com/tokeninfo", builder -> builder.queryParam("id_token", accessToken).build())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new TokenValidFailedException("Social Access Token is unauthorized")))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new TokenValidFailedException("Internal Server Error")))
                .bodyToMono(GoogleUserResponse.class)
                .block();

        return Members.builder()
                .socialId(googleUserResponse.getSub())
                .username(googleUserResponse.getName())
                .email(googleUserResponse.getEmail())
                .memberProvider(MemberProvider.GOOGLE)
                .roleType(RoleType.USER)
                .build();


    }

}
