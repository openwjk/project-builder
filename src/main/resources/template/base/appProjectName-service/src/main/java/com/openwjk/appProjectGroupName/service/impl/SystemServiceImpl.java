package com.openwjk.#{appProjectName}.service.impl;

import com.openwjk.#{appProjectName}.service.service.SystemService;
import org.springframework.stereotype.Service;

@Service
public class SystemServiceImpl implements SystemService {
    @Override
    public String checkRun() {
        return "success.";
    }
}
