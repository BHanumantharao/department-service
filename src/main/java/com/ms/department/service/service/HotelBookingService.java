package com.ms.department.service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class HotelBookingService {

    @Autowired
    private static final Map<Integer, String> immutableMap;
    static {
        Map<Integer, String> mutableMap = new HashMap<>();
        mutableMap.put(101, "one");
        mutableMap.put(2, "two");
        immutableMap = Collections.unmodifiableMap(mutableMap);
    }
}
