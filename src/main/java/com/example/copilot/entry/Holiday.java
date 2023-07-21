package com.example.copilot.entry;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Holiday extends AuditBaseEntry{

    private String countryCode;

    private String countryDesc;

    private String holidayDate;

    private String holidayName;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryDesc() {
        return countryDesc;
    }

    public void setCountryDesc(String countryDesc) {
        this.countryDesc = countryDesc;
    }

    public String getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(String holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    @Override
    public String toString() {
        return "Holiday{" +
                "countryCode='" + countryCode + '\'' +
                ", countryDesc='" + countryDesc + '\'' +
                ", holidayDate='" + holidayDate + '\'' +
                ", holidayName='" + holidayName + '\'' +
                '}';
    }

    public String toCSVString() {
        return  countryCode + ',' +
                countryDesc + ',' +
                holidayDate + ',' +
                holidayName + ',';
    }
}
