package test;

import cn.succy.alarm.Constant;
import cn.succy.alarm.template.TemplateManager;
import cn.succy.alarm.template.TemplateModel;
import cn.succy.mq.consumer.Consumer;
import cn.succy.mq.consumer.QueueConsumer;
import com.xiaoleilu.hutool.lang.Console;
import org.junit.Test;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.io.Serializable;
import java.net.InetAddress;

/**
 * @author Succy
 * @date 2017-11-01 22:20
 **/

public class MqTest {
    //public static void main(String[] args) {
    //    Consumer consumer = new QueueConsumer(Constant.ALAEM_QUEUE_NAME);
    //    consumer.receive(new MessageListener() {
    //        @Override
    //        public void onMessage(Message message) {
    //            ObjectMessage msg = (ObjectMessage) message;
    //            try {
    //                Serializable object = msg.getObject();
    //                TemplateModel model = (TemplateModel) object;
    //                String templateMsg = TemplateManager.getTemplateMsg(model);
    //                System.out.println(templateMsg);
    //            } catch (JMSException e) {
    //                e.printStackTrace();
    //            }
    //        }
    //    });
    //}

    @Test
    public void getIp() throws Exception {
        InetAddress address =  InetAddress.getLocalHost();
        String hostAddress = address.getHostAddress();
        String canonicalHostName = address.getCanonicalHostName();
        String hostName = address.getHostName();

        Console.log("hostAddress: {}; canonicalHostName: {}; hostName: {}", hostAddress, canonicalHostName, hostName);
    }
}
