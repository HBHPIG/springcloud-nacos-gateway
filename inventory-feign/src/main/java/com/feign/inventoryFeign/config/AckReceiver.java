package com.feign.inventoryFeign.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HBH
 */

@Component
public class AckReceiver implements ChannelAwareMessageListener {

    private static final  String TESTDIRECTQUEUE = "TestDirectQueue";

    private static final  String TESTDIRECTQUEUE1 = "TestDirectQueue1";

    @Override
    public void onMessage (Message message, Channel channel) throws Exception {

        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            String msg = message.toString();
            System.out.println(msg);
            String[] myArray = msg.split("'");
            int aa = 1/0;
            Map<String, String> msgMap = mapStringToMap(myArray[1].trim(), 3);

            if (TESTDIRECTQUEUE.equals(message.getMessageProperties().getConsumerQueue())){

                System.out.println(TESTDIRECTQUEUE+"   deliveryTag="+deliveryTag+"  MyAckReceiver   "+msgMap.toString());

                System.out.println("消息的主题消息来自："+message.getMessageProperties().getConsumerQueue());
                /**
                 * 第二个参数，手动确认可以批处理。true：可以一次性确认，deliveryTag 小于等于传入值的所有消息
                 */
                channel.basicAck(deliveryTag, true);
            }

           else if (TESTDIRECTQUEUE1.equals(message.getMessageProperties().getConsumerQueue())){

                System.out.println(TESTDIRECTQUEUE1+"   deliveryTag="+deliveryTag+"  MyAckReceiver   "+msgMap.toString());

                System.out.println("消息的主题消息来自："+message.getMessageProperties().getConsumerQueue());

                /**
                 * 第二个参数true会重新放回队列
                 */
                channel.basicReject(deliveryTag,false);

                /**
                 * 第二个参数，拒绝确认。true：批量处理小于等于deliveryTag的所有值，false：单条
                 */
//                channel.basicNack(deliveryTag, true, true);
            }

           else if ("fanout.A".equals(message.getMessageProperties().getConsumerQueue())){

                System.out.println("fanout.A"+"   deliveryTag="+deliveryTag+"  MyAckReceiver   "+msgMap.toString());

                System.out.println("消息的主题消息来自："+message.getMessageProperties().getConsumerQueue());
                /**
                 * 第二个参数，手动确认可以批处理。true：可以一次性确认，deliveryTag 小于等于传入值的所有消息
                 */
                channel.basicAck(deliveryTag, true);
            } else {
                System.out.println(message.getMessageProperties().getConsumerQueue()+"   deliveryTag="+deliveryTag+"  MyAckReceiver   "+msgMap.toString());

                System.out.println("消息的主题消息来自："+message.getMessageProperties().getConsumerQueue());
                /**
                 * 第二个参数，手动确认可以批处理。true：可以一次性确认，deliveryTag 小于等于传入值的所有消息
                 */
                channel.basicAck(deliveryTag, true);
            }


        }catch (Exception e){
            System.out.println("处理消息时显示异常,异常是:{},现拒绝消费当前消息且不再放回队列!!!"+e.getMessage());
            channel.basicReject(deliveryTag, false);
//            e.printStackTrace();
        }
    }

    /**
     * {key=value,key=value,key=value} 格式转换成map
     * @param str
     * @param entryNum
     * @return
     */

    private Map<String, String> mapStringToMap(String str,int entryNum ) {
        str = str.substring(1, str.length() - 1);
        String[] strs = str.split(",",entryNum);
        Map<String, String> map = new HashMap<String, String>();
        for (String string : strs) {
            String key = string.split("=")[0].trim();
            String value = string.split("=")[1];
            map.put(key, value);
        }
        return map;
    }
}
