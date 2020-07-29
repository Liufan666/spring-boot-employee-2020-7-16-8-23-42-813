package com.thoughtworks.springbootemployee.utils;

import com.thoughtworks.springbootemployee.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class PageHelper {

    public static <T> List<T> page(int page, int pageSize,List<T> list){
        List<T> result = new ArrayList<>();
        int startIndex = (page-1)*pageSize;
        int endIndex = pageSize*page;
        for (int index = startIndex; index < Math.min(endIndex, list.size()); index++) {
            result.add(list.get(index));
        }
        return result;
    }
}
