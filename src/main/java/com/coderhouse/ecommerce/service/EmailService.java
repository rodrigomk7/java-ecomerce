package com.coderhouse.ecommerce.service;

import com.coderhouse.ecommerce.config.AppProperties;
import com.coderhouse.ecommerce.model.document.UserDocument;
import com.coderhouse.ecommerce.model.response.ItemResponse;
import com.coderhouse.ecommerce.model.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private AppProperties appProperties;

    public void sendOrderConfirmationEmail(OrderResponse data) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(data.getEmail());
        msg.setBcc(appProperties.getConfigMail());
        msg.setSubject("Ecommerce Order #" + data.getOrderNumber() + " Confirmation");

        var msgInfo = "Thank you for taking an order with us. Here is more information about your order: \n\n" +
                "Order #" + data.getOrderNumber() + "\n" +
                "Products: \n";
        var msgProducts = "";
        for (ItemResponse product : data.getProducts()) {
            msgProducts = msgProducts + product.toString() + "\n";
        }

        msg.setText(msgInfo + msgProducts);
        javaMailSender.send(msg);
    }

    public void sendNewUserEmail(UserDocument data) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(data.getEmail());
        msg.setBcc(appProperties.getConfigMail());
        msg.setSubject("New registration: " + data.getName());

        var msgInfo = "Thank you " + data.getName() + " for creating a new account in the ecommerce!";
        msg.setText(msgInfo);
        javaMailSender.send(msg);
    }
}
