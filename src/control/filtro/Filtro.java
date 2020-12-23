package control.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import model.utente.UtenteBean;

@WebFilter(filterName = "Filter", urlPatterns = { "/titolare/*", "/cliente/*", "/gestore/*" })

public class Filtro implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpServletResponse hResponse = (HttpServletResponse) response;
		System.out.println("la sessione è valida?" + hRequest.isRequestedSessionIdValid());
		HttpSession session = hRequest.getSession(true);// Prendo la sessione, ma non ne creo un'altra se è invalida

		System.out.println("Sto filtrando la richiesta");
		String requestURI = hRequest.getRequestURI();

		if (requestURI.contains("/titolare/")) {
			System.out.println("il path contiene *titolare*");
			checkAccess(session, request, response, chain, hResponse, hRequest, "titolare");
		} else if (requestURI.contains("/cliente/")) {
			System.out.println("il path contiene *cliente*");
			checkAccess(session, request, response, chain, hResponse, hRequest, "cliente");
		} else if (requestURI.contains("/gestore/")) {
			System.out.println("il path contiene *gestore*");
			checkAccess(session, request, response, chain, hResponse, hRequest, "gestore");

		}
		
		
	}

	public void checkAccess(HttpSession session, ServletRequest sRequest, ServletResponse sResponse, FilterChain chain,
			HttpServletResponse hResponse, HttpServletRequest hRequest, String tipoUtente)
			throws IOException, ServletException {

		if (session != null) {
			System.out.println("la sessione va bene");
			UtenteBean user = (UtenteBean) session.getAttribute("utente");
			if (user != null && user.getRuolo().name().equals(tipoUtente))
				chain.doFilter(sRequest, sResponse);
			else if (user == null)
				hResponse.sendRedirect(hResponse.encodeRedirectURL(hRequest.getContextPath() + "/login.jsp"));
			else
				hResponse.sendRedirect(hResponse.encodeRedirectURL(hRequest.getContextPath() + "/index.jsp"));
		}
	}

}
