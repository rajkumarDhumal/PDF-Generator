package com.PDFgenerator.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class PdfEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Lob
	private byte[] pdf;
	public PdfEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PdfEntity(Long id, byte[] pdf) {
		super();
		this.id = id;
		this.pdf = pdf;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public byte[] getPdf() {
		return pdf;
	}
	public void setPdf(byte[] pdf) {
		this.pdf = pdf;
	}
	
	
	

}
