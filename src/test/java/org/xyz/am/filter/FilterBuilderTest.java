package org.xyz.am.filter;

import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.google.gson.Gson;

public class FilterBuilderTest {
	// Name Joe AND age > 30 AND role administrator
	private static final String RULES = "{AND=[{Equals={name=firstname, value=[Joe]}}, {GreatThan={name=age, value=[\"30\"]}}, {Equals={name=role, value=[administrator]}}]}";

	@Test
	public void testFilterBuilder() throws Exception {
		FilterBuilder filterBuilder = new FilterBuilder();
		Map<String, String> resource = new LinkedHashMap<>();

		resource.put("firstname", "Joe");
		resource.put("surname", "Bloggs");
		resource.put("role", "administrator");
		resource.put("age", "35");

		Map<String, Object> rules = new Gson().fromJson(RULES, Map.class);
		Filter filter = filterBuilder.processRules(rules);
		assertTrue(filter.matches(resource));

		resource.put("firstname", "jOE");
		assertTrue(filter.matches(resource));
	}
}
