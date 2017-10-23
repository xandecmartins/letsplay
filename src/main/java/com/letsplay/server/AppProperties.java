package com.letsplay.server;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("letsplay")
public class AppProperties {
	
	public String getUrlBGG() {
		return urlBGG;
	}

	public void setUrlBGG(String urlBGG) {
		this.urlBGG = urlBGG;
	}

	private String urlBGG;
	
}