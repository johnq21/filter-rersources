package org.xyz.am.filter;

import java.util.Map;

public abstract class BooleanFilter implements Filter {
	protected String propertyName;
	protected String propertyValue;

	protected String findResourceValue(Map<String, String> resource) {
		return resource.get(propertyName);
	}
}
