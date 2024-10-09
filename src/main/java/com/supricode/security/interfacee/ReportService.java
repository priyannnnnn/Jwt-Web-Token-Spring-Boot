package com.supricode.security.interfacee;

import com.supricode.security.model.Report;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ReportService {
    Report createReport(MultipartFile picture, String category_report, String description, String location) throws IOException;
    List<Report> getAllReports();
    Optional<Report> getReportById(Long id);
    Report updateReport(Report report) throws IOException;
    List<Report> getReportsByStatus(String status);
}
