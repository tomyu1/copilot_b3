package com.example.copilot.controller;

import com.example.copilot.entry.Holiday;
import com.example.copilot.service.HolidayMntService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private HolidayMntService holidayMntService;

    // health check endpoint
    @GetMapping("/health")
    public String health() {
        return "ok";
    }


   // insert json data
    @PostMapping("/insert")
    public String insert(@RequestBody List<Holiday> holidays) {
        // country code, holiday date has text
        for (Holiday holiday : holidays) {
            if (!StringUtils.hasText(holiday.getCountryCode()) || !StringUtils.hasText(holiday.getHolidayDate())) {
                return "fail,invalid param";
            }
        }

        long line = holidayMntService.insert(holidays);

        return "succ " + line;
    }

    // update data by json
    @PostMapping("/updateByCountryCodeAndHolidayDate")
    public String update(@RequestBody List<Holiday> holidays) {

        long line = holidayMntService.update(holidays);

        return "succ " + line;
    }

    // remove data by json
    @PostMapping("/removeByCountryCodeAndHolidayDate")
    public String remove(@RequestBody Holiday holiday) {
        // country code, holiday date can't be null
        if (!StringUtils.hasText(holiday.getCountryCode()) || !StringUtils.hasText(holiday.getHolidayDate())) {
            return "fail,invalid param";
        }

        long line = holidayMntService.remove(holiday);

        return "succ " + line;
    }

    // return all data by country code and after a date
    @GetMapping("/queryNextYear")
    public List<Holiday> queryNextYear(@RequestParam String countryCode) throws Exception {
        // country code has text
        if (!StringUtils.hasText(countryCode)) {
            return null;
        }
        Date startDate = DateUtils.addDays(new Date(),-1);
        Date endDate = DateUtils.addYears(startDate,1);
        return holidayMntService.queryAllByTimeRanage(countryCode,  startDate,  endDate);
    }

    // query next holiday by country code and current date
    @GetMapping("/queryNextHoliday")
    public Holiday queryNextHoliday(@RequestParam String countryCode) throws Exception {
        return holidayMntService.queryNextHoliday(countryCode);
    }

    // query input date is holiday or not
    @GetMapping("/queryIsHoliday")
    public String queryIsHoliday(@RequestParam String date) {
        return holidayMntService.queryIsHoliday(date);
    }


}
