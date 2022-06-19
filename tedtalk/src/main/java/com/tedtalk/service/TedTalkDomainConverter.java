package com.tedtalk.service;

import com.tedtalk.domain.dto.TedTalkDTO;
import com.tedtalk.domain.utilitypojo.TedTalkCSV;
import com.tedtalk.domain.vo.TedTalkVO;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class TedTalkDomainConverter {

    public static Optional<TedTalkDTO> convertCSVToDTO(TedTalkCSV tedTalkCSV) {
        if(tedTalkCSV.getTitle() == null || tedTalkCSV.getTitle().isEmpty() ||
                tedTalkCSV.getAuthor() == null || tedTalkCSV.getAuthor().isEmpty())
            return Optional.empty();

        TedTalkDTO tedTalk = new TedTalkDTO();
        tedTalk.setTitle(tedTalkCSV.getTitle());
        tedTalk.setAuthor(tedTalkCSV.getAuthor());
        tedTalk.setLink(tedTalkCSV.getLink());

        try {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .append(DateTimeFormatter.ofPattern("MMMM uuuu"))
                    .toFormatter(Locale.ENGLISH);
            LocalDate dateTime = YearMonth.parse(tedTalkCSV.getDate(), formatter).atDay(1);
            tedTalk.setDate(dateTime);
        } catch(DateTimeParseException e) {
            //log DateTimeParseException exception
        }

        try {
            tedTalk.setLikes(Long.parseLong(tedTalkCSV.getLikes()));
            tedTalk.setViews(Long.parseLong(tedTalkCSV.getViews()));
        } catch (NumberFormatException numberFormatException) {
            //log ArithmeticException exception
        }
        return Optional.of(tedTalk);
    }

    public static List<TedTalkDTO> convertCSVToDTO(List<TedTalkCSV> tedTalkCSVs) {
        List<TedTalkDTO> tedTalkDTOs = new ArrayList<>();

        if(tedTalkCSVs == null || tedTalkCSVs.isEmpty())
            return tedTalkDTOs;

        tedTalkCSVs.forEach(tedTalkCSV -> {
            Optional<TedTalkDTO> tedTalkDTO = convertCSVToDTO(tedTalkCSV);
            if(tedTalkDTO.isPresent()) tedTalkDTOs.add(tedTalkDTO.get());
        });

        return tedTalkDTOs;
    }

    public static List<TedTalkVO> convertDTOToVO(List<TedTalkDTO> tedTalkDTOs) {
        List<TedTalkVO> tedTalkVOs = new ArrayList<>();
        tedTalkDTOs.forEach(tedTalkDTO -> {
            tedTalkVOs.add(convertDTOToVO(tedTalkDTO));
        });
        return tedTalkVOs;
    }

    public static TedTalkVO convertDTOToVO(TedTalkDTO tedTalkDTO) {
        TedTalkVO tedTalkVO = new TedTalkVO();
        tedTalkVO.setId(tedTalkDTO.getId());
        tedTalkVO.setTitle(tedTalkDTO.getTitle());
        tedTalkVO.setAuthor(tedTalkDTO.getAuthor());
        tedTalkVO.setDate(tedTalkDTO.getDate());
        tedTalkVO.setLikes(tedTalkDTO.getLikes());
        tedTalkVO.setViews(tedTalkDTO.getViews());
        tedTalkVO.setLink(tedTalkDTO.getLink());

        return tedTalkVO;
    }

    public static TedTalkDTO convertVOToDTO(TedTalkVO tedTalkVO) {
        TedTalkDTO tedTalkDTO = new TedTalkDTO();
        tedTalkDTO.setId(tedTalkVO.getId());
        tedTalkDTO.setTitle(tedTalkVO.getTitle());
        tedTalkDTO.setAuthor(tedTalkVO.getAuthor());
        tedTalkDTO.setDate(tedTalkVO.getDate());
        tedTalkDTO.setLikes(tedTalkVO.getLikes());
        tedTalkDTO.setViews(tedTalkVO.getViews());
        tedTalkDTO.setLink(tedTalkVO.getLink());

        return tedTalkDTO;
    }
}
