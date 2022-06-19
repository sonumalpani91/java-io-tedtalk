package com.tedtalk.utility;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Component
public class CSVReader<T> {

    public List<T> readCSV(String fileName, Class<T> clazz) throws FileNotFoundException {
        CsvToBean<T> beans = new CsvToBeanBuilder(new FileReader(fileName))
                .withType(clazz)
                .withIgnoreQuotations(true)
                .withThrowExceptions(false)
                .build();

        final List<T> entities = beans.parse();//2

        entities.stream().forEach((entity) -> {
            System.out.println("Parsed data:" + entity.toString());
        });

        beans.getCapturedExceptions().stream().forEach((exception) -> { //3
            System.out.println("Inconsistent data:" +
                    String.join("||", exception.getLine()));//4
        });
        return entities;
    }
}
