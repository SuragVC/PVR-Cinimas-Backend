package com.pvr.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.pvr.entity.MovieTicket;
import com.pvr.entity.Theater;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.util.Base64Utils;
import org.springframework.util.Base64Utils;

@Component
public class TwilioSMSService {
	String SID = base64Decoder("QUM1OTJkODVjN2RmMzkyNDZhNmRiNGU4ZmM4NjFmZjgyNw==");
	String token = base64Decoder("MjFiOGUxMTRmNzQwN2JlZTgyOGZiMTRhNWI1ODM3N2Y=");
	String twilioNo = base64Decoder("MDEyMTc4MDE5");

	public boolean confirmationSMSSender(MovieTicket ticket, Theater theater) {
		Twilio.init(SID, token);
		try {
			Message message = Message.creator(
					new com.twilio.type.PhoneNumber("+91" + ticket.getMobileNo()),
					new com.twilio.type.PhoneNumber("+14" + twilioNo),
					"Ticket Booking Confirmed! Ticket ID : " + ticket.getMovieTicketID() + ", Theater Name : "
							+ ticket.getTheaterName() + ", Movie Name : " + theater.getRunningMovie()
							+ ", Ticket Count : " + ticket.getTicketCount() +" Total Payment : "+ticket.getPayment().getTotalPayment()+ " Happy Watching")
					.create();

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	public boolean cancelSMSSender(MovieTicket ticket) {
		Twilio.init(SID, token);
		try {
			Message message = Message.creator(
					new com.twilio.type.PhoneNumber("+91" + ticket.getMobileNo()),
					new com.twilio.type.PhoneNumber("+14" + twilioNo),
					"Ticket Cancel Confirmed! Ticket ID : " + ticket.getMovieTicketID() + " is canceled!"+ticket.getPayment().getTotalPayment()+" will be refund to your account"+" Thank you")
					.create();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	public String base64Decoder(String encoded) {
		byte[] decodedBytes = Base64Utils.decode(encoded.getBytes());
		String decoded = new String(decodedBytes);
		return decoded;
	}

}
