package com.skilldistillery.medicaltracker.services;

import java.util.List;

import com.skilldistillery.medicaltracker.entities.MedicalHistory;
import com.skilldistillery.medicaltracker.entities.Medication;
import com.skilldistillery.medicaltracker.entities.Patient;
import com.skilldistillery.medicaltracker.entities.User;

public interface UserService {

	List<User> getAllUsers();
	
	User findById(int id);

	User createUser(User user);
	
	User updateUser (int id, User user);
	
	Patient getUserPatient(String username);

	List<Medication> getUserPatientMeds(String username);

	List<MedicalHistory> getUserPatientMedHis(String username);

	User getUserByUsername(String username);
	
	
}
