package com.emre.security.service;


import com.emre.security.util.PyomoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PyomoService {

    private PyomoUtil pyomoUtil;

    public String runScript() {
        return pyomoUtil.runScriptAsync();
    }

}
