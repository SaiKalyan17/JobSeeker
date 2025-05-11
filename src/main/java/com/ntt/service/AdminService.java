package com.ntt.service;

import com.ntt.entity.Admin;

public interface AdminService {

	Admin register(Admin admin);

	Admin findByEmailAndPassword(String email, String password);

}
