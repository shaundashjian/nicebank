package com.shaundashjian.nicebank;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WithdrawalServlet extends HttpServlet {
	
	private CashSlot cashSlot;
	private Account account;
	private Teller teller;
	
	public WithdrawalServlet(CashSlot cashSlot, Account account) {
		this.cashSlot = cashSlot;
		this.account = account;
	}

	protected void doPost(HttpServletRequest request,
						HttpServletResponse response)
						throws ServletException, IOException {
		teller = new AutomatedTeller(cashSlot);
		int amount = Integer.parseInt(request.getParameter("amount"));
		teller.withdrawFrom(account, amount);
		
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		if (account.getDisplay().equals("")) {
			
		response.getWriter().println("<html><head><title>Nice Bank ATM</title></head>"
				+ "<body>Please take your $" + cashSlot.getContents() + "</body></html>");
		} else {
			response.getWriter().println("<html><head><title>Nice Bank ATM</title></head>"
					+ "<body>" + account.getDisplay() + "</body></html>");
			
		}
	}
}