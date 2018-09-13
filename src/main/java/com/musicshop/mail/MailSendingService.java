package com.musicshop.mail;

import java.net.URISyntaxException;
import java.util.Map;

import javax.mail.MessagingException;

import com.musicshop.instrument.Instrument;

public interface MailSendingService {

	void sendInstrumentsOrderMail(Map<Instrument, Integer> instrumentAmount) throws MessagingException;
	void sendEmailConfirmation(String email) throws MessagingException, URISyntaxException;
}
