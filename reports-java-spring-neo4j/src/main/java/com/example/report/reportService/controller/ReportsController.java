package com.example.report.reportService.controller;

import com.example.report.reportService.model.Report;
import com.example.report.reportService.model.ReportDTO;
import com.example.report.reportService.service.ReportsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by nethmih on 06.05.19.
 */

@RestController
@RequestMapping("/reports")
public class ReportsController {


    private final ReportsService reportsService;

    public ReportsController(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    @GetMapping
    public List<Report> getAllReports() {

        return reportsService.getAllReports();
    }

    @PostMapping
    public Report createNewReport(@RequestBody ReportDTO reportDTO) {
        return reportsService.createNewReport(reportDTO);

    }
}
