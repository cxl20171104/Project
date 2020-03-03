package com.alphasta.service;

import com.alphasta.model.User;

public interface ComService {
	public boolean hasPurview(User user, String purview);
}
