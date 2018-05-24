package com.zealot.learn.spi;

import com.zealot.learn.spi.service.Search;
import com.zealot.learn.spi.service.impl.DBSearch;
import com.zealot.learn.spi.service.impl.FileSearch;

public class SimpleTest {

	public static void main(String[] args) {
		
		Search fileSearch = new FileSearch();

		fileSearch.search("zhm");
		
		Search dbSearch = new DBSearch();
		dbSearch.search("zhm");
	}

}
