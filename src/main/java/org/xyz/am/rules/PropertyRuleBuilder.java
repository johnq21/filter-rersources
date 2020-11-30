package org.xyz.am.rules;

/**
 * A builder for property rules
 */
public final class PropertyRuleBuilder {
	private PropertyRule propertyRule;
	private String propertyName;
	private String propertyValue;

	public PropertyRuleBuilder() {
	}

	public PropertyRuleBuilder(String propertyName, String propertyValue) {
		this.propertyName = propertyName;
		this.propertyValue = propertyValue;
	}

	public PropertyRuleBuilder propertyName(String propertyName) {
		this.propertyName = propertyName;
		return this;
	}

	public PropertyRuleBuilder propertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
		return this;
	}

	public PropertyRule build() {
		propertyRule = new PropertyRule(propertyName, propertyValue);
		return propertyRule;
	}
}
