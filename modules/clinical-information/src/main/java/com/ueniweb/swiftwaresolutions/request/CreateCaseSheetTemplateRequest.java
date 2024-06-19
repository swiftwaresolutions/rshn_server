package com.ueniweb.swiftwaresolutions.request;

import lombok.Data;

@Data
public class CreateCaseSheetTemplateRequest {

    private Long caseSheetType;

    private Long templateFieldId;

    private String templateName;

    private String templateDetails;

    @Override
    public String toString() {
        return "CreateCaseSheetTemplateRequest{" +
                "caseSheetType=" + caseSheetType +
                ", templateFieldId=" + templateFieldId +
                ", templateName='" + templateName + '\'' +
                ", templateDetails='" + templateDetails + '\'' +
                '}';
    }
}
