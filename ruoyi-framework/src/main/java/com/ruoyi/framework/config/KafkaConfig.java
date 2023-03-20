package com.ruoyi.framework.config;

import com.ruoyi.common.utils.StringUtils;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * kafka配置
 * 2022-10
 *
 * 下面可以改成bean 还没尝试
 * https://www.cnblogs.com/nanxin521/p/4514971.html
 *
 * 过滤器
 * https://blog.csdn.net/yuanlong122716/article/details/105160545/
 *
 * 注解 @SendTo 再次转发
 *
 * 其他配置 也就是空的 看情况使用吧
 *
 */
@Configuration
@EnableKafka
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Value(" ")
    private String kafkaSecurityProtocol;
    @Value(" ")
    private String kafkaSASLMechanism;
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;
    @Value(" ")
    private String kafkaConsumerSASLJaasConfig;
    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;
    @Value("${spring.kafka.consumer.max-poll-records}")
    private String maxPollRecords;
    @Value("${spring.kafka.producer.retries}")
    private String producerRetries;
    @Value(" ")
    private String kafkaProducerSASLJaasConfig;
    @Value("${spring.kafka.producer.batch-size}")
    private String producerBatchSize;
    @Value("${spring.kafka.producer.properties.linger.ms}")
    private String producerLingerMs;
    @Value("${spring.kafka.producer.buffer-memory}")
    private String bufferMemory;

    @Bean
    public KafkaListenerContainerFactory<?> batchFactory(){
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new
                ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(consumerConfigs()));
        factory.setBatchListener(true); // 开启批量监听
        return factory;
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> config = new HashMap<>();
        //kafka地址
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        //组id
        config.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        config.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
        config.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
        config.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        if (!StringUtils.isEmpty(kafkaSecurityProtocol) && !StringUtils.isEmpty(kafkaSASLMechanism)
                && !StringUtils.isEmpty(kafkaConsumerSASLJaasConfig)) {
            config.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, kafkaSecurityProtocol);
            config.put(SaslConfigs.SASL_MECHANISM, kafkaSASLMechanism);
            config.put("sasl.jaas.config", kafkaConsumerSASLJaasConfig);
        }
        return config;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.RETRIES_CONFIG, producerRetries); // 重试，0为不启用重试机制
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, producerBatchSize); // 控制批处理大小，单位为字节，默认为16384
        properties.put(ProducerConfig.LINGER_MS_CONFIG, producerLingerMs); // 批量发送，延迟为5毫秒，启用该功能能有效减少生产者发送消息次数，从而提高并发量,默认为0
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory); // 生产者可以使用的总内存字节来缓冲等待发送到服务器的记录,默认为33554432，使用默认值即可
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        if (!StringUtils.isEmpty(kafkaSecurityProtocol) && !StringUtils.isEmpty(kafkaSASLMechanism)
                && !StringUtils.isEmpty(kafkaProducerSASLJaasConfig)) {
            properties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, kafkaSecurityProtocol);
            properties.put(SaslConfigs.SASL_MECHANISM, kafkaSASLMechanism);
            properties.put("sasl.jaas.config", kafkaProducerSASLJaasConfig);
        }
        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<String,String> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }


}
