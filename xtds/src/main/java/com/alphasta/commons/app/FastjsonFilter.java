package com.alphasta.commons.app;

import com.alibaba.fastjson.serializer.PropertyFilter;
import java.util.HashSet;
import java.util.Set;

public class FastjsonFilter implements PropertyFilter {

	public FastjsonFilter() {
	}

	public Set getIncludes() {
		return includes;
	}

	public Set getExcludes() {
		return excludes;
	}

	public boolean apply(Object source, String name, Object value) {
		if (excludes.contains(name))
			return false;
		if (excludes.contains((new StringBuilder(String.valueOf(source
				.getClass().getSimpleName()))).append(".").append(name)
				.toString()))
			return false;
		if (includes.size() == 0 || includes.contains(name))
			return true;
		return includes.contains((new StringBuilder(String.valueOf(source
				.getClass().getSimpleName()))).append(".").append(name)
				.toString());
	}

	private final Set includes = new HashSet();
	private final Set excludes = new HashSet();
}
