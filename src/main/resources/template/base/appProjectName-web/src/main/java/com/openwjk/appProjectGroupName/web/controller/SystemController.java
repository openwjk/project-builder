package com.openwjk.#{appProjectName}.web.controller;

import com.openwjk.#{appProjectName}.service.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/21 13:24
 */
@RestController
@RequestMapping("/")
@Slf4j
public class SystemController {

    @Autowired
    SystemService systemService;

    @GetMapping("/check.htm")
    public String checkRun() {
        log.info("success.");
        return systemService.checkRun();
    }
}
