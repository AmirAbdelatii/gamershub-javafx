/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamershub.APIs;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import gamershub.Entities.Tournaments;
/**
 *
 * @author Ghofrane
 */
public class sendSMS {
    public static final String ACCOUNT_SID = System.getenv("AC20c5b47fc96bff945d5a3700652cbbb6");
    public static final String AUTH_TOKEN = System.getenv("db2dffee4c9b319d656e11db15751a0d");

    public static void sendSMS(Tournaments t) {
        Twilio.init("AC20c5b47fc96bff945d5a3700652cbbb6", "db2dffee4c9b319d656e11db15751a0d");
        Message message = Message.creator(new PhoneNumber("+21628223273"),
        new PhoneNumber("+14454466883"),"Name: "+t.getName()+" Description: "+t.getDecription()).create();
       

        System.out.println(message.getSid());
    }
    
}
