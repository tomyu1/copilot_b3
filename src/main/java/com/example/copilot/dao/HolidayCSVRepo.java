package com.example.copilot.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.copilot.entry.Holiday;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.ICSVParser;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Component
@Slf4j
public class HolidayCSVRepo implements HolidayRepo {

    private String dataFilePath = "holidays.csv";

    @Override
    public void insert(Holiday holidays) {
        try(
                FileWriter fileWriter = new FileWriter(ResourceUtils.getFile("classpath:"+dataFilePath).getPath(),true);
                CSVWriter csvWriter = new CSVWriter(fileWriter, ',',
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.NO_ESCAPE_CHARACTER,
                        System.getProperty("line.separator"));
        ){
            csvWriter.writeNext(holidays.toCSVString().split(","));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void overwriteAll(List<Holiday> holidays) {
        try(
                FileWriter fileWriter = new FileWriter("src/main/resources/holidays.csv");
                CSVWriter csvWriter = new CSVWriter(fileWriter, ',',
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.NO_ESCAPE_CHARACTER,
                        System.getProperty("line.separator"));
        ){
            for (Holiday holiday : holidays) {
                csvWriter.writeNext(holiday.toCSVString().split(","));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Holiday> selectAll() {
        List<Holiday> res = new ArrayList<>();
        try(
                FileReader fileReader = new FileReader("src/main/resources/holidays.csv");
                CSVReader csvReader = new CSVReader(fileReader,',','"', ICSVParser.DEFAULT_ESCAPE_CHARACTER);
                 ){

//            String[] header = csvReader.readNext();
            for (String[] strings : csvReader.readAll()) {
                Holiday holiday = new Holiday();
                holiday.setCountryCode(strings[0]);
                holiday.setCountryDesc(strings[1]);
                holiday.setHolidayDate(strings[2]);
                holiday.setHolidayName(strings[3]);
                res.add(holiday);
            }
            log.info("all : {}",res);
            return res;
        }catch (Exception e){
            log.error("read csv file error",e);
            return Collections.emptyList();
        }
    }


    @Test
    public void test(){
        selectAll();
    }

    @Test
    public void testInsert(){
        Holiday holiday = new Holiday();
        holiday.setCountryCode("CN");
        holiday.setCountryDesc("China");
        holiday.setHolidayDate("2024-10-01");
        holiday.setHolidayName("24 National Day");
        insert(holiday);
    }

}
