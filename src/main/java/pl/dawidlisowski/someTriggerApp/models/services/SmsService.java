package pl.dawidlisowski.someTriggerApp.models.services;

import org.springframework.stereotype.Service;
import pl.smsapi.OAuthClient;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.exception.ClientException;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.proxy.ProxyNative;

@Service
public class SmsService {

    public void sendSms(String phone, String message) {
        try {
            OAuthClient client = new OAuthClient("TOKEN SMS");
            SmsFactory smsApi = new SmsFactory(client, new ProxyNative("http://api.smsapi.com/"));
            SMSSend action = smsApi.actionSend()
                    .setText(message)
                    .setTo(phone);
            action.execute();
        } catch (SmsapiException e) {
            throw new RuntimeException(e);
        }
    }
}
