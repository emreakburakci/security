package com.emre.security.controller;


import com.emre.security.util.PyomoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pyomo")
public class PyomoController {

    @Autowired
    PyomoUtil pyomoUtil;

    @GetMapping("/runScript")
    public String pyomo() {
        String result = pyomoUtil.runScript();
        return result;
    }
}
