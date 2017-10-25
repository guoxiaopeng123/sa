package com.initCache;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @描述 : 
 * @创建者 : guoxiaopeng
 * @创建时间 : 2017-9-18 下午4:41:15
 */
public class CacheInit implements ApplicationContextAware,InitializingBean{

    public static LikeSearch likeSearch=new LikeSearch();
    public void afterPropertiesSet() throws Exception {
        List<String>  li=new ArrayList<String> () ;
        for(String s: li){
            likeSearch.put(s,s);
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
           
    }

}
