package com.perezbrandon.HC_Management.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name="appointments")
public class Appointment {
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
    private Date appointment_date;

    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    private Timestamp created_at;
    private Timestamp updated_at;


    public Appointment() {

    }

    public Appointment(Integer patient_id, Integer doctor_id, Date appointment_date, String reason, Status status, Timestamp created_at, Timestamp updated_at) {
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.appointment_date = appointment_date;
        this.reason = reason;
        this.status = status;
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

    public Date getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(Date appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        return "Appointment{" +
                "id=" + id +
                ", patient_id=" + patient_id +
                ", doctor_id=" + doctor_id +
                ", appointment_date=" + appointment_date +
                ", reason='" + reason + '\'' +
                ", status=" + status +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
