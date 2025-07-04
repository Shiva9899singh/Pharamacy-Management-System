package com.pharmacy.OrderService.client;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pharmacy.OrderService.Dto.DrugResponse;

@FeignClient(name = "drug-inventory-service", url = "http://localhost:8088/drugs")
public interface DrugInventoryClient {

    @GetMapping("/getByName/{drugName}")
    DrugResponse getDrugByName(@PathVariable("drugName") String drugName);

    @PutMapping("/updateQuantity/{drugName}")
    void updateDrugQuantity(@PathVariable("drugName") String drugName,
                             @RequestParam("quantityToReduce") long quantityToReduce);
}