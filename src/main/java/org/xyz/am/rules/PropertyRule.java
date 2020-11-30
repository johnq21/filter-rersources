package org.xyz.am.rules;

public final class PropertyRule {
	private final String propertyName;
	private final String propertyValue;

	public PropertyRule(String propertyName, String propertyValue) {
		this.propertyName = propertyName;
		this.propertyValue = propertyValue;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public String getPropertyValue() {
		return propertyValue;
	}
}
