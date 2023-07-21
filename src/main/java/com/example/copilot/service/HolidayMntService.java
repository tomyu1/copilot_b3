package com.example.copilot.service;

import com.example.copilot.dao.HolidayRepo;
import com.example.copilot.entry.Holiday;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HolidayMntService {
    @Autowired
    private HolidayRepo holidayRepo;

    public long insert(List<Holiday> holidays) {
        int line = 0;
        List<Holiday> poList = new ArrayList<>();
        // need a function to write string into csv file
        List<Holiday> all = holidayRepo.selectAll();
           for (Holiday input : holidays) {
              for (Holiday exist : all) {
                  if (exist.getCountryCode().equals(input.getCountryCode()) && exist.getHolidayDate().equals(input.getHolidayDate())){
                      break;
                  }
              }
              poList.add(input);
               line++  ;
           }
        try{
            poList = poList.stream().sorted((a,b) -> a.getHolidayDate().compareTo(b.getHolidayDate())).collect(Collectors.toList());
            holidayRepo.overwriteAll(poList);
        }catch (Exception e){
            log.error("fail to insert {},e :{}",poList,e);
        }
        return line;
    }

    public long update(List<Holiday> holidays) {
        List<Holiday> doList = new ArrayList<>();
        List<Holiday> all = holidayRepo.selectAll();
        for (Holiday input : holidays) {
            for (Holiday holiday : all) {
                if (holiday.getCountryCode().equals(input.getCountryCode()) && holiday.getHolidayDate().equals(input.getHolidayDate())){
                    try{
                        doList.add(input);
                    }catch (Exception e){
                        log.error("fail to insert {},e :{}",input,e);
                    }
                }
            }
        }
        if(doList.size()>0){
            holidayRepo.overwriteAll(doList);
        }
        return doList.size();
    }

    public long remove(Holiday input) {
        int line = 0;
        List<Holiday> poList = new ArrayList<>();
        List<Holiday> holidays = holidayRepo.selectAll();
        for (Holiday all : holidays) {
            if (all.getCountryCode().equals(input.getCountryCode()) && all.getHolidayDate().equals(input.getHolidayDate())){
                line++;
            }else {
                poList.add(all);
            }
        }
        if(line>0){
            holidayRepo.overwriteAll(poList);
        }
        return line;
    }

    public List<Holiday> queryAllByTimeRanage(String countryCode, Date startDate, Date endDate) throws Exception {
        List<Holiday> result = new ArrayList<>();

        List<Holiday> holidays = holidayRepo.selectAll();
        for (Holiday holiday : holidays) {
            if (holiday.getCountryCode().equals(countryCode) && between(holiday.getHolidayDate(),startDate,endDate)){
                result.add(holiday);
            }
        }
        return result;
    }

    // DateUtil dateBetween
    public boolean between(String date, Date startDate, Date endDate) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date input = simpleDateFormat.parse(date);
        return input.after(startDate) && input.before(endDate);
    }

    public boolean after(String inputDate,Date date) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date input = simpleDateFormat.parse(inputDate);
        return input.after(date);
    }

    public Holiday queryNextHoliday(String countryCode) throws Exception {
        List<Holiday> holidays = holidayRepo.selectAll();
        holidays = holidays.stream().sorted((a,b) -> a.getHolidayDate().compareTo(b.getHolidayDate())).collect(Collectors.toList());
        for (Holiday holiday : holidays) {
            if (holiday.getCountryCode().equals(countryCode) && after(holiday.getHolidayDate(),new Date())){
                return holiday;
            }
        }
        return null;
    }

    public String queryIsHoliday(String date) {
        List<Holiday> result = new ArrayList<>();

        List<Holiday> holidays = holidayRepo.selectAll();
        for (Holiday holiday : holidays) {
            if (holiday.getHolidayDate().equals(date)){
                result.add(holiday);
            }
        }

        if(result.size() == 0){
            return "Not holiday";
        }
        return String.format("%S is holiday in [%S]",date,result.stream().map(Holiday::getCountryCode).reduce((a,b) -> a + "," + b).get());
    }


}
