/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Message;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author Trung Quan
 */
public class SmsUtil {

    private static final String Account_SID = "AC722e8b2253b7cf61322f7eec6d4cadff";
    private static final String Account_Token = "142297740ef4a160479f4ebf478a9924";
    private static final String SMS_Center = "+13159097456";
    private static final String Status_Callback_URL = "http://localhost:9999/SE1709_G3_FLM/view/userAccess/profile.jsp";
    private static final TwilioRestClient client;
    
    static {
        client = new TwilioRestClient(Account_SID, Account_Token);
    }
    public static void sendMessage(String toNumber, String code) throws TwilioRestException{
        List<NameValuePair> params = new ArrayList<NameValuePair>(3);
        params.add(new BasicNameValuePair("Body", code));
        params.add(new BasicNameValuePair("To", toNumber));
        params.add(new BasicNameValuePair("From", SMS_Center));
        params.add(new BasicNameValuePair("StatusCallBack", Status_Callback_URL));
        
        client.getAccount().getMessageFactory().create(params);
    }
}
