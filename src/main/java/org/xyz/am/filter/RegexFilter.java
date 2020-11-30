package org.xyz.am.filter;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xyz.am.rules.PropertyRule;

public class RegexFilter extends ComparisonFilter {
	public RegexFilter(PropertyRule propertyRule) {
		propertyName = propertyRule.getPropertyName();
		propertyValue = propertyRule.getPropertyValue();
	}

	@Override
	public boolean matches(Map<String, String> resource) throws Exception {
		String resourceValue = findResourceValue(resource);
		Pattern pattern = Pattern.compile(propertyValue);
		Matcher matcher = pattern.matcher(resourceValue);
		return matcher.matches();
	}
}
