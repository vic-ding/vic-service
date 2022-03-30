package com.vic.es.utils;

public class PageUtil {
    private PageUtil(){}

    private static final int DEFAULT_PAGE_INDEX = 1;

    private static final int DEFAULT_PAGE_SIZE = 10;

    public static int convertPageIndex(Integer pageIndex, Integer pageSize) {
        pageIndex = checkPageIndex(pageIndex);
        pageSize = checkPageSize(pageSize);
        return (pageIndex -1) * pageSize + 1;
    }

    public static int convertPageSize(Integer pageIndex, Integer pageSize) {
        pageIndex = checkPageIndex(pageIndex);
        pageSize = checkPageSize(pageSize);
        return pageIndex * pageSize;
    }

    public static int checkPageIndex(Integer pageIndex) {
        if(null == pageIndex || 0 == pageIndex){
            return DEFAULT_PAGE_INDEX;
        }
        return pageIndex;
    }

    public static int checkPageSize(Integer pageSize) {
        if(null == pageSize || 0 == pageSize){
            return DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }
}
