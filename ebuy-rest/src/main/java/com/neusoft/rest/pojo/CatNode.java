package com.neusoft.rest.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CatNode {

	@JsonProperty("n")//键值对应，name使用n来标注代替
	private String name;
	@JsonProperty("u")
	private String url;
	@JsonProperty("i")
	private List<?> item;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<?> getItem() {
		return item;
	}
	public void setItem(List<?> item) {
		this.item = item;
	}
	
	
	
}
