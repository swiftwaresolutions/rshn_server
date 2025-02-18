package com.ueniweb.swiftwaresolutions.services;

import com.ueniweb.swiftwaresolutions.core.exception.HimsApplicationContextException;
import com.ueniweb.swiftwaresolutions.core.response.Response;
import com.ueniweb.swiftwaresolutions.data.PrescriptionData;
import com.ueniweb.swiftwaresolutions.data.validator.CaseSheetValidator;
import com.ueniweb.swiftwaresolutions.data.validator.PrescriptionValidator;
import com.ueniweb.swiftwaresolutions.domain.*;
import com.ueniweb.swiftwaresolutions.infrastructure.exceptions.NoRecordFoundException;
import com.ueniweb.swiftwaresolutions.infrastructure.exceptions.NotFoundException;
import com.ueniweb.swiftwaresolutions.repository.*;
import com.ueniweb.swiftwaresolutions.request.*;
import com.ueniweb.swiftwaresolutions.rowmapper.SurgeryChecklistRowMapper;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClinicalInfoWritePlatformServiceImpl implements ClinicalInfoWritePlatformService {

    private final GeneralCaseSheetRepositoryWrapper generalCaseSheetRepositoryWrapper;

    private final GeneralCaseSheetRepository generalCaseSheetRepository;

    private final PrescriptionRepository prescriptionRepository;

    private final PrescriptionRepositoryWrapper prescriptionRepositoryWrapper;

    private final PrescriptionDetailsRepository prescriptionDetailsRepository;

    private final InvestigationOrderRepository investigationOrderRepository;

    private final LabOrderRepository labOrderRepository;

    private final LabAndInvSummaryRepository labAndInvSummaryRepository;

    private final NeonateCaseSheetRepository neonateCaseSheetRepository;

    private final NeonateCaseSheetRepositoryWrapper neonateCaseSheetRepositoryWrapper;

    private final LabOrderRepositoryWrapper labOrderRepositoryWrapper;

    private final LabDeleteDetailsWritePlatformService labDeleteDetailsWritePlatformService;

    private final ClinicalInfoReadPlatformService clinicalInfoReadPlatformService;

    private final InvestigationOrderRepositoryWrapper investigationOrderRepositoryWrapper;

    private final PediatricCaseSheetWrapper pediatricCaseSheetWrapper;

    private final CaseSheetTemplateRepository caseSheetTemplateRepository;

    private final OrderDiscountRepository orderDiscountRepository;

    private final DisSumConsultantRepository disSumConsultantRepository;

    private final DisSumDeptRepository disSumDeptRepository;

    private final ComplaintDetailsRepository complaintDetailsRepository;

    private final DiagnosisDetailsRepository diagnosisDetailsRepository;

    private final DiagnosisDetailsSummaryRepository diagnosisDetailsSummaryRepository;

    private final OrderDiscountRepositoryWrapper orderDiscountRepositoryWrapper;

    private final PrescTemplateRepository prescTemplateRepository;

    private final OrderTemplateRepository orderTemplateRepository;

    private final AnthropometryRepository anthropometryRepository;

    private final DischargeSummaryRepository dischargeSummaryRepository;

    private final DisSummaryRepositoryWrapper disSummaryRepositoryWrapper;

    private final CaseSheetValidator caseSheetValidator;

    private final PrescriptionValidator prescriptionValidator;

    private final EntityManager entityManager;

    private final PediatricCaseSheetRepository pediatricCaseSheetRepository;

    private final OPVitalsRepository opVitalsRepository;

    private final OPVitalsRepositoryWrapper opVitalsRepositoryWrapper;

    private final SummaryTemplateRepository summaryTemplateRepository;

    private final AntenatalCaseSheetRepository antenatalCaseSheetRepository;

    private final DentalCaseSheetRepository dentalCaseSheetRepository;

    private final DentalCaseSheetRepositoryWrapper dentalCaseSheetRepositoryWrapper;

    private final DentalComplaintDetailsRepository dentalcomplaintDetailsRepository;

    private final DentalTeethExamDetailsRepository dentalTeethExamDetailsRepository;

    private final DentalTeethTreatDetailsRepository dentalTeethTreatDetailsRepository;

    private final AntenatalCaseSheetRepositotyWrapper antenatalCaseSheetRepositotyWrapper;

    private final AntenatalCaseSheetPreRepository antenatalCaseSheetPreRepository;

    private final SurgeryCaseSheetRepository surgeryCaseSheetRepository;

    private final SurgeryCaseSheetRepositoryWrapper surgeryCaseSheetRepositoryWrapper;

    private final SurgeryNurseRepository surgeryNurseRepository;

    private final SurgerySurgonRepository surgerySurgonRepository;

    private final SurgeryNAmeRepository surgeryNAmeRepository;

    private final NursingIoRepository nursingIoRepository;

    private final NursingIoRepositoryWrapper nursingIoRepositoryWrapper;

    private final SurgeryChecklistRepository surgeryChecklistRepository;

    private final SurgeryChecklistRepositoryWrapper surgeryChecklistRepositoryWrapper;

    private final IpProcedureCaseSheetRepository ipProcedureCaseSheetRepository;

    private final IpProcedureCaseSheetRepositoryWrapper ipProcedureCaseSheetRepositoryWrapper;

    private final AldreteScoreChartRepository aldreteScoreChartRepository;

    private final AldreteScoreChartRepositoryWrapper aldreteScoreChartRepositoryWrapper;

    private final DermatologyCaseSheetRepository dermatologyCaseSheetRepository;

    private final DermatologyCaseSheetRepositoryWrapper dermatologyCaseSheetRepositoryWrapper;

    private final DermatologyComplainDetailsRepository dermatologyComplainDetailsRepository;

    private final DermatologyDiagnosisDetailsRepository dermatologyDiagnosisDetailsRepository;

    private final OpthamologyCaseSheetRepository opthamologyCaseSheetRepository;

    private final OpthamologyCaseSheetRepositoryWrapper opthamologyCaseSheetRepositoryWrapper;

    private final OpthamologyComplainDetailsRepository opthamologyComplainDetailsRepository;

    private final OpthamologyDiagnosisDetailsRepository opthamologyDiagnosisDetailsRepository;

    private final ENTCaseSheetRepository entCaseSheetRepository;

    private final ENTCaseSheetRepositoryWrapper entCaseSheetRepositoryWrapper;

    private final ENTComplainDetailsRepository entComplainDetailsRepository;

    private final ENTDiagnosisDetailsRepository entDiagnosisDetailsRepository;


    @Transactional
    @Override
    public Response saveGeneralCaseSheet(final GeneralCaseSheetRequest generalCaseSheetRequest) {
        try {
            log.debug("START saveGeneralCaseSheet request {}", generalCaseSheetRequest);
            this.caseSheetValidator.validateComplaintData(generalCaseSheetRequest.getCreateComplaintDetailsRequestList());
            this.caseSheetValidator.GeneralCaseSheetDate(generalCaseSheetRequest);
            if (generalCaseSheetRequest.getVisitId()==0 || generalCaseSheetRequest.getPatientId() ==0) {
                throw new NullPointerException("Choose Proper Patient!");
            }

            final GeneralCaseSheet newGeneralCaseSheet = GeneralCaseSheet.to(generalCaseSheetRequest);
            newGeneralCaseSheet.setComplaintDetailsList(ComplaintDetails.to(newGeneralCaseSheet,generalCaseSheetRequest.getCreateComplaintDetailsRequestList()));
            newGeneralCaseSheet.setDiagnosisDetailsList(DiagnosisDetails.to(newGeneralCaseSheet, generalCaseSheetRequest.getCreateDiagnosisDetailsRequestList()));
            generalCaseSheetRepository.saveAndFlush(newGeneralCaseSheet);
            log.debug("END saveGeneralCaseSheet id ");
            return new Response(newGeneralCaseSheet.getId());
        } catch (Exception e) {
            log.error("Caught with exception while saving GeneralCaseSheet {}", e.getMessage());
            throw new RuntimeException(e);

        }

    }

    @Transactional
    @Override
    public List<PrescriptionData> savePrescription(final CreatePrescriptionRequest createPrescriptionRequest) {
        try {
            log.debug("START savePrescription request {}", createPrescriptionRequest);
            this.prescriptionValidator.validatePrescriptionData(createPrescriptionRequest.getCreatePrescriptionDetailsRequestList());
            final Prescription prescription = Prescription.to(createPrescriptionRequest);
            this.entityManager.persist(prescription);  // for inserting display
            prescription.setDisplay(prescription.getId().toString());// for inserting display
            prescription.setPhDescriptionDetailsList(PrescriptionDetails.to(prescription,createPrescriptionRequest.getCreatePrescriptionDetailsRequestList()));
            final Prescription newPrescription = prescriptionRepository.saveAndFlush(prescription);
            log.debug("END savePrescription id ");
            System.out.print("vada"+newPrescription.getId());
            return this.clinicalInfoReadPlatformService.fetchPrescriptionDetailsById(newPrescription.getId());
        } catch (Exception e) {
            log.error("Caught with exception while saving savePrescription {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    @Override
    public void saveInvestigationOrder(final List<CreateInvestigationOrderRequest> createInvestigationOrderRequest) {
        try {
            log.debug("START SAVE InvestigationOrder request {}", createInvestigationOrderRequest);

            final List<InvestigationOrder> investigationOrderList = InvestigationOrder.to(createInvestigationOrderRequest);
            this.investigationOrderRepository.saveAllAndFlush(investigationOrderList);
            log.debug("END SAVE InvestigationOrder id ");
        } catch (Exception e) {
            log.error("Caught with exception while saving Investigation Order {}", e.getMessage());
        }
    }

    @Transactional
    @Override
    public Response updateGeneralCaseSheet(Long id, GeneralCaseSheetRequest generalCaseSheetRequest,Integer caseSheetType) {
        try {
            log.debug("START of updateGeneralCaseSheet() id {} request {} caseSheetType{}", id, generalCaseSheetRequest,caseSheetType);
            final GeneralCaseSheet generalCaseSheet = this.generalCaseSheetRepositoryWrapper.findOneWithNotFoundDetection(id);
            generalCaseSheet.update(generalCaseSheetRequest);
            this.generalCaseSheetRepository.saveAndFlush(generalCaseSheet);

            final List<ComplaintDetails> complaintDetailsList = this.complaintDetailsRepository.fetchComplaintDetailsBycaseSheetId(generalCaseSheet.getId(),caseSheetType);
            for (ComplaintDetails complaintDetails : complaintDetailsList) {
                complaintDetails.setIsValid(0L);
                complaintDetailsRepository.save(complaintDetails);
            }

            final List<DiagnosisDetails> diagnosisDetailsList = this.diagnosisDetailsRepository.fetchDiagnosisDetailsBycaseSheetId(generalCaseSheet.getId());
            for (DiagnosisDetails diagnosisDetails : diagnosisDetailsList) {
                diagnosisDetails.setIsValid(0L);
                diagnosisDetailsRepository.save(diagnosisDetails);
            }

            generalCaseSheet.setComplaintDetailsList(ComplaintDetails.to(generalCaseSheet,generalCaseSheetRequest.getCreateComplaintDetailsRequestList()));
            generalCaseSheet.setDiagnosisDetailsList(DiagnosisDetails.to(generalCaseSheet, generalCaseSheetRequest.getCreateDiagnosisDetailsRequestList()));
            generalCaseSheetRepository.saveAndFlush(generalCaseSheet);

            log.debug("END of updateGeneralCaseSheet() id {} request {}", id, generalCaseSheetRequest);
            return new Response(generalCaseSheet.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public List<PrescriptionData> updatePrescription(Long id, UpdatePrescriptionRequest updatePrescriptionRequest) {
        try {
            log.debug("START updatePrescription id {} request {}", id, updatePrescriptionRequest);
            this.prescriptionValidator.validatePrescriptionData(updatePrescriptionRequest.getCreatePrescriptionDetailsRequestList());
            final Prescription prescription = this.prescriptionRepositoryWrapper.findOneWithNotFoundDetection(id);
            final List<PrescriptionDetails> prescriptionDetailsList = this.prescriptionDetailsRepository.fetchPrescriptionDetailsByPrescriptionId(prescription.getId());
            for (PrescriptionDetails prescriptionDetails : prescriptionDetailsList) {
                prescriptionDetails.setIsCancelled(1L);
            }
            this.prescriptionDetailsRepository.saveAllAndFlush(prescriptionDetailsList);
            prescription.setPhDescriptionDetailsList(PrescriptionDetails.to(prescription, updatePrescriptionRequest.getCreatePrescriptionDetailsRequestList()));
          //  prescription.update(updatePrescriptionRequest);
            this.prescriptionRepository.saveAndFlush(prescription);
            log.debug("END updatePrescription id {} request {}", id, updatePrescriptionRequest);
            return this.clinicalInfoReadPlatformService.fetchPrescriptionDetailsById(prescription.getId());
        } catch (Exception e) {
            log.error("Caught with exception while saving updatePrescription {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public void saveLabOrder(List<CreateLabOrderRequest> createLabOrderRequests) {

        try {
            log.debug("START SAVE LabOrder request {}", createLabOrderRequests);

            final List<LabOrder> labOrderList = LabOrder.to(createLabOrderRequests);
            this.labOrderRepository.saveAllAndFlush(labOrderList);
            log.debug("END SAVE LabOrder id ");
        } catch (Exception e) {
            log.error("Caught with exception while saving Lab Order {}", e.getMessage());
        }
    }

    @Override
    @Transactional
    public Response deleteLabOrderByLabId(Long labId) {
        try {
            log.debug("START deleteLabOrderByLabId labId {}", labId);
            final LabOrder labOrder = this.labOrderRepositoryWrapper.findOneWithNotFoundDetection(labId);
            CreateLabDeleteDetailsRequest createLabDeleteDetailsRequest = new CreateLabDeleteDetailsRequest();
            createLabDeleteDetailsRequest.setPatId(labOrder.getPatId());
            createLabDeleteDetailsRequest.setTestId(labOrder.getTestId());
            createLabDeleteDetailsRequest.setOrderDate(Date.valueOf(LocalDate.now()));
            createLabDeleteDetailsRequest.setOrderUid(labOrder.getUid());
            createLabDeleteDetailsRequest.setUid(labOrder.getUid());
            createLabDeleteDetailsRequest.setUnits(labOrder.getUnit());
            createLabDeleteDetailsRequest.setDate(Date.valueOf(LocalDate.now()));
            createLabDeleteDetailsRequest.setTime(Time.valueOf(LocalTime.now()));
            this.labDeleteDetailsWritePlatformService.saveLabDeleteDetails(createLabDeleteDetailsRequest);
            this.labOrderRepository.deleteById(labOrder.getId());
            log.debug("END deleteLabOrderByLabId ");
            return new Response(labId);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Response deleteLabOrderByPatientId(Long patientId) {
        try {
            log.debug("START deleteLabOrderByPatientId patientId {}", patientId);
            final List<LabOrder> labOrderList = this.labOrderRepositoryWrapper.findOneWithNotFoundDetectionByPatientId(patientId);
            if (labOrderList == null) {
                throw new NotFoundException("This patient doesn't have any lab order");
            }
            for (LabOrder labOrder : labOrderList) {
                CreateLabDeleteDetailsRequest createLabDeleteDetailsRequest = new CreateLabDeleteDetailsRequest();
                createLabDeleteDetailsRequest.setPatId(labOrder.getPatId());
                createLabDeleteDetailsRequest.setTestId(labOrder.getTestId());
                createLabDeleteDetailsRequest.setOrderDate(Date.valueOf(LocalDate.now()));
                createLabDeleteDetailsRequest.setOrderUid(labOrder.getUid());
                createLabDeleteDetailsRequest.setUid(labOrder.getUid());
                createLabDeleteDetailsRequest.setUnits(labOrder.getUnit());
                createLabDeleteDetailsRequest.setDate(Date.valueOf(LocalDate.now()));
                createLabDeleteDetailsRequest.setTime(Time.valueOf(LocalTime.now()));
                this.labDeleteDetailsWritePlatformService.saveLabDeleteDetails(createLabDeleteDetailsRequest);
                log.debug("END deleteLabOrderByPatientId ");
                this.labOrderRepository.delete(labOrder);
            }
            return new Response(patientId);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Response deleteInvestigationOrderById(Long orderId) {
        try {
            log.debug("START deleteInvestigationOrderById orderId {}", orderId);
            final InvestigationOrder investigationOrder = this.investigationOrderRepositoryWrapper.findOneWithNotFoundDetection(orderId);
            this.investigationOrderRepository.deleteById(investigationOrder.getId());
            log.debug("END deleteLabOrderByLabId ");
            return new Response(orderId);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Response deleteInvestigationOrderByPatientId(Long patientId) {
        try {
            log.debug("START deleteInvestigationOrderByPatientId patientId {}", patientId);
            final List<InvestigationOrder> investigationOrders = this.investigationOrderRepositoryWrapper.findOneWithNotFoundDetectionByPatientId(patientId);
            if (investigationOrders == null) {
                throw new NotFoundException("This patientId doesn't have any investigation order");
            }
            for (InvestigationOrder investigationOrder: investigationOrders) {
                this.investigationOrderRepository.deleteById(investigationOrder.getId());
            }
            log.debug("END deleteLabOrderByLabId ");
            return new Response(patientId);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Response saveCaseSheetTemplate(CreateCaseSheetTemplateRequest createCaseSheetTemplateRequest) {
        try {
            log.debug("START saveCaseSheetTemplate createCaseSheetTemplateRequest {}", createCaseSheetTemplateRequest);
            final CaseSheetTemplate caseSheetTemplate = CaseSheetTemplate.to(createCaseSheetTemplateRequest);
            this.caseSheetTemplateRepository.saveAndFlush(caseSheetTemplate);
            log.debug("END saveCaseSheetTemplate id ");
            return new Response(caseSheetTemplate.getId());
        } catch (Exception e) {
            log.error("Caught with exception while saving createCaseSheetTemplateRequest {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public Response saveOrderDiscount(CreateOrderDiscountRequest createOrderDiscountRequest) {
        try {
            log.debug("START saveOrderDiscount CreateOrderDiscountRequest {}", createOrderDiscountRequest);
            final OrderDiscount orderDiscount = OrderDiscount.to(createOrderDiscountRequest);
            this.orderDiscountRepository.saveAndFlush(orderDiscount);
            log.debug("END saveOrderDiscount id ");
            return new Response(orderDiscount.getId());
        } catch (Exception e) {
            log.error("Caught with exception while savingOrderDiscount {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public Response savePrescriptionTemplate(final CreatePrescTemplateRequest createPrescTemplateRequest) {
        try {
            log.debug("START savePrescriptionTemplate request {}", createPrescTemplateRequest);
            final PrescTemplate prescTemplate = PrescTemplate.to(createPrescTemplateRequest);
            prescTemplate.setPrescTemplateDetailsList(PrescTemplateDetails.to(prescTemplate,createPrescTemplateRequest.getCreatePrescTemplateRequestList()));
            this.prescTemplateRepository.saveAndFlush(prescTemplate);
            log.debug("END savePrescription id ");
            return new Response(prescTemplate.getId());
        } catch (Exception e) {
            log.error("Caught with exception while saving PrescriptionTemplate {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public Response deleteOrderDiscByPatId(Long patId) {
        try {
            log.debug("START deleteOrderDiscByPatId patId {}", patId);
            OrderDiscount orderDiscount = this.orderDiscountRepositoryWrapper.findOneWithNotFoundDetectionByPatId(patId);
            if (orderDiscount == null) {
                throw new NoRecordFoundException("Order discount not found for this patient Id:"+patId);
            }
            this.orderDiscountRepository.deleteById(orderDiscount.getId());
            log.debug("END deleteOrderDiscByPatId ");
            return new Response(patId);
        } catch (Exception e) {
            throw new HimsApplicationContextException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void saveProcOrderTemplate(final CreateOrderTemplateRequest createOrderTemplateRequest) {
        try {
            log.debug("START saveProcOrderTemplate request {}", createOrderTemplateRequest);

            final OrderTemplate orderTemplate = OrderTemplate.to(createOrderTemplateRequest);
            orderTemplate.setOrderTemplateDetailsList(OrderTemplateDetails.to(orderTemplate, createOrderTemplateRequest.getCreateOrderTemplateDetailsRequestsList()));
            orderTemplateRepository.saveAndFlush(orderTemplate);
            log.debug("END saveProcOrderTemplate id ");
        } catch (Exception e) {
            log.error("Caught with exception while saving ProcOrderTemplate {}", e.getMessage());
            throw e;
        }
    }

    //@Transactional
    //@Override


    @Transactional
    @Override
    public Response savePediatricCaseSheet(final CreatePeadiatricCaseSheetRequest createPeadiatricCaseSheetRequest) {
        try {
            log.debug("START savePediatricCaseSheet request {}", createPeadiatricCaseSheetRequest);

            final PediatricCaseSheet newpediatricCaseSheet = PediatricCaseSheet.to(createPeadiatricCaseSheetRequest);
            newpediatricCaseSheet.setPediatricComplaintDetailsList(PediatricComplaintDetails.to(newpediatricCaseSheet,createPeadiatricCaseSheetRequest.getCreateComplaintDetailsRequestList()));
            pediatricCaseSheetRepository.saveAndFlush(newpediatricCaseSheet);

            log.debug("END savePediatricCaseSheet id ");
            return new Response(newpediatricCaseSheet.getId());
        } catch (Exception e) {
            log.error("Caught with exception while saving PediatricCaseSheet {}", e.getMessage());
            throw new RuntimeException(e);

        }

    }

    @Transactional
    @Override
    public Response updatePediatricCaseSheet(Long id, CreatePeadiatricCaseSheetRequest createPeadiatricCaseSheetRequest,Integer caseSheetType) {
        try {
            log.debug("START of updatePediatricCaseSheet() id {} request {}", id, createPeadiatricCaseSheetRequest);
            final PediatricCaseSheet pediatricCaseSheet = this.pediatricCaseSheetWrapper.findOneWithNotFoundDetection(id);
            pediatricCaseSheet.update(createPeadiatricCaseSheetRequest);
            this.pediatricCaseSheetRepository.saveAndFlush(pediatricCaseSheet);

            final List<ComplaintDetails> complaintDetailsList = this.complaintDetailsRepository.fetchComplaintDetailsBycaseSheetId(pediatricCaseSheet.getId(),caseSheetType);
            for (ComplaintDetails complaintDetails : complaintDetailsList) {
                complaintDetails.setIsValid(0L);
                complaintDetailsRepository.save(complaintDetails);
            }
            
            pediatricCaseSheet.setPediatricComplaintDetailsList(PediatricComplaintDetails.to(pediatricCaseSheet,createPeadiatricCaseSheetRequest.getCreateComplaintDetailsRequestList()));
            pediatricCaseSheetRepository.saveAndFlush(pediatricCaseSheet);

            log.debug("END of updatePediatricCaseSheet() id {} request {}", id, createPeadiatricCaseSheetRequest);
            return new Response(pediatricCaseSheet.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public Response saveNeonateCaseSheet(final CreateNeoNateCaseSheetRequest createNeoNateCaseSheetRequest) {
        try {
            log.debug("START saveNeonateCaseSheet request {}", createNeoNateCaseSheetRequest);

            final NeonateCaseSheet newNeonateCaseSheet = NeonateCaseSheet.to(createNeoNateCaseSheetRequest);
            newNeonateCaseSheet.setNeoNateComplaintDetailsList(NeoNateComplaintDetails.to(newNeonateCaseSheet,createNeoNateCaseSheetRequest.getCreateComplaintDetailsRequestList()));
            neonateCaseSheetRepository.saveAndFlush(newNeonateCaseSheet);

            log.debug("END saveNeonateCaseSheet id ");
            return new Response(newNeonateCaseSheet.getId());
        } catch (Exception e) {
            log.error("Caught with exception while saving PediatricCaseSheet {}", e.getMessage());
            throw new RuntimeException(e);

        }

    }

    @Transactional
    @Override
    public Response updateNeonateCaseSheet(Long id, CreateNeoNateCaseSheetRequest createNeoNateCaseSheetRequest,Integer caseSheetType) {
        try {
            log.debug("START of updateNeonateCaseSheet() id {} request {}", id, createNeoNateCaseSheetRequest);
            final NeonateCaseSheet neonateCaseSheet = this.neonateCaseSheetRepositoryWrapper.findOneWithNotFoundDetection(id);
            neonateCaseSheet.update(createNeoNateCaseSheetRequest);
            this.neonateCaseSheetRepository.saveAndFlush(neonateCaseSheet);

            final List<ComplaintDetails> complaintDetailsList = this.complaintDetailsRepository.fetchComplaintDetailsBycaseSheetId(neonateCaseSheet.getId(),caseSheetType);
            for (ComplaintDetails complaintDetails : complaintDetailsList) {
                complaintDetails.setIsValid(0L);
                complaintDetailsRepository.save(complaintDetails);
            }

            neonateCaseSheet.setNeoNateComplaintDetailsList(NeoNateComplaintDetails.to(neonateCaseSheet,createNeoNateCaseSheetRequest.getCreateComplaintDetailsRequestList()));
            neonateCaseSheetRepository.saveAndFlush(neonateCaseSheet);

            log.debug("END of updateNeonateCaseSheet() id {} request {}", id, createNeoNateCaseSheetRequest);
            return new Response(neonateCaseSheet.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void saveAnthropometry(List<CreateAnthropometryRequest> createAnthropometryRequests) {

        try {
            log.debug("START SAVE LabOrder request {}", createAnthropometryRequests);

            final List<Anthropometry> anthropometryList = Anthropometry.to(createAnthropometryRequests);
            this.anthropometryRepository.saveAllAndFlush(anthropometryList);
            log.debug("END SAVE createAnthropometryRequests id ");
        } catch (Exception e) {
            log.error("Caught with exception while saving Lab Order {}", e.getMessage());
        }
    }

    @Override
    public void updateAnthropometry(Long vstId, List<CreateAnthropometryRequest> createAnthropometryRequest) {
        try {
            log.debug("START updateAnthropometry id {} request {}", vstId, createAnthropometryRequest);

            final List<Anthropometry> anthropometryList = this.anthropometryRepository.fetchanthropometryListByvstId(vstId);
            for (Anthropometry anthropometry : anthropometryList) {
                anthropometry.setIsValid(0);
            }
            this.anthropometryRepository.saveAllAndFlush(anthropometryList);

            final List<Anthropometry> anthropometryList1 = Anthropometry.to(createAnthropometryRequest);
            this.anthropometryRepository.saveAllAndFlush(anthropometryList1);
            log.debug("END updatePrescription id {} request {}", id, createAnthropometryRequest);

        } catch (Exception e) {
            log.error("Caught with exception while updateAnthropometry {}", e.getMessage());
            throw e;
        }
    }
    @Override
    public Response saveOpVitals(CreateOPVitalsRequest createOPVitalsRequest) {
        try {
            log.debug("START saveOpVitals CreateOPVitalsRequest {}", createOPVitalsRequest);
            final Opvitals opvitals = Opvitals.to(createOPVitalsRequest);
            this.opVitalsRepository.saveAndFlush(opvitals);
            log.debug("END saveOpVitals id ");
            return new Response(opvitals.getId());
        } catch (Exception e) {
            log.error("Caught with exception while savingOrderDiscount {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public Response updateOPVitals(Long id, CreateOPVitalsRequest createOPVitalsRequest) {
        try {
            log.debug("START of updateOPVitals() id {} request {}", id, createOPVitalsRequest);
            final Opvitals opvitals = this.opVitalsRepositoryWrapper.findOneWithNotFoundDetection(id);
            opvitals.update(createOPVitalsRequest);
            this.opVitalsRepository.saveAndFlush(opvitals);

            log.debug("END of updateOPVitals() id {} request {}", id, createOPVitalsRequest);
            return new Response(opvitals.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Response saveSummaryTemplates(CreateSummaryTemplateRequest createSummaryTemplateRequest) {
        try {
            log.debug("START saveSummaryTemplates CreateSummaryTemplateRequest {}", createSummaryTemplateRequest);
            final SummaryTemplate summaryTemplate = SummaryTemplate.to(createSummaryTemplateRequest);
            this.summaryTemplateRepository.saveAndFlush(summaryTemplate);
            log.debug("END saveSummaryTemplates id ");
            return new Response(summaryTemplate.getId());
        } catch (Exception e) {
            log.error("Caught with exception while saveSummaryTemplates {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Response saveDentalCaseSheet(CreateDentalCasesheetRequest createDentalCasesheetRequest) {
        try {
            log.debug("START saveDentalCasesheet CreateDentalRequest {}", createDentalCasesheetRequest);

            final DentalCaseSheet newDentalCaseSheet = DentalCaseSheet.to(createDentalCasesheetRequest);
            newDentalCaseSheet.setDentalcomplaintDetailsList(DentalComplaintDetails.to(newDentalCaseSheet, createDentalCasesheetRequest.getCreateComplaintDetailsRequestList()));
            newDentalCaseSheet.setDentalTeethExamDetailsList(DentalTeethExamDetails.to(newDentalCaseSheet, createDentalCasesheetRequest.getCreateTeethExamDetailsRequestList()));
            newDentalCaseSheet.setDentalTeethTreatDetailsList(DentalTeethTreatDetails.to(newDentalCaseSheet, createDentalCasesheetRequest.getCreateTeethTreatDetailsRequestList()));
            dentalCaseSheetRepository.saveAndFlush(newDentalCaseSheet);

            log.debug("END saveDentalCasesheet id ");
            return new Response(newDentalCaseSheet.getId());
        } catch (Exception e) {
            log.error("Caught with exception while saving DentalCasesheet {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public Response updateDentalCaseSheet(Long id, CreateDentalCasesheetRequest createdentalCaseSheetRequest) {
        try {

            log.debug("START of updateDentalCaseSheet() id {} request {}", id, createdentalCaseSheetRequest);
            final DentalCaseSheet dentalCaseSheet = this.dentalCaseSheetRepositoryWrapper.findOneWithNotFoundDetection(id);
            dentalCaseSheet.update(createdentalCaseSheetRequest);
            this.dentalCaseSheetRepository.saveAndFlush(dentalCaseSheet);

            final List<DentalComplaintDetails> complaintDetailsList = this.dentalcomplaintDetailsRepository.fetchComplaintDetailsBycaseSheetId(dentalCaseSheet.getId());
            for (DentalComplaintDetails complaintDetails : complaintDetailsList) {
                complaintDetails.setIsValid(0L);
                dentalcomplaintDetailsRepository.save(complaintDetails);
            }

            final List<DentalTeethExamDetails> teethexamDetailsList = this.dentalTeethExamDetailsRepository.fetchTeethExamDetailsBycaseSheetId(dentalCaseSheet.getId());
            for (DentalTeethExamDetails teethexamDetails : teethexamDetailsList) {
                teethexamDetails.setIsValid(0L);
                dentalTeethExamDetailsRepository.save(teethexamDetails);
            }

            final List<DentalTeethTreatDetails> teethTreatDetailsList = this.dentalTeethTreatDetailsRepository.fetchTeethTreatDetailsBycaseSheetId(dentalCaseSheet.getId());
            for (DentalTeethTreatDetails teethTreatDetails : teethTreatDetailsList) {
                teethTreatDetails.setIsValid(0L);
                dentalTeethTreatDetailsRepository.save(teethTreatDetails);
            }

            dentalCaseSheet.setDentalcomplaintDetailsList(DentalComplaintDetails.to(dentalCaseSheet,createdentalCaseSheetRequest.getCreateComplaintDetailsRequestList()));
            dentalCaseSheet.setDentalTeethExamDetailsList(DentalTeethExamDetails.to(dentalCaseSheet,createdentalCaseSheetRequest.getCreateTeethExamDetailsRequestList()));
            dentalCaseSheet.setDentalTeethTreatDetailsList(DentalTeethTreatDetails.to(dentalCaseSheet,createdentalCaseSheetRequest.getCreateTeethTreatDetailsRequestList()));
            dentalCaseSheetRepository.saveAndFlush(dentalCaseSheet);

            log.debug("END of updateDentalCaseSheet() id {} request {}", id, createdentalCaseSheetRequest);
            return new Response(dentalCaseSheet.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public Response saveDischargeSummary(final CreateDischargeSummaryRequest createDischargeSummaryRequest) {
        try {
            log.debug("START saveDischargeSummary request {}", createDischargeSummaryRequest);

            final DischargeSummary newDischargeSummary = DischargeSummary.to(createDischargeSummaryRequest);
            newDischargeSummary.setSummaryDiagnosisDetailsList(SummaryDiagnosisDetails.to(newDischargeSummary,createDischargeSummaryRequest.getCreateSummaryDiagnosisDetailsRequestList()));
            newDischargeSummary.setLabAndInvestigationSummaryList(LabAndInvestigationSummary.to(newDischargeSummary, createDischargeSummaryRequest.getCreateLabAndInvSummaryRequestList()));
            newDischargeSummary.setDischargeSummaryConsultantList(DischargeSummaryConsultant.to(newDischargeSummary, createDischargeSummaryRequest.getCreateDisSumConsRequestList()));
            newDischargeSummary.setDischargeSummaryDeptList(DischargeSummaryDept.to(newDischargeSummary, createDischargeSummaryRequest.getCreateDisSumDeptRequestList()));
            dischargeSummaryRepository.saveAndFlush(newDischargeSummary);

            log.debug("END saveDischargeSummary id ");
            return new Response(newDischargeSummary.getId());
        } catch (Exception e) {
            log.error("Caught with exception while saving saveDischargeSummary {}", e.getMessage());
            throw new RuntimeException(e);

        }

    }

    @Transactional
    @Override
    public Response updateDischargeSummary(Long id, CreateDischargeSummaryRequest createDischargeSummaryRequest) {
        try {
            log.debug("START of updateDischargeSummary() id {} request {}", id, createDischargeSummaryRequest);
            final DischargeSummary dischargeSummary = this.disSummaryRepositoryWrapper.findOneWithNotFoundDetection(id);
            dischargeSummary.update(createDischargeSummaryRequest);
            this.dischargeSummaryRepository.saveAndFlush(dischargeSummary);

            final List<DiagnosisDetailsSummary> summaryDiagnosisDetailsList = this.diagnosisDetailsSummaryRepository.fetchDiagnosisDetailsBySumId(dischargeSummary.getId());
            for (DiagnosisDetailsSummary diagnosisDetailsSummary : summaryDiagnosisDetailsList) {
                diagnosisDetailsSummary.setIsValid(0L);
                diagnosisDetailsSummaryRepository.save(diagnosisDetailsSummary);
            }

            final List<LabAndInvestigationSummary> labAndInvestigationSummaryList = this.labAndInvSummaryRepository.fetchLabAndInvBySumId(dischargeSummary.getId());
            for (LabAndInvestigationSummary labAndInvestigationSummary : labAndInvestigationSummaryList) {
                labAndInvestigationSummary.setIsActive(0);
                labAndInvSummaryRepository.save(labAndInvestigationSummary);
            }

            final List<DischargeSummaryConsultant> dischargeSummaryConsultantList = this.disSumConsultantRepository.fetchConsultantDetailsBySumId(dischargeSummary.getId());
            for (DischargeSummaryConsultant dischargeSummaryConsultant : dischargeSummaryConsultantList) {
                dischargeSummaryConsultant.setIsValid(0);
                disSumConsultantRepository.save(dischargeSummaryConsultant);
            }

            final List<DischargeSummaryDept> dischargeSummaryDeptList = this.disSumDeptRepository.fetchDeptDetailsBySumId(dischargeSummary.getId());
            for (DischargeSummaryDept dischargeSummaryDept : dischargeSummaryDeptList) {
                dischargeSummaryDept.setIsValid(0);
                disSumDeptRepository.save(dischargeSummaryDept);
            }


            dischargeSummary.setSummaryDiagnosisDetailsList(SummaryDiagnosisDetails.to(dischargeSummary,createDischargeSummaryRequest.getCreateSummaryDiagnosisDetailsRequestList()));
            dischargeSummary.setLabAndInvestigationSummaryList(LabAndInvestigationSummary.to(dischargeSummary, createDischargeSummaryRequest.getCreateLabAndInvSummaryRequestList()));
            dischargeSummary.setDischargeSummaryConsultantList(DischargeSummaryConsultant.to(dischargeSummary, createDischargeSummaryRequest.getCreateDisSumConsRequestList()));
            dischargeSummary.setDischargeSummaryDeptList(DischargeSummaryDept.to(dischargeSummary, createDischargeSummaryRequest.getCreateDisSumDeptRequestList()));

            dischargeSummaryRepository.saveAndFlush(dischargeSummary);

            log.debug("END of updateDischargeSummary() id {} request {}", id, createDischargeSummaryRequest);
            return new Response(dischargeSummary.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    @Transactional
    public Response saveAntenatalCaseSheet(final CreateAntenatalCaseSheetRequest createAntenatalCaseSheetRequest) {
        try {
            log.debug("START saveAntenatalCaseSheet request {}", createAntenatalCaseSheetRequest);
//            this.caseSheetValidator.validateComplaintData(createAntenatalCaseSheetRequest.getCreateAntenatalPreviousCaseSheetRequests());
//            this.caseSheetValidator.GeneralCaseSheetDate(createAntenatalCaseSheetRequest);
//            if (generalCaseSheetRequest.getVisitId()==0 || generalCaseSheetRequest.getPatientId() ==0) {
//                throw new NullPointerException("Choose Proper Patient!");
//            }

        final AntenatalCaseSheet newAntenatalCaseSheet = AntenatalCaseSheet.to(createAntenatalCaseSheetRequest);
        newAntenatalCaseSheet.setAntenatalCaseSheetPreviousList(AntenatalCaseSheetPrevious.to(newAntenatalCaseSheet,createAntenatalCaseSheetRequest.getCreateAntenatalPreviousCaseSheetRequests()));
        // newAntenatalCaseSheet.setAntenatalCaseSheetCurrentSet(AntenatalCaseSheetCurrent.to(newAntenatalCaseSheet, createAntenatalCaseSheetRequest.getCreateAntenatalCurrentCaseSheetRequests()));
        this.antenatalCaseSheetRepository.saveAndFlush(newAntenatalCaseSheet);
            log.debug("END saveAntenatal id ");
        return new Response(newAntenatalCaseSheet.getId());
        } catch (Exception e) {
            log.error("Caught with exception while saving GeneralCaseSheet {}", e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Transactional
    @Override
    public Response updateAntenatalCaseSheet(Long id, CreateAntenatalCaseSheetRequest createAntenatalCaseSheetRequest) {
        try {
            log.debug("START of updateAntenatalCaseSheet() id {} request {}", id, createAntenatalCaseSheetRequest);
            final AntenatalCaseSheet antenatalCaseSheet = this.antenatalCaseSheetRepositotyWrapper.findOneWithNotFoundDetection(id);
            antenatalCaseSheet.update(createAntenatalCaseSheetRequest);
            this.antenatalCaseSheetRepository.saveAndFlush(antenatalCaseSheet);

            final List<AntenatalCaseSheetPrevious> antenatalCaseSheetPreviousList = this.antenatalCaseSheetPreRepository.fetchAntenatalPreviousBycaseSheetId(antenatalCaseSheet.getId());
            for (AntenatalCaseSheetPrevious antenatalCaseSheetPrevious : antenatalCaseSheetPreviousList) {
                antenatalCaseSheetPrevious.setIsValid(0L);
                antenatalCaseSheetPreRepository.save(antenatalCaseSheetPrevious);
            }

            antenatalCaseSheet.setAntenatalCaseSheetPreviousList(AntenatalCaseSheetPrevious.to(antenatalCaseSheet,createAntenatalCaseSheetRequest.getCreateAntenatalPreviousCaseSheetRequests()));
            antenatalCaseSheetRepository.saveAndFlush(antenatalCaseSheet);

            log.debug("END of updateAntenatalCaseSheet() id {} request {}", id, createAntenatalCaseSheetRequest);
            return new Response(antenatalCaseSheet.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    @Transactional
    public Response saveSurgeryCaseSheet(final CreateSurgeryCaseSheetRequest createSurgeryCaseSheetRequest) {
        try {
            log.debug("START saveAntenatalCaseSheet request {}", createSurgeryCaseSheetRequest);
//            this.caseSheetValidator.validateComplaintData(createSurgeryCaseSheetRequest.getCreateAntenatalPreviousCaseSheetRequests());
//            this.caseSheetValidator.GeneralCaseSheetDate(createSurgeryCaseSheetRequest);
//            if (generalCaseSheetRequest.getVisitId()==0 || generalCaseSheetRequest.getPatientId() ==0) {
//                throw new NullPointerException("Choose Proper Patient!");
//            }

            final SurgeryCaseSheet newSurgeryCaseSheet = SurgeryCaseSheet.to(createSurgeryCaseSheetRequest);
            newSurgeryCaseSheet.setSurgerySurgonList(SurgerySurgon.to(newSurgeryCaseSheet,createSurgeryCaseSheetRequest.getCreateSurigicalSurgonRequests()));
            newSurgeryCaseSheet.setSurgeryNurseList(SurgeryNurse.to(newSurgeryCaseSheet, createSurgeryCaseSheetRequest.getCreateSurigicalNurseRequests()));
            newSurgeryCaseSheet.setSurgeryDataList(SurgeryName.to(newSurgeryCaseSheet,createSurgeryCaseSheetRequest.getCreateSurgicalSurgeryRequests()));
            this.surgeryCaseSheetRepository.saveAndFlush(newSurgeryCaseSheet);
            log.debug("END saveAntenatal id ");
            return new Response(newSurgeryCaseSheet.getId());
        } catch (Exception e) {
            log.error("Caught with exception while saving GeneralCaseSheet {}", e.getMessage());
            throw new RuntimeException(e);
        }

    }


    @Transactional
    @Override
    public Response updatSurgeryCaseSheet(Long id, CreateSurgeryCaseSheetRequest createSurgeryCaseSheetRequest) {
        try {
            log.debug("START of updateSurgeryCaseSheet() id {} request {}", id, createSurgeryCaseSheetRequest);

            final SurgeryCaseSheet surgeryCaseSheet = this.surgeryCaseSheetRepositoryWrapper.findOneWithNotFoundDetection(id);
            surgeryCaseSheet.update(createSurgeryCaseSheetRequest);
            this.surgeryCaseSheetRepository.saveAndFlush(surgeryCaseSheet);

            final List<SurgeryNurse> surgeryNurseList = this.surgeryNurseRepository.fetchSurgeryNurseBycaseSheetId(surgeryCaseSheet.getId());
            for (SurgeryNurse surgeryNurse : surgeryNurseList) {
                surgeryNurse.setIsValid(0L);
                surgeryNurseRepository.save(surgeryNurse);
            }
            final List<SurgeryName> surgeryNameList = this.surgeryNAmeRepository.fetchSurgeryNameBycaseSheetId(surgeryCaseSheet.getId());
            for (SurgeryName surgeryName : surgeryNameList) {
                surgeryName.setIsValid(0L);
                surgeryNAmeRepository.save(surgeryName);
            }
            final List<SurgerySurgon> surgerySurgonList = this.surgerySurgonRepository.fetchSurgerySurgonBycaseSheetId(surgeryCaseSheet.getId());
            for (SurgerySurgon surgerySurgon : surgerySurgonList) {
                surgerySurgon.setIsVAlid(0L);
                surgerySurgonRepository.save(surgerySurgon);
            }

            surgeryCaseSheet.setSurgeryNurseList(SurgeryNurse.to(surgeryCaseSheet,createSurgeryCaseSheetRequest.getCreateSurigicalNurseRequests()));
            surgeryCaseSheet.setSurgeryDataList(SurgeryName.to(surgeryCaseSheet,createSurgeryCaseSheetRequest.getCreateSurgicalSurgeryRequests()));
            surgeryCaseSheet.setSurgerySurgonList(SurgerySurgon.to(surgeryCaseSheet,createSurgeryCaseSheetRequest.getCreateSurigicalSurgonRequests()));
            surgeryCaseSheetRepository.saveAndFlush(surgeryCaseSheet);


            log.debug("END of updateAntenatalCaseSheet() id {} request {}", id, createSurgeryCaseSheetRequest);
            return new Response(surgeryCaseSheet.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Response saveNursingIoSheet(CreateNursingIoReuest createNursingIoReuest) {
        try {
            log.debug("START saveNursingIo CreateNursing io {}", createNursingIoReuest);
            final NursingIoSheet nursingIoSheet = NursingIoSheet.to(createNursingIoReuest);

            this.nursingIoRepository.saveAndFlush(nursingIoSheet);
            log.debug("END saveNursingIo id ");
            return new Response(nursingIoSheet.getId());
        } catch (Exception e) {
            log.error("Caught with exception while savingOrderDiscount {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }


    }
    @Transactional
    @Override
    public Response updateNursingIoSheet(Long id, CreateNursingIoReuest createNursingIoReuest) {
        try {
            log.debug("START of CreateNursingIoReuest() id {} request {}", id, createNursingIoReuest);
            final NursingIoSheet nursingIoSheet = this.nursingIoRepositoryWrapper.findOneWithNotFoundDetection(id);
            nursingIoSheet.update(createNursingIoReuest);
            this.nursingIoRepository.saveAndFlush(nursingIoSheet);

            log.debug("END of updateNursingIoReuest() id {} request {}", id, createNursingIoReuest);
            return new Response(nursingIoSheet.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Response saveSurgeryChecklist(CreateSurgeryChecklistReuest createSurgeryChecklistReuest) {
        try {
            log.debug("START saveSurgeryChecklist CreateSurgeryChecklistReuest {}", createSurgeryChecklistReuest);
            final SurgeryCheck surgeryCheck = SurgeryCheck.to(createSurgeryChecklistReuest);

            this.surgeryChecklistRepository.saveAndFlush(surgeryCheck);
            log.debug("END saveSurgeryChecklist id ");
            return new Response(surgeryCheck.getId());
        } catch (Exception e) {
            log.error("Caught with exception while saveSurgeryChecklist {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }


    }
    @Transactional
    @Override
    public Response updateSurgeryChecklist(Long id, CreateSurgeryChecklistReuest createSurgeryChecklistReuest) {
        try {
            log.debug("START of updateSurgeryChecklist() id {} request {}", id, createSurgeryChecklistReuest);
            final SurgeryCheck surgeryCheck = this.surgeryChecklistRepositoryWrapper.findOneWithNotFoundDetection(id);
            surgeryCheck.update(createSurgeryChecklistReuest);
            this.surgeryChecklistRepository.saveAndFlush(surgeryCheck);

            log.debug("END of updateSurgeryChecklist() id {} request {}", id, createSurgeryChecklistReuest);
            return new Response(surgeryCheck.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public Response saveIpProcedureCaseSheet(final CreateIpProcedureCaseSheetRequest createIpProcedureCaseSheetRequest){
        try {
            log.debug("START saveGeneralCaseSheet request {}", createIpProcedureCaseSheetRequest);
            if (createIpProcedureCaseSheetRequest.getVisitId()==0 || createIpProcedureCaseSheetRequest.getPatientId()==0){
                throw new NullPointerException("Choose Proper Patient!");
            }
            final IpProcedureCaseSheet newIpProcedureCaseSheet = IpProcedureCaseSheet.to(createIpProcedureCaseSheetRequest);
            ipProcedureCaseSheetRepository.saveAndFlush(newIpProcedureCaseSheet);
            log.debug("End saveIpProcedureCaseSheet");
            return new Response(newIpProcedureCaseSheet.getId());
        }
        catch (Exception e) {
            log.error("Caught with exception while saving GeneralCaseSheet {}", e.getMessage());
            throw new RuntimeException(e);

        }
    }

    @Transactional
    @Override
    public Response updateIpProcedureCaseSheet(Long id, CreateIpProcedureCaseSheetRequest createIpProcedureCaseSheetRequest) {
        try {
            log.debug("START updateIpProcedureCaseSheet() id {} request {}", id, createIpProcedureCaseSheetRequest);
            final IpProcedureCaseSheet ipProcedureCaseSheet = this.ipProcedureCaseSheetRepositoryWrapper.findOneWithNotFoundDetection(id);
            ipProcedureCaseSheet.update(createIpProcedureCaseSheetRequest);
            this.ipProcedureCaseSheetRepository.saveAndFlush(ipProcedureCaseSheet);

            log.debug("END of updateIpProcedureCaseSheet() id {} request {}", id, createIpProcedureCaseSheetRequest);
            return new Response(ipProcedureCaseSheet.getId());
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public Response saveAldreteScoreChart(CreateAldreteScoreChartReuest createAldreteScoreChartReuest) {
        try {
            log.debug("START saveAldreteScoreChart CreateAldreteScoreChartReuest {}", createAldreteScoreChartReuest);
            final AldreteScoreChart aldreteScoreChart = AldreteScoreChart.to(createAldreteScoreChartReuest);

            this.aldreteScoreChartRepository.saveAndFlush(aldreteScoreChart);
            log.debug("END saveAldreteScoreChart id ");
            return new Response(aldreteScoreChart.getId());
        } catch (Exception e) {
            log.error("Caught with exception while saveSurgeryChecklist {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }


    }

    @Transactional
    @Override
    public Response updateAldreteScoreChart(Long id, CreateAldreteScoreChartReuest createAldreteScoreChartReuest) {
        try {
            log.debug("START of updateAldreteScoreChart() id {} request {}", id, createAldreteScoreChartReuest);
            final AldreteScoreChart aldreteScoreChart = this.aldreteScoreChartRepositoryWrapper.findOneWithNotFoundDetection(id);
            aldreteScoreChart.update(createAldreteScoreChartReuest);
            this.aldreteScoreChartRepository.saveAndFlush(aldreteScoreChart);

            log.debug("END of updateSurgeryChecklist() id {} request {}", id, createAldreteScoreChartReuest);
            return new Response(aldreteScoreChart.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public Response saveDermatologyCaseSheet(final CreateDermatologyCaseSheetRequest createDermatologyCaseSheetRequest) {
        try {
            log.debug("START saveDermatologyCaseSheet request {}", createDermatologyCaseSheetRequest);
            this.caseSheetValidator.validateComplaintData(createDermatologyCaseSheetRequest.getCreateComplaintDetailsRequestList());
            //this.caseSheetValidator.DermatologyCaseSheetDate(createDermatologyCaseSheetRequest);
            if (createDermatologyCaseSheetRequest.getVisitId()==0 || createDermatologyCaseSheetRequest.getPatientId() ==0) {
                throw new NullPointerException("Choose Proper Patient!");
            }

            final DermatologyCaseSheet newDermatologyCaseSheet = DermatologyCaseSheet.to(createDermatologyCaseSheetRequest);
            newDermatologyCaseSheet.setDermatologyComplaintDetailsList(DermatologyComplaintDetails.to(newDermatologyCaseSheet,createDermatologyCaseSheetRequest.getCreateComplaintDetailsRequestList()));
            newDermatologyCaseSheet.setDermatologyDiagnosisDetailsList(DermatologyDiagnosisDetails.to(newDermatologyCaseSheet,createDermatologyCaseSheetRequest.getCreateDiagnosisDetailsRequestList()));
            dermatologyCaseSheetRepository.saveAndFlush(newDermatologyCaseSheet);
            log.debug("END saveDermatologyCaseSheet id ");
            return new Response(newDermatologyCaseSheet.getId());
        } catch (Exception e) {
            log.error("Caught with exception while saving GeneralCaseSheet {}", e.getMessage());
            throw new RuntimeException(e);

        }

    }

    @Transactional
    @Override
    public Response updateDermatologyCaseSheet(Long id, CreateDermatologyCaseSheetRequest createDermatologyCaseSheetRequest,Integer caseSheetType) {
        try {
            log.debug("START of updateDermatologyCaseSheet() id {} request {} caseSheetType{}", id, createDermatologyCaseSheetRequest,caseSheetType);
            final DermatologyCaseSheet dermatologyCaseSheet = this.dermatologyCaseSheetRepositoryWrapper.findOneWithNotFoundDetection(id);
            dermatologyCaseSheet.update(createDermatologyCaseSheetRequest);
            this.dermatologyCaseSheetRepository.saveAndFlush(dermatologyCaseSheet);

            final List<DermatologyComplaintDetails> complaintDetailsList = this.dermatologyComplainDetailsRepository.fetchDermatologyComplaintDetailsBycaseSheetId(dermatologyCaseSheet.getId(),caseSheetType);
            for (DermatologyComplaintDetails dermatologyComplaintDetails : complaintDetailsList) {
                dermatologyComplaintDetails.setIsValid(0L);
                dermatologyComplainDetailsRepository.save(dermatologyComplaintDetails);
            }


            final List<DermatologyDiagnosisDetails> dermatologyDiagnosisDetailsList = this.dermatologyDiagnosisDetailsRepository.fetchDermatologyDiagnosisDetailsBycaseSheetId(dermatologyCaseSheet.getId());
            for (DermatologyDiagnosisDetails dermatologyDiagnosisDetails : dermatologyDiagnosisDetailsList) {
                dermatologyDiagnosisDetails.setIsValid(0L);
                dermatologyDiagnosisDetailsRepository.save(dermatologyDiagnosisDetails);
            }

            dermatologyCaseSheet.setDermatologyComplaintDetailsList(DermatologyComplaintDetails.to(dermatologyCaseSheet,createDermatologyCaseSheetRequest.getCreateComplaintDetailsRequestList()));
            dermatologyCaseSheet.setDermatologyDiagnosisDetailsList(DermatologyDiagnosisDetails.to(dermatologyCaseSheet, createDermatologyCaseSheetRequest.getCreateDiagnosisDetailsRequestList()));
            dermatologyCaseSheetRepository.saveAndFlush(dermatologyCaseSheet);

            log.debug("END of updateDermatologyCaseSheet() id {} request {}", id, createDermatologyCaseSheetRequest);
            return new Response(dermatologyCaseSheet.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public Response saveOpthamologyCaseSheet(final CreateOpthamologyCaseSheetRequest createOpthamologyCaseSheetRequest) {
        try {
            log.debug("START saveOpthamologyCaseSheet request {}", createOpthamologyCaseSheetRequest);
            this.caseSheetValidator.validateComplaintData(createOpthamologyCaseSheetRequest.getCreateComplaintDetailsRequestList());
            //this.caseSheetValidator.DermatologyCaseSheetDate(createDermatologyCaseSheetRequest);
            if (createOpthamologyCaseSheetRequest.getVisitId()==0 || createOpthamologyCaseSheetRequest.getPatientId() ==0) {
                throw new NullPointerException("Choose Proper Patient!");
            }

            final OpthamologyCaseSheet newOpthamologyCaseSheet = OpthamologyCaseSheet.to(createOpthamologyCaseSheetRequest);
            newOpthamologyCaseSheet.setOpthamologyComplaintDetailsList(OpthamologyComplaintDetails.to(newOpthamologyCaseSheet,createOpthamologyCaseSheetRequest.getCreateComplaintDetailsRequestList()));
            newOpthamologyCaseSheet.setOpthamologyDiagnosisDetailsList(OpthamologyDiagnosisDetails.to(newOpthamologyCaseSheet,createOpthamologyCaseSheetRequest.getCreateDiagnosisDetailsRequestList()));
            opthamologyCaseSheetRepository.saveAndFlush(newOpthamologyCaseSheet);
            log.debug("END saveDermatologyCaseSheet id ");
            return new Response(newOpthamologyCaseSheet.getId());
        } catch (Exception e) {
            log.error("Caught with exception while saving GeneralCaseSheet {}", e.getMessage());
            throw new RuntimeException(e);

        }

    }

    @Transactional
    @Override
    public Response updateOpthamologyCaseSheet(Long id, CreateOpthamologyCaseSheetRequest createOpthamologyCaseSheetRequest,Integer caseSheetType) {
        try {
            log.debug("START of updateOpthamologyCaseSheet() id {} request {} caseSheetType{}", id, createOpthamologyCaseSheetRequest,caseSheetType);
            final OpthamologyCaseSheet opthamologyCaseSheet = this.opthamologyCaseSheetRepositoryWrapper.findOneWithNotFoundDetection(id);
            opthamologyCaseSheet.update(createOpthamologyCaseSheetRequest);
            this.opthamologyCaseSheetRepository.saveAndFlush(opthamologyCaseSheet);

            final List<OpthamologyComplaintDetails> complaintDetailsList = this.opthamologyComplainDetailsRepository.fetchOpthamologyComplaintDetailsBycaseSheetId(opthamologyCaseSheet.getId(),caseSheetType);
            for (OpthamologyComplaintDetails opthamologyComplaintDetails : complaintDetailsList) {
                opthamologyComplaintDetails.setIsValid(0L);
                opthamologyComplainDetailsRepository.save(opthamologyComplaintDetails);
            }


            final List<OpthamologyDiagnosisDetails> opthamologyDiagnosisDetailsList = this.opthamologyDiagnosisDetailsRepository.fetchOpthamologyDiagnosisDetailsBycaseSheetId(opthamologyCaseSheet.getId());
            for (OpthamologyDiagnosisDetails opthamologyDiagnosisDetails : opthamologyDiagnosisDetailsList) {
                opthamologyDiagnosisDetails.setIsValid(0L);
                opthamologyDiagnosisDetailsRepository.save(opthamologyDiagnosisDetails);
            }

            opthamologyCaseSheet.setOpthamologyComplaintDetailsList(OpthamologyComplaintDetails.to(opthamologyCaseSheet,createOpthamologyCaseSheetRequest.getCreateComplaintDetailsRequestList()));
            opthamologyCaseSheet.setOpthamologyDiagnosisDetailsList(OpthamologyDiagnosisDetails.to(opthamologyCaseSheet, createOpthamologyCaseSheetRequest.getCreateDiagnosisDetailsRequestList()));
            opthamologyCaseSheetRepository.saveAndFlush(opthamologyCaseSheet);

            log.debug("END of updateOpthamologyCaseSheet() id {} request {}", id, createOpthamologyCaseSheetRequest);
            return new Response(opthamologyCaseSheet.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public Response saveENTCaseSheet(final CreateENTCaseSheetRequest createENTCaseSheetRequest) {
        try {
            log.debug("START saveENTCaseSheet request {}", createENTCaseSheetRequest);
            this.caseSheetValidator.validateComplaintData(createENTCaseSheetRequest.getCreateComplaintDetailsRequestList());
            //this.caseSheetValidator.DermatologyCaseSheetDate(createDermatologyCaseSheetRequest);
            if (createENTCaseSheetRequest.getVisitId()==0 || createENTCaseSheetRequest.getPatientId() ==0) {
                throw new NullPointerException("Choose Proper Patient!");
            }

            final ENTCaseSheet newENTCaseSheet = ENTCaseSheet.to(createENTCaseSheetRequest);
            newENTCaseSheet.setEntComplaintDetailsList(ENTComplaintDetails.to(newENTCaseSheet,createENTCaseSheetRequest.getCreateComplaintDetailsRequestList()));
            newENTCaseSheet.setEntDiagnosisDetailsList(ENTDiagnosisDetails.to(newENTCaseSheet,createENTCaseSheetRequest.getCreateDiagnosisDetailsRequestList()));
            entCaseSheetRepository.saveAndFlush(newENTCaseSheet);
            log.debug("END saveENTCaseSheet id ");
            return new Response(newENTCaseSheet.getId());
        } catch (Exception e) {
            log.error("Caught with exception while saving GeneralCaseSheet {}", e.getMessage());
            throw new RuntimeException(e);

        }

    }

    @Transactional
    @Override
    public Response updateENTCaseSheet(Long id, CreateENTCaseSheetRequest createENTCaseSheetRequest,Integer caseSheetType) {
        try {
            log.debug("START of updateENTCaseSheet() id {} request {} caseSheetType{}", id, createENTCaseSheetRequest,caseSheetType);
            final ENTCaseSheet entCaseSheet = this.entCaseSheetRepositoryWrapper.findOneWithNotFoundDetection(id);
            entCaseSheet.update(createENTCaseSheetRequest);
            this.entCaseSheetRepository.saveAndFlush(entCaseSheet);

            final List<ENTComplaintDetails> complaintDetailsList = this.entComplainDetailsRepository.fetchENTComplaintDetailsBycaseSheetId(entCaseSheet.getId(),caseSheetType);
            for (ENTComplaintDetails entComplaintDetails : complaintDetailsList) {
                entComplaintDetails.setIsValid(0L);
                entComplainDetailsRepository.save(entComplaintDetails);
            }


            final List<ENTDiagnosisDetails> entDiagnosisDetailsList = this.entDiagnosisDetailsRepository.fetchENTDiagnosisDetailsBycaseSheetId(entCaseSheet.getId());
            for (ENTDiagnosisDetails entDiagnosisDetails : entDiagnosisDetailsList) {
                entDiagnosisDetails.setIsValid(0L);
                entDiagnosisDetailsRepository.save(entDiagnosisDetails);
            }

            entCaseSheet.setEntComplaintDetailsList(ENTComplaintDetails.to(entCaseSheet,createENTCaseSheetRequest.getCreateComplaintDetailsRequestList()));
            entCaseSheet.setEntDiagnosisDetailsList(ENTDiagnosisDetails.to(entCaseSheet, createENTCaseSheetRequest.getCreateDiagnosisDetailsRequestList()));
            entCaseSheetRepository.saveAndFlush(entCaseSheet);

            log.debug("END of updateDermatologyCaseSheet() id {} request {}", id, createENTCaseSheetRequest);
            return new Response(entCaseSheet.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
