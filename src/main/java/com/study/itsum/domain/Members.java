package com.study.itsum.domain;


import com.study.itsum.oauth.enumerate.RoleType;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Members {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String username;

    private String nickname;

    private String password;

    private Long phone_number;

    private String email;

    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberProvider memberProvider;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType roleType;

    public void updateNickname(String nickname){
        this.nickname = nickname;
    }



}
