package com.druginventoryservice.service.impl;

import com.druginventoryservice.entity.Drug;
import com.druginventoryservice.exception.DrugCreationException;
import com.druginventoryservice.exception.DrugsNotFoundException;
import com.druginventoryservice.exception.DuplicateDrugException;
import com.druginventoryservice.repository.DrugRepository;
import com.druginventoryservice.service.DrugService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DrugServiceImpl implements DrugService {

    @Autowired
    private DrugRepository drugRepo;

    @Override
    public Drug addDrugs(Drug drug) {

        Optional<Drug> existingDrug = this.drugRepo.findByDrugName(drug.getDrugName());
        if (existingDrug.isPresent()) {
            throw new DuplicateDrugException("Drug name already exists in the database.");
        }

        try {
            LocalDateTime now = LocalDateTime.now();
            drug.setCreatedAt(now);
            drug.setUpdatedAt(now);

            return this.drugRepo.save(drug);
        } catch (Exception e) {
            throw new DrugCreationException("Unexpected error while adding drug: " + drug.getDrugName(), e);
        }

    }

    @Override
    public List<Drug> getAllDrugs() {
        try {
            List<Drug> drugsList = this.drugRepo.findAll();
            if (drugsList.isEmpty()) {
                throw new DrugsNotFoundException("No drugs found in Inventory.");
            }
            return drugsList;
        } catch (Exception e) {
            throw new DrugsNotFoundException("Failed to Fetch Drugs");
        }
    }

    @Override
    public Drug updateDrug(Drug drug, String drugName) {
        try {
            Drug existingDrugName = this.drugRepo.findByDrugName(drugName)
                    .orElseThrow(() ->
                            new DrugsNotFoundException("Drug not found with name: " + drugName));

            drug.setDrugId(existingDrugName.getDrugId());
            drug.setDrugName(drug.getDrugName());
            drug.setDescription(drug.getDescription());
            drug.setManufacturer(drug.getManufacturer());
            drug.setQuantity(drug.getQuantity());
            drug.setPrice(drug.getPrice());
            drug.setCreatedAt(existingDrugName.getCreatedAt());
            drug.setUpdatedAt(LocalDateTime.now());
            return this.drugRepo.save(drug);

        } catch (Exception e) {
            throw new DrugCreationException("Failed to update drug: " + drug.getDrugName(),e);
        }
    }

    @Override
    @Transactional
    public void deleteDrug(String drugName) {
        Drug drug = drugRepo.findByDrugName(drugName)
                .orElseThrow(() -> new DrugsNotFoundException("Drug not found with name: " + drugName));

        try {
            drugRepo.deleteByDrugName(drug.getDrugName());
        } catch (DataAccessException e) {
            throw new DrugCreationException("Failed to delete drug: " + drugName, e);
        }
    }

}
