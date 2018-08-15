package com.musicshop.mail;

import java.util.Map;
import java.util.Map.Entry;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.musicshop.instrument.Instrument;

@Service
public class MailSendingServiceImpl implements MailSendingService {

	private JavaMailSender javaMailSender;
	private Environment environment;

	@Autowired
	public MailSendingServiceImpl(JavaMailSender javaMailSender, Environment environment) {
		this.javaMailSender = javaMailSender;
		this.environment = environment;
	}

	@Override
	public void sendInstrumentsOrderMail(Map<Instrument, Integer> instrumentAmount) throws MessagingException {
		if (!instrumentAmount.isEmpty()) {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
			String[] receivers = environment.getProperty("order.mail.receivers").split(",");

			String content = "<table border='1'><tr><th>Instrument</th><th>Image</th><th>Amount</th><th>Price</th>";
			for (Entry<Instrument, Integer> e : instrumentAmount.entrySet()) {
				content += "<tr><td>" + e.getKey().getName() + "</td><td><img src='" + e.getKey().getImages().get(0)
						+ "' style='width:150px;height:150px'/></td><td>" + e.getValue() + "</td><td>"
						+ e.getKey().getPrice() * e.getValue() + "</td></tr>";
			}
			content += "</table>";
			helper.setTo(receivers);
			helper.setFrom("ivancemusicshop@gmail.com");
			helper.setSubject("Instruments order list");
			mimeMessage.setContent(content, "text/html");
			javaMailSender.send(mimeMessage);
		}
	}

}
