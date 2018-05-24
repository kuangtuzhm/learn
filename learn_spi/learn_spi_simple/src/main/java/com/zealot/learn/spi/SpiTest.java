package com.zealot.learn.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

import com.zealot.learn.spi.service.Search;

public class SpiTest {

	public static void main(String[] args) {
		
		ServiceLoader<Search> s = ServiceLoader.load(Search.class);    
        Iterator<Search> searchs = s.iterator();    
        while (searchs.hasNext()) {    
            Search curSearch = searchs.next();    
            curSearch.search("test");    
        }    
	}

}
