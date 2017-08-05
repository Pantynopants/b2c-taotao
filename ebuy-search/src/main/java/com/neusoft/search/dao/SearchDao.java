package com.neusoft.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.neusoft.search.pojo.SearchResult;

public interface SearchDao {

	SearchResult search(SolrQuery query) throws Exception;
}
