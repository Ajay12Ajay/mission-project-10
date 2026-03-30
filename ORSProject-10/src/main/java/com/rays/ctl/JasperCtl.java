package com.rays.ctl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.MarksheetDTO;
import com.rays.form.MarksheetForm;
import com.rays.service.MarksheetServiceInt;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 * REST controller for generating JasperReports.
 * Compiles {@code project_10.jrxml}, fills it using the active JDBC connection,
 * and streams the resulting PDF to the HTTP response.
 * Mapped to {@code /Jasper}.
 *
 * @author Ajay Pratap Kerketta
 */
@RestController
@RequestMapping(value = "Jasper")
public class JasperCtl extends BaseCtl<MarksheetDTO, MarksheetForm, MarksheetServiceInt> {

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Generates and streams the Marksheet Merit List as a PDF.
	 * Compiles the JRXML template, fills it via the Hibernate JDBC connection,
	 * and writes the PDF bytes directly to the response output stream.
	 *
	 * @param request  the incoming HTTP request
	 * @param response the HTTP response; content type is set to {@code application/pdf}
	 * @throws JRException  if JasperReports compilation or filling fails
	 * @throws SQLException if a database connection error occurs
	 * @throws IOException  if writing to the response output stream fails
	 */
	@GetMapping(value = "/report", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void display(HttpServletRequest request, HttpServletResponse response)
			throws JRException, SQLException, IOException {

		System.out.println("*** Jasper Ctl ***");		

		Connection con = null;

		java.io.InputStream jrxmlStream = getClass().getClassLoader().getResourceAsStream("project_10.jrxml");

		JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);

		Map<String, Object> map = new HashMap<String, Object>();

		Session session = (Session) entityManager.unwrap(Session.class);

		con = ((SessionImpl) session).connection();

//      Report fill karo
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, con);

//      PDF generate karo
		byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);

//      Browser me show karo
		response.setContentType("application/pdf");

		response.getOutputStream().write(pdf);

		response.getOutputStream().flush();

		
	}
}