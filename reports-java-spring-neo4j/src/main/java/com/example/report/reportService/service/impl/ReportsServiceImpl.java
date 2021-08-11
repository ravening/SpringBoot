package com.example.report.reportService.service.impl;

import com.example.report.reportService.model.Report;
import com.example.report.reportService.model.ReportDTO;
import com.example.report.reportService.repository.EntityRepository;
import com.example.report.reportService.repository.ReportRepository;
import com.example.report.reportService.repository.UserRepository;
import com.example.report.reportService.service.ReportsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nethmih on 06.05.19.
 */

@Service
@Transactional
public class ReportsServiceImpl implements ReportsService {


    private final UserRepository userRepository;
    private final ReportRepository reportRepository;
    private final EntityRepository entityRepository;

    public ReportsServiceImpl(UserRepository userRepository, ReportRepository reportRepository,
                              EntityRepository entityRepository) {
        this.userRepository = userRepository;
        this.reportRepository = reportRepository;
        this.entityRepository = entityRepository;
    }

    @Override
    public List<Report> getAllReports(){
        List<Report> reports = new ArrayList<>();
        Iterable<Report> allReports = reportRepository.findAll();
        allReports.forEach(reports::add);
        return reports;
    }

    @Override
    public Report createNewReport(ReportDTO reportDTO) {

        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");


        Report report = new Report();
        report.setTitle(reportDTO.getTitle());
        report.setDescription(reportDTO.getDescription());
        report.setDate(formatter.format(new Date()));

        Report newReport = reportRepository.save(report);

        userRepository.createReportRelationship(reportDTO.getUsername(), newReport.getId());
        reportRepository.createBelongRelationship(newReport.getId(), reportDTO.getEntityName());

        return newReport;
    }
}
