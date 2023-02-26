package com.PDFgenerator.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PDFgenerator.model.PdfData;
import com.PDFgenerator.model.PdfEntity;
import com.PDFgenerator.repository.PdfRepository;
import com.PDFgenerator.service.PdfGenerationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Optional;




@RestController
@RequestMapping("/pdf")
@Api(value = "PDF")
public class PdfController {

    @Autowired
    private PdfGenerationService pdfGenerationService;


    @PostMapping("/generate/{filename}")
	@ApiOperation(value = "Generate and Download PDF", notes = "Insert Buyers, Sellers and Items information.")
    public ResponseEntity<Resource> generateAndSavePdf(@RequestBody PdfData pdfData, @PathVariable String filename) throws IOException {
        PdfEntity pdfEntity = pdfGenerationService.savePdf(pdfData);
        
        byte[] pdfBytes = pdfEntity.getPdf();
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+filename+".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(pdfBytes.length)
                .body(resource);
        
    }

    @GetMapping("/download/{id}/{filename}")
	@ApiOperation(value = "Download PDF", notes = "insert id(to return pdf) and filename(to assign filename to download file) in URL ")
    public ResponseEntity<Resource> downloadPdf(@PathVariable Long id,  @PathVariable String filename) {
        Optional<PdfEntity> optionalPdf = pdfGenerationService.getPdfEntity(id);
        if (optionalPdf.isPresent()) {
            PdfEntity pdfEntity = optionalPdf.get();
            byte[] pdfBytes = pdfEntity.getPdf();
            ByteArrayResource resource = new ByteArrayResource(pdfBytes);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+filename+".pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .contentLength(pdfBytes.length)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}