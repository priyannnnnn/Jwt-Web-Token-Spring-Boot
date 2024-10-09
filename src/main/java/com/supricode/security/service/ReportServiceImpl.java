package com.supricode.security.service;

import com.supricode.security.interfacee.ReportInterface;
import com.supricode.security.interfacee.ReportRepository;
import com.supricode.security.interfacee.ReportService;
import com.supricode.security.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public Report createReport(MultipartFile picture, String category_report, String description, String location) throws IOException{
        Report report = new Report();
        report.setPicture(picture.getBytes());
        report.setCategory_report(category_report);
        report.setDescription(description);
        report.setLocation(location);
//        report.setTime(time);
//        report.setStatus(status);
//        report.setDescription_admin(description_admin);
        return reportRepository.save(report);
    }

    @Override
    public List<Report> getAllReports(){
        return reportRepository.findAll().stream().map(image -> {
            image.setBase64Data(Base64.getEncoder().encodeToString(image.getPicture()));
            return image;
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<Report> getReportById(Long id) {
        return reportRepository.findById(id);
    }

    @Override
    public Report updateReport(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public List<Report> getReportsByStatus(String status) {
        return reportRepository.findByStatus(status);
    }
}
