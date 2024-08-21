package com.emre.security.controller;


import com.emre.security.service.PyomoService;
import com.emre.security.util.PyomoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pyomo")
@RequiredArgsConstructor
public class PyomoController {

    PyomoService pyomoService;

    @GetMapping("/runScript")
    public String runScript() {

        return pyomoService.runScript();
    }
}
