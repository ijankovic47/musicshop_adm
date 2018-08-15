package com.musicshop.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.musicshop.instrument.Instrument;
import com.musicshop.instrument.InstrumentDao;
import com.musicshop.shoppingcart.ShoppingCart;

@Controller
@RequestMapping("/order")
public class OrderController {

	private ShoppingCart shoppingCart;
	private InstrumentDao instrumentDao;
	private Session session;

	@Autowired
	public OrderController(ShoppingCart shoppingCart, Session session, InstrumentDao instrumentDao) {
		this.shoppingCart = shoppingCart;
		this.session = session;
		this.instrumentDao=instrumentDao;
	}

	@RequestMapping()
	public String makeOrder() throws MessagingException {

		List<Instrument> instruments=readInstrumentsByIds(shoppingCart.getItems().keySet());
		MimeMessage mimeMessage = new MimeMessage(session);
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
		String[] receivers = { "ijankovic47@gmail.com"};

		String content = "<table border='1'><tr><th>Instrument</th><th>Image</th><th>Amount</th><th>Price</th>";
		for (Instrument i: instruments) {
			content += "<tr><td>" + i.getName() + "</td><td><img src='" +i.getImages().get(0)+"' style='width:150px;height:150px'/></td><td>"
		+shoppingCart.getItems().get(i.getId())+"</td><td>"+i.getPrice()*shoppingCart.getItems().get(i.getId())+"</td></tr>";
		}
		content += "</table>";
		helper.setTo(receivers);
		helper.setFrom("ivancemusicshop@gmail.com");
		helper.setSubject("Instruments order list");
		mimeMessage.setContent(content, "text/html");
		Transport.send(mimeMessage);

		return "redirect:/";
	}
	
	private List<Instrument> readInstrumentsByIds(Set<Integer> ids){
		
		List<Instrument> instruments=instrumentDao.readByIds(new ArrayList(ids));
		return instruments;
	}
}
