package de.hsaugsburg.oosd.artx.services;

import de.hsaugsburg.oosd.artx.models.Product;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;

@Service
public class EmailService {

    /**
     * Static Strings needed for creating the invoice
     */

    private final static String ART_X_MAIL_ADDRESS = "art.artx@gmx.de";

    private final static String MAIL_SUBJECT = "ArtX - Vielen Dank für Deinen Einkauf";

    private final static String MAIL_START = "Lieber Kunde,\n\nvielen Dank für Deinen Einkauf. Anbei erhältst Du Deine Rechnung:\n\n";

    private final static String MAIL_END = "\n\nMit freundlichen Grüßen\n\nDein ArtX-Team";

    private final static String PAYMENT_DETAILS = "\n\nEmpfänger: ArtX\nIBAN: 123456789\nBankinstitut: BankX";

    private String invoice;

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, Collection<Product> boughtItems) {
        SimpleMailMessage message = new SimpleMailMessage();

        createInvoice(boughtItems);

        message.setFrom(ART_X_MAIL_ADDRESS);
        message.setTo(to);
        message.setSubject(MAIL_SUBJECT);
        message.setText(MAIL_START + invoice + PAYMENT_DETAILS + MAIL_END);

        mailSender.send(message);
    }

    private void createInvoice(Collection<Product> boughtItems) {
        invoice = "";
        BigDecimal invoiceSum = new BigDecimal(0);

        for (Product item : boughtItems) {
            invoice += "" + item.getName() + "\n" + item.getPrice() + " €\n\n";
            invoiceSum = invoiceSum.add(item.getPrice());
        }

        invoice += "Gesamt: " + invoiceSum + " €";
    }
}
