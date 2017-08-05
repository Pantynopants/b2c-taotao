package com.neusoft.portal.service;

import com.neusoft.portal.pojo.SearchResult;

public interface SearchService {

	SearchResult search(String queryString, int page);
}
