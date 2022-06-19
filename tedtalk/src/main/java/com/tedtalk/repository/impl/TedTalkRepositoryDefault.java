package com.tedtalk.repository.impl;

import com.tedtalk.domain.dto.TedTalkDTO;
import com.tedtalk.repository.TedTalkRepository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
public class TedTalkRepositoryDefault implements TedTalkRepository {
    private Map<Long, TedTalkDTO> tedTalks;
    private AtomicLong tedTalkIdGenerator;

    public TedTalkRepositoryDefault() {
        tedTalks = new HashMap<>();
        tedTalkIdGenerator = new AtomicLong(1L);
    }

    @Override
    public List<TedTalkDTO> getAll(Map<String, Object> filters) {
        if(filters.isEmpty())
            return new ArrayList<>(tedTalks.values());

        List<TedTalkDTO> tedTalkDTOS = tedTalks.values()
                .stream()
                .filter(tedTalkDTO -> {
                    if(filters.containsKey("author") && filters.get("author").equals(tedTalkDTO.getAuthor()))
                        return true;
                    if(filters.containsKey("title") && filters.get("title").equals(tedTalkDTO.getTitle()))
                        return true;
                    if(filters.containsKey("likes") && filters.get("likes").equals(tedTalkDTO.getLikes()))
                        return true;
                    if(filters.containsKey("views") && filters.get("views").equals(tedTalkDTO.getViews()))
                        return true;
                    return false;
                })
                .collect(Collectors.toList());

        return tedTalkDTOS;
    }

    @Override
    public Optional<TedTalkDTO> getTedTalk(long id) {

        if(tedTalks.containsKey(id)) return Optional.of(tedTalks.get(id));
        return Optional.empty();
    }

    @Override
    public TedTalkDTO insertTedTalk(TedTalkDTO tedTalk) {
        tedTalk.setId(tedTalkIdGenerator.getAndIncrement());
        tedTalks.put(tedTalk.getId(), tedTalk);
        return tedTalk;
    }

    @Override
    public void insertTedTalk(List<TedTalkDTO> tedTalks) {
        tedTalks.forEach(tedTalk -> {
            insertTedTalk(tedTalk);
        });
    }

    @Override
    public TedTalkDTO updateTedTalk(TedTalkDTO tedTalk) {
        tedTalks.put(tedTalk.getId(), tedTalk);
        return tedTalk;
    }

    @Override
    public boolean delete(long id) {
        if(tedTalks.containsKey(id)) {
            tedTalks.remove(id);
            return true;
        }
        return false;
    }
}
