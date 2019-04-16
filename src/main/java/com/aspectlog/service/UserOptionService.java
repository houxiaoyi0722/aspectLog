package com.aspectlog.service;

import org.springframework.stereotype.Service;

@Service
public class UserOptionService {


    public String test(String aaa) {
            System.out.println(aaa);
        return  aaa;
    }
}
