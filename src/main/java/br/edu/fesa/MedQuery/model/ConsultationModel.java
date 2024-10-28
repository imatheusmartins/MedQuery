package br.edu.fesa.MedQuery.model;

import java.sql.Date;
import java.util.UUID;

import br.edu.fesa.MedQuery.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TB_CONSULTATIONS", schema = "MEDQUERY")
public class ConsultationModel {
    
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "PATIENT_ID", nullable = false, unique = true)
    private int patientId;

    @Column(name = "DOCTOR_ID", nullable = false, unique = false)
    private int doctor_id;

    @Column(name = "UNITY_ID", nullable = false, unique = false)
    private int unity_id;
    
    @Column(name = "REPORT", nullable = false, unique = true, length = 250)
    private String report;

    
    public ConsultationModel(int patientId, int doctor_id, int unity_id){
        this.patientId = patientId;
        this.doctor_id = doctor_id;
        this.unity_id = unity_id;
    }

    public int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getDoctorId() {
        return doctor_id;
    }

    public int getUnityId() {
        return unity_id;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report){
        this.report = report;
    }
}
