package com.skilldistillery.medicaltracker.services;

import java.util.List;

import com.skilldistillery.medicaltracker.entities.MedicalHistory;
import com.skilldistillery.medicaltracker.entities.Medication;
import com.skilldistillery.medicaltracker.entities.Patient;
import com.skilldistillery.medicaltracker.entities.Provider;

public interface PatientService {

	Patient update(Patient patient, Integer id);

	Patient createPatient(Patient patient, String username);


	Patient showPat(String username, int patId);

	List<Medication> listMeds(Integer patId);
	List<MedicalHistory> listMedHist(Integer patId);

	List<Patient> showAllPatients();
	Boolean removeProvider(int id, String username);

	Boolean addProviderToPatient(int id, String username);

	Patient findPatientById(int id);

	List<Provider> listProviders(String username);


}
