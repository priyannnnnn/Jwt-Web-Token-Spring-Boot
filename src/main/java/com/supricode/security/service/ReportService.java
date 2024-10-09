package com.supricode.security.service;

import com.supricode.security.interfacee.ReportInterface;
import com.supricode.security.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportInterface reportInterface;

//    public Report saveReport(MultipartFile file)throws IOException{
//        Report report = new Report();
////        report.setCategory_report(reportDetails.getCategory_report());
////        report.setDescription(reportDetails.getDescription());
////        report.setLocation(reportDetails.getLocation());
//        report.setPicture(file.getBytes());
//
//        return reportInterface.save(report);
//    }
//    public List<Report> getAllReport(){
//        return reportInterface.findAll();
//    }

    public List<Report> getAllReports() {
        return reportInterface.findAll();
    }

    public Optional<Report> getReportById(Long id) {
        return reportInterface.findById(id);
    }

    public Report createReport(Report report) {
        return reportInterface.save(report);
    }
}
