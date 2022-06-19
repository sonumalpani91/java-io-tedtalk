package com.tedtalk.service.impl;

import com.tedtalk.config.TedTalkProperties;
import com.tedtalk.domain.dto.TedTalkDTO;
import com.tedtalk.domain.utilitypojo.TedTalkCSV;
import com.tedtalk.domain.vo.TedTalkVO;
import com.tedtalk.exception.RequestConsistencyException;
import com.tedtalk.repository.TedTalkRepository;
import com.tedtalk.service.TedTalkDomainConverter;
import com.tedtalk.service.TedTalkManager;
import com.tedtalk.utility.CSVReader;
import org.apache.commons.configuration.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class TedTalkManagerDefault implements TedTalkManager {

    private TedTalkProperties properties;
    private CSVReader<TedTalkCSV> csvReader;
    private TedTalkRepository tedTalkRepository;

    public TedTalkManagerDefault(TedTalkRepository tedTalkRepository,
                                 CSVReader<TedTalkCSV> csvReader, TedTalkProperties properties) {
        this.tedTalkRepository = tedTalkRepository;
        this.csvReader = csvReader;
        this.properties = properties;
    }

    @Override
    public List<TedTalkVO> getALlTedTalks(Map<String, Object> filters) {
        List<TedTalkDTO> tedTalkDTOS = tedTalkRepository.getAll(filters);
        return TedTalkDomainConverter.convertDTOToVO(tedTalkDTOS);
    }

    @Override
    public Optional<TedTalkVO> getTedTalk(long id) {
        Optional<TedTalkDTO> tedTalkDTO = tedTalkRepository.getTedTalk(id);
        if(tedTalkDTO.isPresent()) {
            return Optional.of(TedTalkDomainConverter.convertDTOToVO(tedTalkDTO.get()));
        }
        return Optional.empty();
    }

    @Override
    public TedTalkVO insertTedTalk(TedTalkVO tedTalk) {
        TedTalkDTO tedTalkDTO = tedTalkRepository.insertTedTalk(TedTalkDomainConverter.convertVOToDTO(tedTalk));
        tedTalk.setId(tedTalkDTO.getId());
        return tedTalk;
    }

    @Override
    public void insertTedTalk(List<TedTalkVO> tedTalks) {

    }

    @Override
    public TedTalkVO updateTedTalk(TedTalkVO tedTalk) {
        if(tedTalk.getId() == null)
            throw new RequestConsistencyException("Invalid request, TedTalk Id is empty");

        if(!tedTalkRepository.getTedTalk(tedTalk.getId()).isPresent())
            throw new RequestConsistencyException("Invalid request, TedTalk not present");

        tedTalkRepository.updateTedTalk(TedTalkDomainConverter.convertVOToDTO(tedTalk));
        return tedTalk;
    }

    @Override
    public boolean deleteTedTalk(long id) {
        if(!tedTalkRepository.getTedTalk(id).isPresent())
            throw new RequestConsistencyException("Invalid request, TedTalk not present");
        return tedTalkRepository.delete(id);
    }

    @PostConstruct
    public void init() {
        try {
            List<TedTalkCSV> entities = csvReader.readCSV("/Users/sonumalpani/Sonu/codingPractice/iodigital/java-io-tedtalk/tedtalk/src/main/java/com/tedtalk/utility/data.csv", TedTalkCSV.class);
            List<TedTalkDTO> tedTalkDTOS = TedTalkDomainConverter.convertCSVToDTO(entities);
            tedTalkRepository.insertTedTalk(tedTalkDTOS);
        } catch (FileNotFoundException fne) {
            //log this exception
        }
    }
}
