package com.musicshop.order;

import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
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
	private JavaMailSender javaMailSender;

	@Autowired
	public OrderController(ShoppingCart shoppingCart, JavaMailSender javaMailSender, InstrumentDao instrumentDao) {
		this.shoppingCart = shoppingCart;
		this.javaMailSender = javaMailSender;
		this.instrumentDao=instrumentDao;
	}

	@RequestMapping()
	public String makeOrder() throws MessagingException {

		List<Instrument> instruments=instrumentDao.readByIds(new ArrayList<Integer>(shoppingCart.getItems().keySet()));
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
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
		javaMailSender.send(mimeMessage);

		return "redirect:/";
	}
}
