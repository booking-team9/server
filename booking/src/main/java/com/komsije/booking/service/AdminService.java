package com.komsije.booking.service;

import com.komsije.booking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AccountRepository accountRepository;
}