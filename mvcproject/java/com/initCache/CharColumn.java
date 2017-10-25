package com.initCache;

import java.util.Map;

/**
 * @描述 : 
 * @创建者 : guoxiaopeng
 * @创建时间 : 2017-9-18 下午5:02:50
 */
public class CharColumn<T>  {

    /**
     * 天津   天  key value  天津 
     */
    private String keyword;
    private Map<String,String> map;
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public Map<String,String> getMap() {
        return map;
    }
    public void setMap(Map<String,String> map) {
        this.map = map;
    }
    
}
