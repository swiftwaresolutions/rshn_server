package com.ueniweb.swiftwaresolutions.services;

import com.ueniweb.swiftwaresolutions.core.response.Response;
import com.ueniweb.swiftwaresolutions.data.AnthropometryData;
import com.ueniweb.swiftwaresolutions.data.PediatricCaseSheetData;
import com.ueniweb.swiftwaresolutions.data.PrescTemplateDetailsData;
import com.ueniweb.swiftwaresolutions.data.PrescriptionData;
import com.ueniweb.swiftwaresolutions.domain.Anthropometry;
import com.ueniweb.swiftwaresolutions.domain.PediatricCaseSheet;
import com.ueniweb.swiftwaresolutions.request.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface ClinicalInfoWritePlatformService {

    Response saveGeneralCaseSheet(final GeneralCaseSheetRequest generalCaseSheetRequest);

    Response updateGeneralCaseSheet(final Long id, final GeneralCaseSheetRequest generalCaseSheetRequest,final Integer caseSheetType);

    List<PrescriptionData>  savePrescription(final CreatePrescriptionRequest createPrescriptionRequest);

    void saveInvestigationOrder(final List<CreateInvestigationOrderRequest> createInvestigationOrderRequest);

    List<PrescriptionData> updatePrescription(final Long id, final UpdatePrescriptionRequest createPrescriptionRequest);

    void saveLabOrder(final List<CreateLabOrderRequest> labOrderRequests);

    Response deleteLabOrderByLabId(final Long labId);

    Response deleteLabOrderByPatientId(final Long patientId);

    Response deleteInvestigationOrderById(final Long orderId);

    Response deleteInvestigationOrderByPatientId(final Long patientId);

    Response saveCaseSheetTemplate(final CreateCaseSheetTemplateRequest createCaseSheetTemplateRequest);

    Response saveOrderDiscount(final CreateOrderDiscountRequest createOrderDiscountRequest);

    Response savePrescriptionTemplate(final CreatePrescTemplateRequest createPrescTemplateRequest);

    Response deleteOrderDiscByPatId(final Long patId);

    void saveProcOrderTemplate(final CreateOrderTemplateRequest createOrderTemplateRequest);



  //  Response savePediatricCaseSheet(final CreatePeadiatricCaseSheetRequest createPeadiatricCaseSheetRequest);

    Response saveDischargeSummary(final CreateDischargeSummaryRequest createDischargeSummaryRequest);

    Response savePediatricCaseSheet(final CreatePeadiatricCaseSheetRequest createPeadiatricCaseSheetRequest);

    Response updatePediatricCaseSheet(final Long id, final CreatePeadiatricCaseSheetRequest createPeadiatricCaseSheetRequest, final Integer caseSheetType);

    Response saveNeonateCaseSheet(final CreateNeoNateCaseSheetRequest createNeoNateCaseSheetRequest);

    Response updateNeonateCaseSheet(final Long id, final CreateNeoNateCaseSheetRequest createNeoNateCaseSheetRequest,final Integer caseSheetType);

    void saveAnthropometry(final List<CreateAnthropometryRequest> anthropometryRequests);

    void updateAnthropometry(final Long vstId, final List<CreateAnthropometryRequest> createAnthropometryRequest);

    Response saveOpVitals(final CreateOPVitalsRequest createOPVitalsRequest);

    Response updateOPVitals(final Long id, final CreateOPVitalsRequest createOPVitalsRequest);

    Response saveSummaryTemplates(final CreateSummaryTemplateRequest createSummaryTemplateRequest);

    Response saveDentalCaseSheet(final CreateDentalCasesheetRequest createDentalCasesheetRequest);

    Response updateDentalCaseSheet(final Long id, final CreateDentalCasesheetRequest createdentalCaseSheetRequest);

    Response saveAntenatalCaseSheet(final CreateAntenatalCaseSheetRequest createAntenatalCaseSheetRequest);

    Response updateAntenatalCaseSheet(final Long id, final CreateAntenatalCaseSheetRequest createAntenatalCaseSheetRequest);

    Response updateDischargeSummary(final Long id, final CreateDischargeSummaryRequest createDischargeSummaryRequest);

    Response saveSurgeryCaseSheet(final CreateSurgeryCaseSheetRequest createSurgeryCaseSheetRequest);

    Response  updatSurgeryCaseSheet(final Long id, final CreateSurgeryCaseSheetRequest createSurgeryCaseSheetRequest);
}
