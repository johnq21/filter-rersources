package org.xyz.am.filter;

import java.util.Map;

public interface Filter {
	boolean matches(Map<String, String> resource) throws Exception;
}
