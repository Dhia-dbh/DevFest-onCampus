package phone;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class Messagerie {
	
    public static final String ACCOUNT_SID = "AC19929b74b758ff455a73620f21a82902";
    public static final String AUTH_TOKEN = "7f90d54eea53b06d35ea7360a2b6a84e";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21654114908"),
                new com.twilio.type.PhoneNumber("+12058989175"),
                "go to a safe place")
            .create();

        System.out.println(message.getSid());
    }
}
