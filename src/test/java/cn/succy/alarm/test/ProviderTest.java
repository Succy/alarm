package cn.succy.alarm.test;

import cn.succy.alarm.provider.ContactProvider;
import cn.succy.alarm.provider.ContactProviderFactory;
import cn.succy.alarm.receiver.Receiver;
import org.junit.Test;

import java.util.Set;

/**
 * @author Succy
 * @date 2017-10-13 20:50
 **/

public class ProviderTest {
    @Test
    public void testJsonProvider() {

        ContactProvider provider = ContactProviderFactory.getContactProvider();
        Receiver receiver = provider.getReceiver();
        Set<String> emailSet = receiver.getEmailSet();
        Set<String> phoneSet = receiver.getPhoneSet();

        Set<String> weChatSet = receiver.getWeChatSet();
        System.out.println(emailSet);
        System.out.println(phoneSet);
        System.out.println(weChatSet);
    }
}
