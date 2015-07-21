package br.com.alura.gerenciador.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/logout")
public class Logout extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		Cookie userLoggedIn = new Cookies(req.getCookies()).getUsuarioLogado();
		if(userLoggedIn == null) {
			System.out.println("Usuário já estava deslogado");
			writer.println("<html><body>Usuário deslogado</body></html>");
			return;
		} else {
			userLoggedIn.setMaxAge(0);
			writer.println("<html><body>Usuário " + userLoggedIn.getName() + " deslogado </body></html>");
			System.out.println("Usuário " + userLoggedIn.getName() + " deslogado"); 
			resp.addCookie(userLoggedIn);
		}
	}
}
