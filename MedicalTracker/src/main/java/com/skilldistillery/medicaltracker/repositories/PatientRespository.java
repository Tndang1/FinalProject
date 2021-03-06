package com.skilldistillery.medicaltracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.medicaltracker.entities.Patient;

public interface PatientRespository extends JpaRepository<Patient, Integer>{
	Patient findByUser_UsernameAndId(String username, int id);
}
