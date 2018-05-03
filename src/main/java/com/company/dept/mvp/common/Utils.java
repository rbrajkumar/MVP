package com.company.dept.mvp.common;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public static synchronized String extractFileName(final String url) {
		String[] paths = url.split(AppConstants.FWD_SLASH);
		return paths[paths.length-1];
	}
}
