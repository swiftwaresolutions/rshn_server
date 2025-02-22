package com.ueniweb.swiftwaresolutions.rowmapper;

import com.ueniweb.swiftwaresolutions.data.AntenatelCaseSheetData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AntenatalCaseSheetRowMapper implements RowMapper<AntenatelCaseSheetData> {
    private final String schema;

    private final String tableSchema;
    public AntenatalCaseSheetRowMapper() {
        final StringBuilder tableBuilder = new StringBuilder(200);
        tableBuilder.append(" FROM cli_antenatal_master a ");

        final StringBuilder builder = new StringBuilder(200);
        builder.append("a.id as id, ");
        builder.append("a.patId as patId, ");
        builder.append("a.vstId as vstId , ");
        builder.append("a.ipId as ipId, ");
        builder.append("a.time as time, ");
        builder.append("a.date as date, ");
        builder.append("a.durationofmarriage as durationofmarriage, ");
        builder.append("a.Consanguinity as Consanguinity, ");
        builder.append("a.Conception as Conception, ");
        builder.append("a.Menstrualhistory as Menstrualhistory, ");
        builder.append("a.Hypertension as Hypertension, ");
        builder.append("a.DiabetesMellitus as DiabetesMellitus, ");
        builder.append("a.TwinGestation as TwinGestation, ");
        builder.append("a.Chiefcomplaints as Chiefcomplaints, ");
        builder.append("a.Historyofpresentillness as Historyofpresentillness, ");
        builder.append("a.Menstrualhistroy as Menstrualhistroy, ");
        builder.append("a.Martialhistroy as Martialhistroy, ");
        builder.append("a.FamilyHistory as FamilyHistory, ");
        builder.append("a.SurgicalHistory as SurgicalHistory, ");
        builder.append("a.Allergichistory as Allergichistory, ");
        builder.append("a.Others as Others, ");
        builder.append("a.Instrumentaldelivery as Instrumentaldelivery, ");
        builder.append("a.Eclampsia as Eclampsia, ");
        builder.append("a.PostPartumHaemorrhage as PostPartumHaemorrhage, ");
        builder.append("a.3ormoreabortions as 3ormoreabortions, ");
        builder.append("a.Previouspretermdelivery as Previouspretermdelivery, ");
        builder.append("a.Stillbirthneonataldeath as Stillbirthneonataldeath, ");
        builder.append("a.CaesareanSection as CaesareanSection, ");
        builder.append("a.examDate as examDate, ");
        builder.append("a.weight as weight,");
        builder.append("a.PedalOdema as PedalOdema ,");
        builder.append("a.Bp as Bp ,");
        builder.append("a.Fundalheight as Fundalheight, ");
        builder.append("a.Presentation as Presentation ,");
        builder.append("a.fhs as fhs, ");
        builder.append("a.pallor as pallor ,");
        builder.append("a.iceturs as iceturs ,");
        builder.append("a.cyanosis as cyanosis ,");
        builder.append("a.clubbing as clubbing, ");
        builder.append("a.lymphadenopathy as lymphadenopathy ,");
        builder.append("a.edama as edama ,");
        builder.append("a.Lmp as Lmp, ");
        builder.append("a.Edd as Edd, ");
        builder.append("a.Ga as Ga ,");
        builder.append("a.G as G ,");
        builder.append("a.P as P ,");
        builder.append("a.L as L ,");
        builder.append("a.A as A ,");
        builder.append("a.Modeofconception as Modeofconception, ");
        builder.append("a.RedDischange as RedDischange, ");
        builder.append("a.RedDischangeInput as RedDischangeInput, ");
        builder.append("a.Vomiting as Vomiting ,");
        builder.append("a.VomitingInput as VomitingInput ,");
        builder.append("a.UrinComp as UrinComp ,");
        builder.append("a.UrinCompInput as UrinCompInput ,");
        builder.append("a.TakeFolSupplement as TakeFolSupplement ,");
        builder.append("a.TakeFolSupplementInput as TakeFolSupplementInput, ");
        builder.append("a.HisFoldFever as HisFoldFever ,");
        builder.append("a.HisFoldFeverInput as HisFoldFeverInput ,");
        builder.append("a.Quidceing as Quidceing ,");
        builder.append("a.Quidceinginput as Quidceinginput ,");
        builder.append("a.AnyHistory as AnyHistory ,");
        builder.append("a.1stDose as 1stDose, ");
        builder.append("a.2ndDose as 2ndDose ,");
        builder.append("a.dose3 as dose3 ,");
        builder.append("a.PerFutalmovement as PerFutalmovement, ");
        builder.append("a.PerFutalMovementIn as PerFutalMovementIn ,");
        builder.append("a.bleeding as bleeding, ");
        builder.append("a.bleedingInput as bleedingInput ,");
        builder.append("a.AnyComplaints as AnyComplaints, ");
        builder.append("a.AnyComplaintsInput as AnyComplaintsInput, ");
        builder.append("a.PrimiUnder as PrimiUnder, ");
        builder.append("a.PrimiAbove as PrimiAbove, ");
        builder.append("a.AgeAbove as AgeAbove ,");
        builder.append("a.PregnanceAfter as PregnanceAfter, ");
        builder.append("a.Infertility as Infertility, ");
        builder.append("a.FifthPregancy as FifthPregancy, ");
        builder.append("a.HeightD as HeightD, ");
        builder.append("a.SystemicDisease as SystemicDisease ,");
        builder.append("a.ThreatenedAbortion as ThreatenedAbortion ,");
        builder.append("a.GestationExceeding as GestationExceeding, ");
        builder.append("a.PrematureLabour as PrematureLabour ,");
        builder.append("a.Apa as Apa, ");
        builder.append("a.Abnormalpresentation as Abnormalpresentation, ");
        builder.append("a.IUGR as IUGR ,");
        builder.append("a.Multiplepregnancy as Multiplepregnancy ,");
        builder.append("a.Hydraminos as Hydraminos ,");
        builder.append("a.PIH as PIH, ");
        builder.append("a.ServerAnaemia as ServerAnaemia ,");
        builder.append("a.CardiacDisease as CardiacDisease, ");
        builder.append("a.GDMonOHA as GDMonOHA ,");
        builder.append("a.GDMoninsulin as GDMoninsulin, ");
        builder.append("a.temperature as temperature, ");
        builder.append("a.Pulse as Pulse, ");
        builder.append("a.rr as rr, ");
        builder.append("a.Fhr as Fhr ,");
        builder.append("a.Engagement as Engagement ,");
        builder.append("a.lie as lie ,");
        builder.append("a.FinalGestationalAge as FinalGestationalAge ,");
        builder.append("a.usgAge as usgAge ,");
        builder.append("a.Cervicaldilation as Cervicaldilation ,");
        builder.append("a.Cervicaleffacement as Cervicaleffacement ,");
        builder.append("a.Clrfluid as Clrfluid ,");
        builder.append("a.Station as Station ,");
        builder.append("a.Pelvisadequate as Pelvisadequate ,");
        builder.append("a.LabExam as LabExam ,");
        builder.append("a.USGExam as USGExam ,");
        builder.append("a.otherExam as otherExam ,");
        builder.append("a.Membrane as Membrane, ");
        builder.append("a.comboteim2 as comboteim2 ,");
        builder.append("a.comboteim2In as comboteim2In, ");
        builder.append("a.comboteim3 as comboteim3 ,");
        builder.append("a.comboteim3In as comboteim3In ,");
        builder.append("a.medicalHis as medicalHis, ");
        builder.append("a.anemiaPre as anemiaPre, ");
        builder.append("a.obstructedlabour as obstructedlabour ,");
        builder.append("a.aphpph as aphpph ,");
        builder.append("a.congenitalAnomaly as congenitalAnomaly ,");
        builder.append("a.othesPreRisk as othesPreRisk ,");
        builder.append("a.IfCaesarean as IfCaesarean ,");
        builder.append("a.1stDose2 as 1stDose2 ,");
        builder.append("a.1stDoseIn as 1stDoseIn ,");
        builder.append("a.2ndDose2 as 2ndDose2 ,");
        builder.append("a.2ndDoseIn as 2ndDoseIn, ");
        builder.append("a.booster as booster ,");
        builder.append("a.boosterIn as boosterIn ,");
        builder.append("a.trimester1 as trimester1 ,");
        builder.append("a.trimester2 as trimester2 ,");
        builder.append("a.trimester3 as trimester3 ,");
        builder.append("a.shortgravida as shortgravida ,");
        builder.append("a.teenagePrimi as teenagePrimi ,");
        builder.append("a.pregnancyInfertility as pregnancyInfertility ,");
        builder.append("a.gth as gth ,");
        builder.append("a.elderlyGravida as elderlyGravida, ");
        builder.append("a.grandmulti as grandmulti ,");
        builder.append("a.gawks as gawks, ");
        builder.append("a.anemiaCur as anemiaCur ,");
        builder.append("a.chronicDisease as chronicDisease ,");
        builder.append("a.othesCurRisk as othesCurRisk ");

        builder.append(tableBuilder);
        this.tableSchema = tableBuilder.toString();
        this.schema = builder.toString();
    }
    public String schema() {
        return this.schema;
    }

    public String tableSchema() {
        return this.tableSchema;
    }

    @Override
    public AntenatelCaseSheetData mapRow(ResultSet rs, int i) throws SQLException {
        final Long id = rs.getLong("id");
        final Long patId = rs.getLong("patId");
        final Long visitId = rs.getLong("vstId");
        final Long ipId = rs.getLong("ipId");
        final String time = rs.getString("time");
        final String date =rs.getString("date");
        final String durationofmarriage = rs.getString("durationofmarriage");
        final String Consanguinity = rs.getString("Consanguinity");
        final String Conception = rs.getString("Conception");
        final String Menstrualhistory = rs.getString("Menstrualhistory");
        final String Hypertension = rs.getString("Hypertension");
        final String DiabetesMellitus = rs.getString("DiabetesMellitus");
        final String TwinGestation = rs.getString("TwinGestation");
        final String Chiefcomplaints = rs.getString("Chiefcomplaints");
        final String Historyofpresentillness = rs.getString("Historyofpresentillness");
        final String Menstrualhistroy = rs.getString("Menstrualhistroy");
        final String Martialhistroy = rs.getString("Martialhistroy");
        final String FamilyHistory = rs.getString("FamilyHistory");
        final String SurgicalHistory = rs.getString("SurgicalHistory");
        final String Allergichistory = rs.getString("Allergichistory");
        final String Others = rs.getString("Others");
        final String Instrumentaldelivery = rs.getString("Instrumentaldelivery");
        final String Eclampsia = rs.getString("Eclampsia");
        final String PostPartumHaemorrhage = rs.getString("PostPartumHaemorrhage");
        final String ormoreabortions = rs.getString("3ormoreabortions");
        final String Previouspretermdelivery = rs.getString("Previouspretermdelivery");
        final String Stillbirthneonataldeath = rs.getString("Stillbirthneonataldeath");
        final String CaesareanSection = rs.getString("CaesareanSection");
        final String examDate = rs.getString("examDate");
        final String weight = rs.getString("weight");
        final String PedalOdema = rs.getString("PedalOdema");
        final String Bp = rs.getString("Bp");
        final String Fundalheight = rs.getString("Fundalheight");
        final String Presentation = rs.getString("Presentation");
        final String fhs = rs.getString("fhs");
        final String pallor = rs.getString("pallor");
        final String iceturs = rs.getString("iceturs");
        final String cyanosis = rs.getString("cyanosis");
        final String clubbing = rs.getString("clubbing");
        final String lymphadenopathy = rs.getString("lymphadenopathy");
        final String edama = rs.getString("edama");
        final String Lmp = rs.getString("Lmp");
        final String Edd = rs.getString("Edd");
        final String Ga = rs.getString("Ga");
        final String G = rs.getString("G");
        final String P = rs.getString("P");
        final String L = rs.getString("L");
        final String A = rs.getString("A");
        final String Modeofconception = rs.getString("Modeofconception");
        final String RedDischange = rs.getString("RedDischange");
        final String RedDischangeInput = rs.getString("RedDischangeInput");
        final String Vomiting = rs.getString("Vomiting");
        final String VomitingInput = rs.getString("VomitingInput");
        final String UrinComp = rs.getString("UrinComp");
        final String UrinCompInput = rs.getString("UrinCompInput");
        final String TakeFolSupplement = rs.getString("TakeFolSupplement");
        final String TakeFolSupplementInput = rs.getString("TakeFolSupplementInput");
        final String HisFoldFever = rs.getString("HisFoldFever");
        final String HisFoldFeverInput = rs.getString("HisFoldFeverInput");
        final String Quidceing = rs.getString("Quidceing");
        final String Quidceinginput = rs.getString("Quidceinginput");
        final String AnyHistory = rs.getString("AnyHistory");
        final String stDose = rs.getString("1stDose");
        final String ndDose = rs.getString("2ndDose");
        final String dose3 = rs.getString("dose3");
        final String PerFutalmovement = rs.getString("PerFutalmovement");
        final String PerFutalMovementIn = rs.getString("PerFutalMovementIn");
        final String bleeding = rs.getString("bleeding");
        final String bleedingInput = rs.getString("bleedingInput");
        final String AnyComplaints = rs.getString("AnyComplaints");
        final String AnyComplaintsInput = rs.getString("AnyComplaintsInput");
        final String PrimiUnder = rs.getString("PrimiUnder");
        final String PrimiAbove = rs.getString("PrimiAbove");
        final String AgeAbove = rs.getString("AgeAbove");
        final String PregnanceAfter = rs.getString("PregnanceAfter");
        final String Infertility = rs.getString("Infertility");
        final String FifthPregancy = rs.getString("FifthPregancy");
        final String HeightD = rs.getString("HeightD");
        final String SystemicDisease = rs.getString("SystemicDisease");
        final String ThreatenedAbortion = rs.getString("ThreatenedAbortion");
        final String GestationExceeding = rs.getString("GestationExceeding");
        final String PrematureLabour = rs.getString("PrematureLabour");
        final String Apa = rs.getString("Apa");
        final String Abnormalpresentation = rs.getString("Abnormalpresentation");
        final String IUGR = rs.getString("IUGR");
        final String Multiplepregnancy = rs.getString("Multiplepregnancy");
        final String Hydraminos = rs.getString("Hydraminos");
        final String PIH = rs.getString("PIH");
        final String ServerAnaemia = rs.getString("ServerAnaemia");
        final String CardiacDisease = rs.getString("CardiacDisease");
        final String GDMonOHA = rs.getString("GDMonOHA");
        final String GDMoninsulin = rs.getString("GDMoninsulin");
        final String temperature = rs.getString("temperature");
        final String Pulse = rs.getString("Pulse");
        final String rr = rs.getString("rr");
        final String Fhr = rs.getString("Fhr");
        final String Engagement = rs.getString("Engagement");
        final String lie = rs.getString("lie");
        final String finalGestationalAge = rs.getString("FinalGestationalAge");
        final String usgAge = rs.getString("usgAge");
        final String Cervicaldilation = rs.getString("Cervicaldilation");
        final String Cervicaleffacement = rs.getString("Cervicaleffacement");
        final String Membrane = rs.getString("Membrane");
        final String Clrfluid = rs.getString("Clrfluid");
        final String Station = rs.getString("Station");
        final String Pelvisadequate = rs.getString("Pelvisadequate");
        final String LabExam = rs.getString("LabExam");
        final String USGExam = rs.getString("USGExam");
        final String comboteim2 = rs.getString("comboteim2");
        final String comboteim2In = rs.getString("comboteim2In");
        final String comboteim3 = rs.getString("comboteim3");
        final String comboteim3In = rs.getString("comboteim3In");
        final String otherExam = rs.getString("otherExam");
        final String medicalHis = rs.getString("medicalHis");
        final String anemiaPre = rs.getString("anemiaPre");
        final String obstructedlabour = rs.getString("obstructedlabour");
        final String aphpph = rs.getString("aphpph");
        final String congenitalAnomaly = rs.getString("congenitalAnomaly");
        final String othesPreRisk = rs.getString("othesPreRisk");
        final String IfCaesarean = rs.getString("IfCaesarean");
        final String stDose2 = rs.getString("1stDose2");
        final String stDoseIn = rs.getString("1stDoseIn");
        final String ndDose2 = rs.getString("2ndDose2");
        final String ndDoseIn = rs.getString("2ndDoseIn");
        final String booster = rs.getString("booster");
        final String boosterIn = rs.getString("boosterIn");
        final String trimester1 = rs.getString("trimester1");
        final String trimester2 = rs.getString("trimester2");
        final String trimester3 = rs.getString("trimester3");
        final String shortgravida = rs.getString("shortgravida");
        final String teenagePrimi = rs.getString("teenagePrimi");
        final String pregnancyInfertility = rs.getString("pregnancyInfertility");
        final String gth = rs.getString("gth");
        final String elderlyGravida = rs.getString("elderlyGravida");
        final String grandmulti = rs.getString("grandmulti");
        final String gawks = rs.getString("gawks");
        final String anemiaCur = rs.getString("anemiaCur");
        final String chronicDisease = rs.getString("chronicDisease");
        final String othesCurRisk = rs.getString("othesCurRisk");





        return AntenatelCaseSheetData.newInstance(id,  patId, visitId, ipId, time, date, durationofmarriage, Consanguinity, Conception, Menstrualhistory, Hypertension, DiabetesMellitus,  TwinGestation,
                Chiefcomplaints, Historyofpresentillness, Menstrualhistroy, Martialhistroy,  FamilyHistory, SurgicalHistory, Allergichistory, Others, Instrumentaldelivery, Eclampsia, PostPartumHaemorrhage,
                ormoreabortions, Previouspretermdelivery, Stillbirthneonataldeath, CaesareanSection, examDate, weight, PedalOdema, Bp, Fundalheight, Presentation, fhs, pallor, iceturs,cyanosis, clubbing, lymphadenopathy, edama, Lmp, Edd,
                Ga, G, P, L, A, Modeofconception, RedDischange, RedDischangeInput, Vomiting, VomitingInput, UrinComp, UrinCompInput,  TakeFolSupplement, TakeFolSupplementInput, HisFoldFever, HisFoldFeverInput, Quidceing, Quidceinginput,
                AnyHistory, stDose, ndDose, dose3, PerFutalmovement, PerFutalMovementIn, bleeding, bleedingInput, AnyComplaints, AnyComplaintsInput, PrimiUnder, PrimiAbove, AgeAbove, PregnanceAfter, Infertility, FifthPregancy, HeightD,
                SystemicDisease, ThreatenedAbortion, GestationExceeding, PrematureLabour, Apa, Abnormalpresentation, IUGR, Multiplepregnancy, Hydraminos, PIH, ServerAnaemia, CardiacDisease, GDMonOHA, GDMoninsulin,temperature,
                Pulse,rr,Fhr,Engagement,lie,finalGestationalAge,usgAge,Cervicaldilation,Cervicaleffacement,Membrane,Clrfluid,Station,Pelvisadequate,LabExam,USGExam,otherExam, comboteim2,  comboteim2In,  comboteim3,  comboteim3In ,
                medicalHis, anemiaPre,  obstructedlabour,  aphpph,  congenitalAnomaly,  othesPreRisk,  IfCaesarean,  stDose2,  stDoseIn,  ndDose2,  ndDoseIn,  booster,  boosterIn,  trimester1,  trimester2,  trimester3,  shortgravida,  teenagePrimi,  pregnancyInfertility,  gth,  elderlyGravida,  grandmulti,  gawks,  anemiaCur,  chronicDisease,  othesCurRisk);
    }
}
