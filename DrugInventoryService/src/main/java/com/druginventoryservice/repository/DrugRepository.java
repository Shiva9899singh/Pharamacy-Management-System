package com.druginventoryservice.repository;

import com.druginventoryservice.entity.Drug;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DrugRepository extends JpaRepository<Drug,Long> {

    public Optional<Drug> findByDrugName(String drugName);

    @Modifying
    @Transactional
    @Query("DELETE FROM Drug d WHERE d.drugName = :drugName")
    void deleteByDrugName(@Param("drugName") String drugName);

}
