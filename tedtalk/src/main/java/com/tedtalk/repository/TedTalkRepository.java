package com.tedtalk.repository;

import com.tedtalk.domain.dto.TedTalkDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TedTalkRepository {
    List<TedTalkDTO> getAll(Map<String, Object> filters);
    Optional<TedTalkDTO> getTedTalk(long id);
    TedTalkDTO insertTedTalk(TedTalkDTO tedTalk);
    void insertTedTalk(List<TedTalkDTO> tedTalks);
    TedTalkDTO updateTedTalk(TedTalkDTO tedTalkDTO);
    boolean delete(long id);
}
