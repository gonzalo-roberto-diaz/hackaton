package com.neuralhack.service;

import com.neuralhack.domain.BusinessStructure;
import com.neuralhack.domain.LoanApplicationInfo;
import com.neuralhack.domain.LogRecord;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gonzalo on 2/14/2016.
 */
public class LogGenerator {




    public static void main(String ... vars) throws  Exception{
      generateFile();
       // toConsole();
    }

    private static Set<LogRecord> createDecisions(){
        CreditDecisionService decisionService = new CreditDecisionService();
        Set<LogRecord> logEntries = new HashSet<>();

        int totalAccepted =0;
        int totalRejected = 0;


        for (int i=0; i<5_000; i++){
            LoanApplicationInfo application = RandomApplicationFactory.create();
            LogRecord newRecord = Converter.loanAppToLogRecord(application);
            boolean decision = decisionService.decide(application);
            newRecord.setApproved(decision);
            logEntries.add(newRecord);
        }

        for (LogRecord record: logEntries) {
            if (record.isApproved()){
                totalAccepted++;
            }else{
                totalRejected++;
            }
        }

        System.out.println("accepted =" + totalAccepted + " rejected=" + totalRejected);
        System.out.println("percentage accepted =" + ((double)totalAccepted / (double)(totalAccepted + totalRejected) ));
        return logEntries;
    }

    public static void toConsole(){
         createDecisions();
    }

    public static void generateFile() throws Exception{
        Charset charset = Charset.forName("UTF-8");
        Path path = FileSystems.getDefault().getPath(".", "training-set-1.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
            Set<LogRecord>  logEntries = createDecisions();

            CreditDecisionService decisionService = new CreditDecisionService();
            for (LogRecord record : logEntries){
                String line = record.toCSVString().concat("\n");
                writer.write(line, 0, line.length());
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

}
