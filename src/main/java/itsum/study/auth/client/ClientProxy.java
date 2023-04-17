package itsum.study.auth.client;

import itsum.study.members.domain.Members;

public interface ClientProxy {

    Members getUserData(String accessToken);
}
