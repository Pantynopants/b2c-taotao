package com.neusoft.search.service;

import com.neusoft.search.pojo.SearchResult;

public interface SearchService {

	SearchResult search(String queryString, int page, int rows) throws Exception;
}
