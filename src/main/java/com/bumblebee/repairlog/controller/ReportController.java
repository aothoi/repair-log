package com.bumblebee.repairlog.controller;

import com.bumblebee.repairlog.domain.entity.Report;
import com.bumblebee.repairlog.repository.EngineerRepository;
import com.bumblebee.repairlog.repository.PartRepository;
import com.bumblebee.repairlog.repository.ToolingRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/create")
    public String getCreateReportForm(Model model) {
        getReportModelAttributes(model, new Report());

        return "create-report";
    }

    @PostMapping("/create")
    public String createReport(@Valid @ModelAttribute Report report,
                               BindingResult bindingResult,
                               Model model) {

        System.out.println("============");
        System.out.println(report.toString());
        System.out.println("============");
//
//        if (bindingResult.hasErrors()) {
//            getReportModelAttributes(model, report);
//
//            return "create-report";
//        }

        getReportModelAttributes(model, new Report());

        return "create-report";
    }

    @GetMapping("/list")
    public String getReportList(Model model) {
        return "report-list";
    }

    @GetMapping("/{formId}/details")
    public String getFormDetails(@PathVariable String formId, Model model) {
        return "create-report";
    }

    private void getReportModelAttributes(Model model, Report report) {
        model.addAllAttributes(Map.of(
                "report", report,
                "engineerList", engineerRepository.findAll(),
                "partList", partRepository.findAll(),
                "toolingList", toolingRepository.findAll()
        ));
    }
}
