package com.example.copilot.dao;

import com.example.copilot.entry.Holiday;

import java.util.List;

public interface HolidayRepo {
    void insert(Holiday holidays);

    void overwriteAll(List<Holiday> holidays);

    List<Holiday> selectAll();
}
