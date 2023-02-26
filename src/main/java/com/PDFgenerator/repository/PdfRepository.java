package com.PDFgenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PDFgenerator.model.PdfEntity;

@Repository
public interface PdfRepository extends JpaRepository<PdfEntity, Long>{

}

