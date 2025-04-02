package com.bumblebee.repairlog.controller;

import com.bumblebee.repairlog.domain.dto.TestReportDto;
import com.bumblebee.repairlog.repository.PartRepository;
import com.bumblebee.repairlog.repository.TestReportRepository;
import com.bumblebee.repairlog.service.TestReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.bumblebee.repairlog.util.Paths.CREATE_TEST_REPORT_PAGE;
import static com.bumblebee.repairlog.util.Paths.TEST_REPORT_LIST_PAGE;

/**
 * @author aothoi
 * @since 02-Apr-25
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/test-report")
public class TestReportController {

    private final TestReportRepository testReportRepository;

    private final TestReportService testReportService;

    private final PartRepository partRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/create")
    public String getCreateTestReportForm(Model model) {
        getReportModelAttributes(model, new TestReportDto());

        return CREATE_TEST_REPORT_PAGE;
    }

    @PostMapping("/create")
    public String createTestReport(@Valid @ModelAttribute TestReportDto testReportDto,
                                   BindingResult bindingResult,
                                   Model model) {

        if (bindingResult.hasErrors()) {
            getReportModelAttributes(model, testReportDto);

            return CREATE_TEST_REPORT_PAGE;
        }

        testReportService.save(testReportDto);

        getReportModelAttributes(model, new TestReportDto());
        model.addAttribute("success", true);

        return CREATE_TEST_REPORT_PAGE;
    }

    @GetMapping("/list")
    public String getReportList(Model model) {
        model.addAttribute("testReports", testReportRepository.findAll());

        return TEST_REPORT_LIST_PAGE;
    }

    private void getReportModelAttributes(Model model, TestReportDto testReportDto) {
        model.addAllAttributes(Map.of(
                "testReportDto", testReportDto,
                "partList", partRepository.findAll()
        ));
    }
}
