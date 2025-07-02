package com.druginventoryservice.service;

import com.druginventoryservice.entity.Drug;

import java.util.List;

public interface DrugService {

    public Drug addDrugs(Drug drug);

    public List<Drug> getAllDrugs();

    public Drug updateDrug(Drug drug, String drugName);

    public void deleteDrug(String drugName);
}
