package com.emre.security.util;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

@Service
public class PyomoUtil {

    public String runScript() {
        StringBuilder output = new StringBuilder();
        try {
            ProcessBuilder pb = new ProcessBuilder("python","C:\\Users\\Administrator\\Desktop\\vscode\\wfm\\main.py");
            //C:\Users\Administrator\Desktop\vscode\wfm

            pb.directory(new File("C:\\Users\\Administrator\\Desktop\\vscode\\wfm"));
            // Start the process
            Process p = pb.start();
            // Capture the output from the script
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }
            //get errorStream
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                output.append(line);
            }



            // Wait for the process to finish
            int exitCode = p.waitFor();
            if (exitCode != 0) {

                return "Exit Code:" + exitCode + "\n" + output.toString();
            }


        }catch (Exception e) {
            e.printStackTrace();
        }

        return  output.toString();
    }
}
