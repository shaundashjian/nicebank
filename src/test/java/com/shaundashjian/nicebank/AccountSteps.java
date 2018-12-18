package com.shaundashjian.nicebank;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import com.shaundashjian.transforms.MoneyConverter;
import cucumber.api.java.en.Then;
import static org.junit.Assert.*;

public class AccountSteps {
	KnowsTheDomain helper;
	
	public AccountSteps(KnowsTheDomain helper) {
		this.helper = helper;
	}
	
	@Given("^I have deposited (\\$\\d+\\.\\d+) in my account$")
	public void iHaveDeposited$InMyAccount(
			@Transform(MoneyConverter.class) Money amount) 
			throws Exception {
		helper.getAccount().deposit(amount);
		assertEquals("Incorrect funds - ", amount,
				helper.getAccount().getBalance());
	}

}