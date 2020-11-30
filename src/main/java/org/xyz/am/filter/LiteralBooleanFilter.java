package org.xyz.am.filter;

import java.util.Map;

import org.xyz.am.rules.PropertyRule;

public class LiteralBooleanFilter extends BooleanFilter {
	public LiteralBooleanFilter(PropertyRule propertyRule) {
		propertyName = propertyRule.getPropertyName();
		propertyValue = propertyRule.getPropertyValue();
	}

	@Override
	public boolean matches(Map<String, String> resource) {
		String resourceValue = findResourceValue(resource);
		return Boolean.parseBoolean(resourceValue) == Boolean.parseBoolean(propertyValue);
	}
}
