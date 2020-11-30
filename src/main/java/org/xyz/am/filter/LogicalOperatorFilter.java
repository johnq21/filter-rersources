package org.xyz.am.filter;

public abstract class LogicalOperatorFilter implements Filter {
	protected Filter left;
	protected Filter right;
}
