package com.bumblebee.repairlog.controller;

import com.bumblebee.repairlog.domain.dto.ReportDto;
import com.bumblebee.repairlog.domain.dto.ToolingCommentDto;
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

import java.util.List;
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
        getReportModelAttributes(model, new ReportDto(), false);

        return CREATE_REPORT_PAGE;
    }

    @PostMapping("/create")
    public String createReport(@Valid @ModelAttribute ReportDto reportDto,
                               BindingResult bindingResult,
                               Model model) {

        if (bindingResult.hasErrors()) {
            getReportModelAttributes(model, reportDto, true);

            return "create-report";
        }

        reportService.save(reportDto);

        getReportModelAttributes(model, new ReportDto(), false);

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

    private void getReportModelAttributes(Model model, ReportDto reportDto, boolean useToolingComments) {
        List<ToolingCommentDto> toolingList = useToolingComments ?
                reportDto.getToolingComments() :
                toolingRepository.findAll().stream()
                        .map(ToolingCommentDto::initialize)
                        .toList();

        model.addAllAttributes(Map.of(
                "reportDto", reportDto,
                "engineerList", engineerRepository.findAll(),
                "partList", partRepository.findAll(),
                "toolingList", toolingList
        ));
    }
}
