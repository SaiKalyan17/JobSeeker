package com.ntt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntt.entity.Admin;
import com.ntt.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin register(Admin admin) {
		// TODO Auto-generated method stub
		return adminRepository.save(admin);
	}

	@Override
	public Admin findByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return adminRepository.findByEmailAndPassword(email, password);
	}

}
