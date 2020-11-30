package org.xyz.am.filter;

import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import org.xyz.am.rules.PropertyRule;
import org.xyz.am.rules.PropertyRuleBuilder;

public class LiteralBooleanTest {

	@Test
	public void testLiteralBooleanFilter() {
		String propertyName = "isMarried";
		String propertyValue = "true";

		Map<String, String> resource = createResource();
		PropertyRuleBuilder builder = new PropertyRuleBuilder();
		PropertyRule propertyRule = builder.propertyName(propertyName).propertyValue(propertyValue).build();
		LiteralBooleanFilter literalBooleanFilter = new LiteralBooleanFilter(propertyRule);

		assertTrue(literalBooleanFilter.matches(resource));
	}

	public Map<String, String> createResource() {
		Map<String, String> resource = new LinkedHashMap<>();

		resource.put("firstname", "Joe");
		resource.put("surname", "Bloggs");
		resource.put("role", "user");
		resource.put("isMarried", "true");

		return resource;
	}
}
