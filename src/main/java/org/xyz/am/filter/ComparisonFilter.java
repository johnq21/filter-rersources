package org.xyz.am.filter;

import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.xyz.am.util.IllegalArgumentsFoundException;
import org.xyz.am.util.MissingPropertyNameException;
import org.xyz.am.util.MissingPropertyValueException;

public abstract class ComparisonFilter implements Filter {
	protected String propertyName;
	protected String propertyValue;

	protected String findResourceValue(Map<String, String> resource) throws Exception {
		String resourceValue = resource.get(propertyName);
		if (resourceValue != null) {
			if (!resourceValue.isEmpty()) {
				return resourceValue;
			} else {
				throw new MissingPropertyValueException(
						"Property value cannot be found. PropertyName: " + propertyName);
			}
		} else {
			throw new MissingPropertyNameException("Property name cannot be found. PropertyName: " + propertyName);
		}
	}

	protected double checkIsNumbericValue(String value) throws Exception {
		if (NumberUtils.isParsable(value)) {
			return Double.parseDouble(value);
		} else {
			throw new IllegalArgumentsFoundException("Value is not numeric. Value: " + value);
		}
	}
}
