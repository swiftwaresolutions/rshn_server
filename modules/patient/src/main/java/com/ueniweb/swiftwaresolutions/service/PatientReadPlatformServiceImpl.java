package com.ueniweb.swiftwaresolutions.service;

import com.ueniweb.swiftwaresolutions.core.services.PaginationHelper;
import com.ueniweb.swiftwaresolutions.data.PatientData;
import com.ueniweb.swiftwaresolutions.data.PatientIpData;
import com.ueniweb.swiftwaresolutions.rowmapper.IpPatientsRowMapper;
import com.ueniweb.swiftwaresolutions.rowmapper.PatientRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientReadPlatformServiceImpl implements PatientReadPlatformService {

    private final JdbcTemplate jdbcTemplate;

    private final PaginationHelper<PatientData> patientDataPaginationHelper = new PaginationHelper<>();

    public List<PatientData> fetchAllPatientData() {
        try {
            final PatientRowMapper patientRowMapper = new PatientRowMapper();
            String whereCondition = " WHERE c.is_cardin_rack = 0 AND c.is_in_ip=0 AND b.date = CURRENT_DATE AND c.is_blocked=0 AND c.loc_id = 1 " +
                    "GROUP BY DATE(b.date),c.display_number,b.id\n" +
                    "ORDER BY (CASE WHEN d.dept_id = '' THEN 0 ELSE 1 END),(CASE WHEN u.id = '' THEN 0 ELSE 1 END),d.name,b.token_no_doctor \n";
            //String qry = "SELECT " + patientRowMapper.schema() + whereCondition + " LIMIT ? OFFSET ? ";
            String qry = "\n SELECT " + patientRowMapper.schema() + whereCondition;
            System.out.print(qry);
            String countQry = "SELECT count(*)  " +
                    "    FROM rec_patient_details p  INNER JOIN rec_patient_opvisits b ON p.pat_id = b.pat_id  INNER JOIN rec_patient c ON b.pat_id = c.id   WHERE \n" +
                    "    c.is_cardin_rack = 0 \n" +
                    "    AND b.date BETWEEN CURRENT_DATE - 2 AND CURRENT_DATE \n" +
                    "    AND c.loc_id = 1  ;";
            //return this.patientDataPaginationHelper.fetchPage(this.jdbcTemplate, countQry, qry, patientRowMapper, pageSize, page)
            return this.jdbcTemplate.query(qry, patientRowMapper);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<PatientIpData> fetchAllIpPatientData() {
        try {
            final IpPatientsRowMapper ipPatientsRowMapper = new IpPatientsRowMapper();
            String whereCondition = " a1.pat_id = a2.pat_id AND a2.id = a3.opvisit_id AND a3.id = a4.ip_id AND \n" +
                   "  a4.rb_id = a6.id AND a6.ward_id = a5.id AND a4.is_dis = 0 AND a3.is_dis = 0";
            //     "  a4.rb_id = a6.id AND a6.ward_id = a5.id AND a3.date >=NOW()-INTERVAL 15 DAY";
            String qry = " SELECT " + ipPatientsRowMapper.schema() + "FROM " + ipPatientsRowMapper.tableSchema() + "WHERE " + whereCondition;
            return this.jdbcTemplate.query(qry, ipPatientsRowMapper);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
