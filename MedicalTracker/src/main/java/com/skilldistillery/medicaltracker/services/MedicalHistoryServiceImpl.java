package com.skilldistillery.medicaltracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.medicaltracker.entities.MedicalHistory;
import com.skilldistillery.medicaltracker.entities.Medication;
import com.skilldistillery.medicaltracker.repositories.MedicalHistoryRepository;
import com.skilldistillery.medicaltracker.repositories.MedicationRepository;

@Service
public class MedicalHistoryServiceImpl implements MedicalHistoryService {
	@Autowired
	private MedicalHistoryRepository repo;
	@Autowired
	private MedicationRepository rxRepo;

	@Override
	public List<MedicalHistory> index() {
		return repo.findAll();
	}

	@Override
	public MedicalHistory findById(int medHisId) {
		Optional<MedicalHistory> medOpt = repo.findById(medHisId);
		MedicalHistory medHis = null;
		if (medOpt.isPresent()) {
			medHis = medOpt.get();
		}
		return medHis;
	}
	@Override
	public List<Medication> findHistoryMedsById(int medHisId) {
		Optional<MedicalHistory> medOpt = repo.findById(medHisId);
		MedicalHistory medHis = null;
		List<Medication> meds = null;
		if (medOpt.isPresent()) {
			medHis = medOpt.get();
			meds = medHis.getMedications();
		}
		return meds;
	}

	@Override
	public List<Medication> addMedToHist(int medHistId, int medId) {
		Optional<MedicalHistory> medHistOpt = repo.findById(medHistId);
		Optional<Medication> medOpt = rxRepo.findById(medId);
		List<Medication> meds = null;
		MedicalHistory hist = null;
		if (medOpt.isPresent() && medHistOpt.isPresent()) {
			Medication rx = medOpt.get();
			hist = medHistOpt.get();
			meds = hist.getMedications();
			rx.setMedHis(hist);
			meds.add(rx);
			hist.setMedications(meds);
			repo.saveAndFlush(hist);
		}
		return meds;
	}


	@Override
	public MedicalHistory createNewMedicalHistory(MedicalHistory medHis) {
		repo.saveAndFlush(medHis);
		return medHis;
	}

	@Override
	public MedicalHistory updateMedicalHistory(int medId, MedicalHistory medHis) {
		Optional<MedicalHistory> medOpt = repo.findById(medId);
		MedicalHistory managedMed = null;
		if (medOpt.isPresent()) {
			managedMed = medOpt.get();
			if (medHis.getDiagnosis() != null) {
				managedMed.setDiagnosis(medHis.getDiagnosis());
			}
			if (medHis.isActive() != null) {
				managedMed.setActive(medHis.isActive());
			}
			if (medHis.getOnset() != null) {
				managedMed.setOnset(medHis.getOnset());
			}
			if (medHis.getTreatment() != null) {
				managedMed.setTreatment(medHis.getTreatment());
			}
			if (medHis.getPatient() != null) {
				managedMed.setPatient(medHis.getPatient());
			}
			if (medHis.getMedications() != null) {
				managedMed.setMedications(medHis.getMedications());
			}
			repo.saveAndFlush(managedMed);
		}

		return managedMed;
	}

	@Override
	public boolean deleteMedicalHistory(int medId) {
		boolean deleted = false;
		Optional<MedicalHistory> medOpt = repo.findById(medId);
		if (medOpt.isPresent()) {
			MedicalHistory medHis = medOpt.get();
			if (medHis.getMedications() != null) {
				List<Medication> medList = medHis.getMedications();
				for (Medication medication : medList) {
					Optional<Medication> rxOpt = rxRepo.findById(medication.getId());
					if (rxOpt.isPresent()) {
						Medication med = rxOpt.get();
						med.setMedHis(null);
						rxRepo.saveAndFlush(med);
					}
				}
				}
			repo.deleteById(medId);
			deleted = true;
		}
		return deleted;
	}

}