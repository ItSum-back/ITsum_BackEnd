package itsum.study.auth.service;

import itsum.study.auth.client.ClientGoogle;
import itsum.study.auth.dto.AuthRequest;
import itsum.study.auth.dto.AuthResponse;
import itsum.study.auth.jwt.AuthToken;
import itsum.study.auth.jwt.AuthTokenProvider;
import itsum.study.members.domain.Members;
import itsum.study.members.repository.MemberQuerydslRepository;
import itsum.study.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {

    private final ClientGoogle clientGoogle;
    private final MemberQuerydslRepository memberQuerydslRepository;
    private final AuthTokenProvider authTokenProvider;
    private final MemberRepository memberRepository;

    @Transactional
    public AuthResponse login(AuthRequest authRequest) {
        Members googleMember = clientGoogle.getUserData(authRequest.getAccessToken());
        String socialId = googleMember.getSocialId();
        Members member = memberQuerydslRepository.findBySocialId(socialId);

        AuthToken appToken = authTokenProvider.createUserAppToken(socialId);

        if (member == null) {
            memberRepository.save(googleMember);
            return AuthResponse.builder()
                    .appToken(appToken.getToken())
                    .isNewMember(Boolean.TRUE)
                    .socialId(socialId)
                    .userName(googleMember.getName())
                    .build();
        }

        return AuthResponse.builder()
                .appToken(appToken.getToken())
                .isNewMember(Boolean.FALSE)
                .socialId(socialId)
                .userName(member.getName())
                .build();
    }
}
