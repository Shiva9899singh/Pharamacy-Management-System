package com.druginventoryservice.controller;

import com.druginventoryservice.entity.Drug;
import com.druginventoryservice.service.DrugService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drugs")
@Tag(name = "Drug APIs")
public class DrugController {

    @Autowired
    private DrugService drugService;

    @PostMapping("/add")
    public ResponseEntity<Drug> addDrugs(@Valid @RequestBody Drug drug) {
        Drug newDrugs = this.drugService.addDrugs(drug);
        return new ResponseEntity<>(newDrugs, HttpStatus.CREATED);
    }

    @GetMapping("/fetchDrugs")
    public ResponseEntity<List<Drug>> fetchAllDrugs() {
        List<Drug> allDrugs = this.drugService.getAllDrugs();
        return ResponseEntity.ok(allDrugs);
    }
    @GetMapping("/getByName/{name}")
    public ResponseEntity<Drug> getByName(@PathVariable String name) {
        return ResponseEntity.ok(drugService.getDrugByName(name));
    }

    @PutMapping("/updateDrugs/{drugName}")
    public ResponseEntity<Drug> updateDrugDetails(@Valid @RequestBody Drug drug, @PathVariable("drugName") String drugName) {
        Drug updatedDrug = this.drugService.updateDrug(drug, drugName);
        return new ResponseEntity<>(updatedDrug, HttpStatus.OK);
    }

    @DeleteMapping("/deleteDrugs/{drugName}")
    public ResponseEntity<String> deleteDrug(@PathVariable("drugName") String drugName) {
        this.drugService.deleteDrug(drugName);
        String message = "Drug with name '" + drugName + "' has been successfully deleted.";
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @PutMapping("/updateQuantity/{name}")
    public ResponseEntity<String> updateQuantity(@PathVariable String name, @RequestParam long quantityToReduce) {
        return ResponseEntity.ok(drugService.updateQuantityOrDelete(name, quantityToReduce));
    }
}
