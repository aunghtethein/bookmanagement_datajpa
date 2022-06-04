package com.demo.service;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import com.demo.ds.Book;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface BookService {
	
	void save(Book book);
	void delete(Book book);
	List<Book> getAllBooks();
	Book findByBookCode(String code);
	void exportwithPdf(OutputStream out) throws SQLException,FileNotFoundException,JRException;
	void exportwithExcel(OutputStream out) throws SQLException,FileNotFoundException,JRException;

}
