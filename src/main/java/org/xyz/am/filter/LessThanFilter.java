package org.xyz.am.filter;

import java.util.Map;

import org.xyz.am.rules.PropertyRule;

public class LessThanFilter extends ComparisonFilter {
	public LessThanFilter(PropertyRule propertyRule) {
		propertyName = propertyRule.getPropertyName();
		propertyValue = propertyRule.getPropertyValue();
	}

	@Override
	public boolean matches(Map<String, String> resource) throws Exception {
		String resourceValue = findResourceValue(resource);
		return checkIsNumbericValue(resourceValue) < checkIsNumbericValue(propertyValue);
	}
}
