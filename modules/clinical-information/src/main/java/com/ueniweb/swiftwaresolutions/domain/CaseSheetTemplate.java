package com.ueniweb.swiftwaresolutions.domain;

import com.ueniweb.swiftwaresolutions.request.CreateCaseSheetTemplateRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name="CaseSheetTemplate")
@Table(name = "cli_patient_casesheet_templates")
@Getter
@Setter
public class CaseSheetTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "caseSheetType")
    private Long caseSheetType;

    @Column(name = "templateFieldId")
    private Long templateFieldId;

    @Column(name = "templateName")
    private String templateName;

    @Column(name = "templateDetails")
    private String templateDetails;

    @Column(name = "isValid")
    private int isValid;

    public static CaseSheetTemplate to(CreateCaseSheetTemplateRequest createCaseSheetTemplateRequest) {
        CaseSheetTemplate caseSheetTemplate = new CaseSheetTemplate();
        caseSheetTemplate.setCaseSheetType(createCaseSheetTemplateRequest.getCaseSheetType());
        caseSheetTemplate.setTemplateDetails(createCaseSheetTemplateRequest.getTemplateDetails());
        caseSheetTemplate.setTemplateFieldId(createCaseSheetTemplateRequest.getTemplateFieldId());
        caseSheetTemplate.setTemplateName(createCaseSheetTemplateRequest.getTemplateName());
        caseSheetTemplate.setIsValid(1);
        return caseSheetTemplate;
    }
}
