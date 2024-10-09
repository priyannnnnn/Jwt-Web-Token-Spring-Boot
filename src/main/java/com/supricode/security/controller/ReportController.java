package com.supricode.security.controller;

import com.supricode.security.interfacee.ReportService;
import com.supricode.security.model.Report;
import com.supricode.security.timer.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth/report")
@CrossOrigin(origins = "http://localhost:3000")
public class ReportController {

    @Autowired
//    private ReportService reportService;
    private ReportService reportService;

    @PostMapping
   public ResponseEntity<Report> createReport(
           @RequestParam("picture") MultipartFile picture,
           @RequestParam("category_report") String category_report,
           @RequestParam("description") String description,
           @RequestParam("location") String location
//           @RequestParam("time") String time,
//           @RequestParam("status") String status,
//           @RequestParam("description_admin") String description_admin

    ){
        try {
            Report report = reportService.createReport(picture, category_report, description, location);
            return new ResponseEntity<>(report, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        return new ResponseEntity<>(reportService.getAllReports(), HttpStatus.OK);
//        return reportService.getAllReports();

    }

//    @GetMapping("/all")
//    public ResponseEntity<List<Image>> getAllImages() {
//        return new ResponseEntity<>(imageService.getAllImages(), HttpStatus.OK);
//    }
//}

//    @GetMapping
//    public ResponseEntity<List<Report>> getAllReports() {
//        List<Report> reports = reportService.getAllReports();
//        if (reports.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(reports, HttpStatus.OK);
//    }

    // Endpoint to get a report by ID
    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable("id") Long id) {
        Optional<Report> report = reportService.getReportById(id);
        if (report.isPresent()) {
            return new ResponseEntity<>(report.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to update a report
    @PutMapping("/update/{id}")
    public ResponseEntity<Report> updateReport(
            @PathVariable("id") Long id,
//            @RequestParam("picture") MultipartFile picture,
//            @RequestParam("category_report") String category_report,
//            @RequestParam("description") String description,
//            @RequestParam("location") String location,
//            @RequestParam("time") String time,
//            @RequestParam("status") String status,
//            @RequestParam("description_admin") String description_admin)
            @RequestBody ReportUpdateRequest updateRequest){
        try {
            Optional<Report> existingReport = reportService.getReportById(id);
            if (existingReport.isPresent()) {
                Report report = existingReport.get();
                report.setCategory_report(updateRequest.getCategory_report());
                report.setDescription(updateRequest.getDescription());
                report.setLocation(updateRequest.getLocation());
                report.setTime(updateRequest.getTime());
                report.setStatus(updateRequest.getStatus());
                report.setDescription_admin(updateRequest.getDescription_admin());
                Report updatedReport = reportService.updateReport(report);
                return new ResponseEntity<>(updatedReport, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Report>> getReportsByStatus(@PathVariable("status") String status) {
        List<Report> reports = reportService.getReportsByStatus(status);
        if (reports.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(reports, HttpStatus.OK);
        }
    }
}
