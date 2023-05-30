package itsum.study.auth.client;

import itsum.study.auth.dto.GoogleUserResponse;
import itsum.study.auth.enumerate.RoleType;
import itsum.study.auth.exception.TokenValidFailedException;
import itsum.study.members.domain.Members;
import itsum.study.members.enumerate.MemberProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClientGoogle implements ClientProxy {

    private final WebClient webClient;

    // TODO ADMIN 유저 생성 시 getAdminUserData 메소드 생성 필요
    @Override
    public Members getUserData(String accessToken) {
        GoogleUserResponse googleUserResponse = webClient.get()
                .uri("https://www.googleapis.com/oauth2/v3/userinfo")
                .header("AUTHORIZATION", "Bearer " + accessToken)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new TokenValidFailedException("Social Access Token is unauthorized")))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new TokenValidFailedException("Internal Server Error")))
                .bodyToMono(GoogleUserResponse.class)
                .block();

        return Members.builder()
                .socialId(googleUserResponse.getSub())
                .name(googleUserResponse.getName())
                .email(googleUserResponse.getEmail())
                .memberProvider(MemberProvider.GOOGLE)
                .roleType(RoleType.USER)
                .build();
    }
}
