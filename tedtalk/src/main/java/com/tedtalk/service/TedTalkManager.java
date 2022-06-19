package com.tedtalk.service;

import com.tedtalk.domain.vo.TedTalkVO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TedTalkManager {
    List<TedTalkVO> getALlTedTalks(Map<String, Object> filters);

    Optional<TedTalkVO> getTedTalk(long id);

    TedTalkVO insertTedTalk(TedTalkVO tedTalk);

    void insertTedTalk(List<TedTalkVO> tedTalks);

    TedTalkVO updateTedTalk(TedTalkVO tedTalk);

    boolean deleteTedTalk(long id);
}
