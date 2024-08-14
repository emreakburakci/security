package com.emre.security.util;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.logging.Handler;
import java.util.logging.Logger;

@Service
public class PyomoUtil {

    Logger logger = Logger.getLogger(PyomoUtil.class.getName());

    public String runScript() {
        StringBuilder output = new StringBuilder();
        try {
            ProcessBuilder pb = new ProcessBuilder("python -u","C:\\Users\\Administrator\\Desktop\\vscode\\wfm\\main.py");
            //C:\Users\Administrator\Desktop\vscode\wfm

            pb.directory(new File("C:\\Users\\Administrator\\Desktop\\vscode\\wfm"));
            // Start the process
            Process p = pb.start();
            // Capture the output from the script
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
                logger.info(line);
                flushLogs();
            }
            //get errorStream
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                output.append(line);
                logger.warning(line);
                flushLogs();
            }



            // Wait for the process to finish
            int exitCode = p.waitFor();
            if (exitCode != 0) {
                logger.info("Exit Code:" + exitCode + "\n" + output.toString());
                flushLogs();

                return "Exit Code:" + exitCode + "\n" + output.toString();
            }


        }catch (Exception e) {
            e.printStackTrace();
            logger.warning(e.getMessage());
            flushLogs();
        }

        return  output.toString();
    }

    private void flushLogs() {
        for (Handler handler : logger.getHandlers()) {
            handler.flush();
        }
    }
}
