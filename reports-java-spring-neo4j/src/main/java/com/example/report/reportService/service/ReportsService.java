package com.example.report.reportService.service;

import com.example.report.reportService.model.Report;
import com.example.report.reportService.model.ReportDTO;

import java.util.List;

/**
 * Created by nethmih on 06.05.19.
 */
public interface ReportsService {
    List<Report> getAllReports();

    Report createNewReport(ReportDTO reportDTO);
}
