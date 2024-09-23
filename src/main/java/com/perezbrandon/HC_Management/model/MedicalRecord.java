package com.perezbrandon.HC_Management.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name="medical_records")
public class MedicalRecord {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    private Integer id;

    @Column(nullable = false)
    private Integer patient_id;

    @Column(nullable = false)
    private Integer doctor_id;

    @Column(nullable = false)
    private Integer appointment_id;

    @Column(nullable = false)
    private String diagnosis;

    private String treatment;
    private Timestamp created_at;
    private Timestamp updated_at;

    public MedicalRecord() {

    }

    public MedicalRecord(Integer id, Integer patient_id, Integer doctor_id, Integer appointment_id, String diagnosis, String treatment, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.appointment_id = appointment_id;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public Integer getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Integer getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(Integer appointment_id) {
        this.appointment_id = appointment_id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "id=" + id +
                ", patient_id=" + patient_id +
                ", doctor_id=" + doctor_id +
                ", appointment_id=" + appointment_id +
                ", diagnosis='" + diagnosis + '\'' +
                ", treatment='" + treatment + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
