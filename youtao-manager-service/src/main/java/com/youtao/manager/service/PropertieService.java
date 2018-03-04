package com.youtao.manager.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PropertieService {
	
	@Value("${youtao.manager.image.repositoryPath}")
	public String repositoryPath;
	@Value("${youtao.manager.image.baseUrl}")
	public String imageBaseUrl;

}
