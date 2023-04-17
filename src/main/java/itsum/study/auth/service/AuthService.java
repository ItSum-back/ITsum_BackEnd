package itsum.study.auth.service;

import io.jsonwebtoken.Claims;
import itsum.study.auth.dto.AuthResponse;
import itsum.study.auth.jwt.AuthToken;
import itsum.study.auth.jwt.AuthTokenProvider;
import itsum.study.members.domain.Members;
import itsum.study.members.repository.MemberQuerydslRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthTokenProvider authTokenProvider;
    private final MemberQuerydslRepository memberQuerydslRepository;

    public AuthResponse updateToken(AuthToken authToken) {
        Claims claims = authToken.getTokenClaims();
        if (claims == null) {
            return null;
        }

        String socialId = claims.getSubject();

        AuthToken newAppToken = authTokenProvider.createUserAppToken(socialId);

        return AuthResponse.builder()
                .appToken(newAppToken.getToken())
                .build();
    }

    public Long getMemberId(String token) {
        AuthToken authToken = authTokenProvider.convertAuthToken(token);

        Claims claims = authToken.getTokenClaims();
        if (claims == null) {
            return null;
        }

        try {
            Members member =  memberQuerydslRepository.findBySocialId(claims.getSubject());
            return member.getId();

        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자가 존재하지 않습니다.");
        }
    }
}

