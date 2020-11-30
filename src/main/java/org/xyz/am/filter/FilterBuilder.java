package org.xyz.am.filter;

import java.util.List;
import java.util.Map;

import org.xyz.am.rules.PropertyRule;
import org.xyz.am.rules.PropertyRuleBuilder;
import org.xyz.am.util.IllegalArgumentsFoundException;

/**
 * Use individual filters as components to build composite filters based on a
 * set of rules derived from a Json file
 */
public class FilterBuilder {
	// Not all filters are added
	private static final String AND_FILTER = "AND";
	private static final String OR_FILTER = "OR";
	private static final String GREAT_THAN_FILTER = "GreatThan";
	private static final String EQUALS_FILTER = "Equals";

	/**
	 * @param rulesInput input constructed from Json file
	 * @return Filter composite filter
	 * @throws Exception
	 */
	public Filter processRules(Map<String, Object> rulesInput) throws Exception {
		String operatorName = rulesInput.keySet().iterator().next();

		if (isLogicalOperator(operatorName)) {
			List<Object> valuesList = (List<Object>) rulesInput.get(operatorName);
			if (valuesList.size() < 2) {
				throw new IllegalArgumentsFoundException(
						"Expected more than two arguments for operator: " + operatorName);
			}

			Map<String, Object> firstValue = (Map<String, Object>) valuesList.get(0);
			Filter leftFilter = processRules((Map<String, Object>) firstValue);
			// After getting the first filter the rest follows a recursive pattern
			for (int i = 1; i < valuesList.size(); i++) {
				Filter rightFilter = processRules((Map<String, Object>) valuesList.get(i));
				leftFilter = populateLogicalFilter(operatorName, leftFilter, rightFilter);
			}
			return leftFilter;
		} else {
			return buildComparisonFilter(rulesInput);
		}
	}

	private boolean isLogicalOperator(String name) {
		return name.equals("AND") || name.equals("OR");
	}

	private Filter buildComparisonFilter(Map<String, Object> rulesInput) throws Exception {
		String operatorName = rulesInput.keySet().iterator().next();
		Map<String, Object> values = (Map<String, Object>) rulesInput.get(operatorName);

		String propertyName = (String) values.get("name");
		List<String> propertyValues = (List<String>) values.get("value");
		String propertyValue = propertyValues.get(0);

		PropertyRuleBuilder builder = new PropertyRuleBuilder();
		PropertyRule propertyRule = builder.propertyName(propertyName).propertyValue(propertyValue).build();

		return populateComparisonFilter(operatorName, propertyRule);
	}

	private Filter populateComparisonFilter(String operatorName, PropertyRule propertyRule) throws Exception {
		if (operatorName.equals(GREAT_THAN_FILTER)) {
			return new GreatThanFilter(propertyRule);
		} else if (operatorName.equals(EQUALS_FILTER)) {
			return new EqualsFilter(propertyRule);
		} else {
			throw new IllegalArgumentsFoundException("Filter name not found. Filter name: " + operatorName);
		}
	}

	private Filter populateLogicalFilter(String operatorName, Filter leftFilter, Filter rightFilter) throws Exception {
		if (operatorName.equals(AND_FILTER)) {
			return new AndFilter(leftFilter, rightFilter);
		} else if (operatorName.equals(OR_FILTER)) {
			return new OrFilter(leftFilter, rightFilter);
		} else {
			throw new IllegalArgumentsFoundException("Filter name not found. Filter name: " + operatorName);
		}
	}
}
