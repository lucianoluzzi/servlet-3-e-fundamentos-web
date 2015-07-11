package br.com.alura.gerenciador.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.Empresa;
import br.com.alura.gerenciador.dao.EmpresaDAO;

@WebServlet(urlPatterns = "/busca")
public class BuscaEmpresa extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		String selectFilter = req.getParameter("filtro");
		writeHtmlAndBody(writer, false);
		writer.println("Resultado da busca: </br>");
		writeEmpresas(writer, selectFilter);
		writeHtmlAndBody(writer, true);
	}
	
	private void writeHtmlAndBody(PrintWriter printWriter, boolean writeEnd) {
		if(!writeEnd) {
			printWriter.println("<html>");
			printWriter.println("<body>");
		} else {
			printWriter.println("</html>");
			printWriter.println("</body>");
		}
	}
	
	private void writeEmpresas(PrintWriter printWriter, String filter) {
		Collection<Empresa> empresas = new EmpresaDAO().buscaPorSimilaridade(filter);
		printWriter.println("<ul>");
		for (Empresa empresa : empresas) {
			printWriter.println("<li>" + empresa.getId() + ": " + empresa.getNome() + "</li>");
		}
		printWriter.println("</ul>");
	}
}
