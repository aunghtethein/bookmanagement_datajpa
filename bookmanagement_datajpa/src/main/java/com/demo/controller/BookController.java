package com.demo.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.ds.Book;
import com.demo.service.BookService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;


@Controller
public class BookController {
	@Autowired
	private BookService bookService;

	@ModelAttribute("book")
	public Book getBook() {
		return new Book();
	}

	@GetMapping("/")
	public String index(Model model) {
		List<Book> list = bookService.getAllBooks();
		model.addAttribute("list", list);
		return "index";
	}

	@GetMapping("/excel")
	public void createExcel(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
		
		ServletContext context = request.getServletContext();
		List<Book> list = bookService.getAllBooks();// Parameters for repor
		
		Map parameters = new HashMap();
		parameters.put("book", "Book List");
		try {
		JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(list);
		JasperReport jasperReport = JasperCompileManager.compileReport(context.getRealPath("/Book.jrxml"));
		JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, source);
	
		
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=book.pdf");
		JRPdfExporter exporterPdf = new JRPdfExporter();
		exporterPdf.setParameter(JRPdfExporterParameter.JASPER_PRINT, print);
		exporterPdf.setParameter(JRPdfExporterParameter.OUTPUT_STREAM,
		response.getOutputStream());
		exporterPdf.exportReport();
		
		} catch (JRException e) {
		e.printStackTrace();
		}
		
		
		}
	

	@GetMapping("/generatePdf")
	public void generatePdf(HttpServletResponse response) throws SQLException, JRException, IOException {
		response.setContentType("application/x-download");
		response.setHeader("Content-Disposition", 
				String.format("attachment; filename=book.pdf"));
		OutputStream out = response.getOutputStream();
		bookService.exportwithPdf(out);
	}
	
	@GetMapping("/generateExcel")
	public void generateExcel(HttpServletResponse response) throws SQLException, JRException, IOException {
		response.setContentType("application/x-download");
		response.setHeader("Content-Disposition", 
				String.format("attachment; filename=book.xlsx"));
		OutputStream out = response.getOutputStream();
		bookService.exportwithExcel(out);
	}
	
	@GetMapping("/setupAddbook")
	public String setupBook(Model model, Book book) {
		model.addAttribute("book", new Book());
		return "addbook";
	}

	
	@PostMapping("/addBook")
	public String addBook(@ModelAttribute("book") @Valid Book book, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "addbook";
		}
		Book b = new Book();
		b.setCode(book.getCode());
		b.setTitle(book.getTitle());
		b.setAuthor(book.getAuthor());
		b.setPrice(Double.valueOf(book.getPrice()));
		bookService.save(b);
		return "redirect:/";

	}

	@GetMapping("/setupUpdateBook")
	public String setupUpdateBook(@RequestParam("code") String code, Model model) {
		model.addAttribute("book", bookService.findByBookCode(code));
		return "updatebook";

	}

	@PostMapping("/updateBook")
	public String updateBook(@ModelAttribute("book") @Valid Book book, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "updatebook";
		}
		bookService.save(book);
		return "redirect:/";
	}

	@GetMapping("/deleteBook")
	public String deleteBook(@RequestParam String code, Model model) {
		Book book = bookService.findByBookCode(code);
		bookService.delete(book);
		return "redirect:/";
	}
}
