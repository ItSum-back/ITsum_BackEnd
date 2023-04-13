package com.study.itsum.oauth.client;

import com.study.itsum.domain.Members;

public interface ClientProxy {

    Members getUserData(String accessToken);
}
