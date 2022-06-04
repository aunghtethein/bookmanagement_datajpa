package com.demo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import com.demo.ds.Book;
import com.demo.repo.BookRepo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepo repo;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public void save(Book book) {
		repo.save(book);
	}

	@Override
	public void delete(Book book) {
		repo.delete(book);
	}

	@Override
	public List<Book> getAllBooks() {
		return repo.findAll();
	}

	@Override
	public Book findByBookCode(String code) {
		return repo.findById(code)
				.orElseThrow( () -> new IllegalArgumentException("INVALID CODE" + code));
	}

	@Override
	public void exportwithPdf(OutputStream out) throws SQLException, FileNotFoundException, JRException {
		try {
			Connection con = jdbcTemplate.getDataSource().getConnection();
			File file = ResourceUtils.getFile("classpath:Book.jrxml");
			JasperReport jasperReport = 
					JasperCompileManager.compileReport(file.getAbsolutePath());
			
			Map<String,Object> parameters = new HashMap<>();
			parameters.put("ReportTitle", "Book List");
			
			JasperPrint jasperPrint = 
					JasperFillManager.fillReport(jasperReport, parameters, con);
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
			exporter.exportReport();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void exportwithExcel(OutputStream out) throws SQLException, FileNotFoundException, JRException {
		try {
			Connection con = jdbcTemplate.getDataSource().getConnection();
			File file = ResourceUtils.getFile("classpath:Book.jrxml");
			JasperReport jasperReport = 
					JasperCompileManager.compileReport(file.getAbsolutePath());
			
			Map<String,Object> parameters = new HashMap<>();
			parameters.put("ReportTitle", "Book List");
			
			JasperPrint jasperPrint = 
					JasperFillManager.fillReport(jasperReport, parameters, con);
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
			exporter.exportReport();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
}
