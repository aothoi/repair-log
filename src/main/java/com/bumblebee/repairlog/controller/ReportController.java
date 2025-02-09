package com.bumblebee.repairlog.controller;

import com.bumblebee.repairlog.domain.dto.ReportDto;
import com.bumblebee.repairlog.repository.EngineerRepository;
import com.bumblebee.repairlog.repository.PartRepository;
import com.bumblebee.repairlog.repository.ToolingRepository;
import com.bumblebee.repairlog.service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.bumblebee.repairlog.util.Paths.CREATE_REPORT_PAGE;
import static com.bumblebee.repairlog.util.Paths.REPORT_LIST_PAGE;

/**
 * @author aothoi
 * @since 7/2/2024
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {

    private final EngineerRepository engineerRepository;

    private final PartRepository partRepository;

    private final ToolingRepository toolingRepository;

    private final ReportService reportService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/create")
    public String getCreateReportForm(Model model) {
        getReportModelAttributes(model, new ReportDto());

        return CREATE_REPORT_PAGE;
    }

    @PostMapping("/create")
    public String createReport(@Valid @ModelAttribute ReportDto report,
                               BindingResult bindingResult,
                               Model model) {

        reportService.save(report);
//        if (bindingResult.hasErrors()) {
//            getReportModelAttributes(model, report);
//
//            return "create-report";
//        }

        getReportModelAttributes(model, new ReportDto());

        return CREATE_REPORT_PAGE;
    }

    @GetMapping("/list")
    public String getReportList(Model model) {
        return REPORT_LIST_PAGE;
    }

    @GetMapping("/{formId}/details")
    public String getFormDetails(@PathVariable String formId, Model model) {
        return "create-report";
    }

    private void getReportModelAttributes(Model model, ReportDto report) {
        model.addAllAttributes(Map.of(
                "report", report,
                "engineerList", engineerRepository.findAll(),
                "partList", partRepository.findAll(),
                "toolingList", toolingRepository.findAll()
        ));
    }
}
