package com.PDFgenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.PDFgenerator.model.PdfData;
import com.PDFgenerator.model.PdfEntity;
import com.PDFgenerator.repository.PdfRepository;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class PdfGenerationService {

	@Autowired
	PdfRepository pdfRepository;
	
    private final TemplateEngine templateEngine;
    

    public PdfGenerationService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public byte[] generatePdfBytes(PdfData pdfData) throws IOException {
        Context context = new Context();
        context.setVariable("seller", pdfData.getSeller());
        context.setVariable("sellerGstin", pdfData.getSellerGstin());
        context.setVariable("sellerAddress", pdfData.getSellerAddress());
        context.setVariable("buyer", pdfData.getBuyer());
        context.setVariable("buyerGstin", pdfData.getBuyerGstin());
        context.setVariable("buyerAddress", pdfData.getBuyerAddress());
        context.setVariable("items", pdfData.getItems());

        String htmlContent = templateEngine.process("invoice", context);

        ByteArrayOutputStream target = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(target);
        PdfDocument pdf = new PdfDocument(writer);
        ConverterProperties converterProperties = new ConverterProperties();
        HtmlConverter.convertToPdf(htmlContent, pdf, converterProperties);
        pdf.close();
        return target.toByteArray();
    }
    
    public PdfEntity savePdf(PdfData pdfData) throws IOException {
    	
    	
    	byte[] pdf = generatePdfBytes(pdfData);
    	
    	PdfEntity pdfEntity = new PdfEntity();
    	pdfEntity.setPdf(pdf);;
    	
    	return pdfRepository.save(pdfEntity);

	}
    
    public Optional<PdfEntity> getPdfEntity(Long id) {
    	
    	Optional<PdfEntity> pdfEntity = pdfRepository.findById(id);
		
    	return pdfEntity;
    }
}

