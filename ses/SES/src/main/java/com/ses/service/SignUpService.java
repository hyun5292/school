package com.ses.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ses.dao.SignUpDAO;

@Service
public class SignUpService implements SUService{
	@Inject
	SignUpDAO suDAO;
}
