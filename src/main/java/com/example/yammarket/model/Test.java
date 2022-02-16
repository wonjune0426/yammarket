package com.example.yammarket.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Test {
    private String msg;


    @Override
    public String toString() {
        return "Test{" +
                "msg='" + msg + '\'' +
                '}';
    }

}
