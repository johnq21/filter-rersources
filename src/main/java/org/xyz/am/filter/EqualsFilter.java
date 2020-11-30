package org.xyz.am.filter;

import java.util.Map;

import org.xyz.am.rules.PropertyRule;

public class EqualsFilter extends ComparisonFilter {

	public EqualsFilter(PropertyRule propertyRule) {
		propertyName = propertyRule.getPropertyName();
		propertyValue = propertyRule.getPropertyValue();
	}

	@Override
	public boolean matches(Map<String, String> resource) throws Exception {
		String resourceValue = findResourceValue(resource);
		return resourceValue.equalsIgnoreCase(propertyValue);
	}
}
