package com.skilldistillery.medicaltracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.medicaltracker.entities.MedicalHistory;
import com.skilldistillery.medicaltracker.entities.Medication;
import com.skilldistillery.medicaltracker.entities.Patient;
import com.skilldistillery.medicaltracker.entities.Provider;
import com.skilldistillery.medicaltracker.entities.User;
import com.skilldistillery.medicaltracker.repositories.PatientRespository;

@Service
public class PatientServiceImpl implements PatientService {
	@Autowired
	private PatientRespository patRepo; 

	@Override
	public Patient createPatient(Patient patient, User user) {
		Patient patToAdd = patient;
		patToAdd.setUser(user);
		patRepo.saveAndFlush(patToAdd);
		return patToAdd;
	}
	@Override
	public Patient update(Patient patient, Integer id) {
		Optional<Patient> patOpt = patRepo.findById(id);
		Patient dbPat = patOpt.get();
		if(dbPat != null) {
			if(patient.getDob() != null) dbPat.setDob(patient.getDob());
			if(patient.getFname() != null) dbPat.setFname(patient.getFname());
			if(patient.getLname() != null) dbPat.setLname(patient.getLname());
			if(patient.getImg() != null) dbPat.setImg(patient.getImg());
			patRepo.flush();
		}
		return dbPat;
	}
	@Override
	public void addProviderToPatient(Provider provider, Integer patId) {
		Optional<Patient> patOpt = patRepo.findById(patId);
		Patient dbPat = patOpt.get();
		if(dbPat != null) {
			dbPat.addProvider(provider);
			patRepo.flush();
		}
	}
	@Override
	public Patient showPat(String username, int patId) {
		Patient pat = patRepo.findByUser_UsernameAndId(username, patId);
		return pat;
	}
	
	@Override
	public List<Patient> showAllPatients(){
		return patRepo.findAll();
	}
	
	@Override
	public List<Medication> listMeds(Integer patId) {
		Optional<Patient> patOpt = patRepo.findById(patId);
		Patient dbPat = patOpt.get();
		if (dbPat != null) {
			return dbPat.getMedications();
		}
		return null;
	}
	@Override
	public List<MedicalHistory> listMedHist(Integer patId) {
		Optional<Patient> patOpt = patRepo.findById(patId);
		Patient dbPat = patOpt.get();
		if (dbPat != null) {
			return dbPat.getMedHis();
		}
		return null;
	}
	
}