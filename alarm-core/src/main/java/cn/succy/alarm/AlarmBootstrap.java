package cn.succy.alarm;

import cn.succy.alarm.template.TemplateModel;
import cn.succy.mq.consumer.Consumer;
import cn.succy.mq.consumer.QueueConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.io.Serializable;

/**
 * @author Succy
 * @date 2017-11-01 22:20
 **/

public class AlarmBootstrap {
    private static final Logger logger = LoggerFactory.getLogger(AlarmBootstrap.class);

    public static void main(String[] args) {
        Consumer consumer = new QueueConsumer(Constant.ALAEM_QUEUE_NAME);
        logger.info("--------- begin listening ----------");
        consumer.receive(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                ObjectMessage msg = (ObjectMessage) message;
                try {
                    Serializable object = msg.getObject();
                    TemplateModel model = (TemplateModel) object;
                    Alarm.send(model);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
