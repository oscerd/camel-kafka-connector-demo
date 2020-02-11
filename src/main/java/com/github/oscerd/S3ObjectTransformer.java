package com.github.oscerd;

import java.util.Map;

import com.amazonaws.services.s3.model.S3ObjectInputStream;

import org.apache.camel.kafkaconnector.converters.S3ObjectSerializer;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.transforms.Transformation;

public class S3ObjectTransformer<R extends ConnectRecord<R>> implements Transformation<R> {

    public static final ConfigDef CONFIG_DEF = new ConfigDef()
            .define("test", ConfigDef.Type.STRING, "test", ConfigDef.Importance.MEDIUM, "Transform the content of a bucket into a string ");

    private final S3ObjectSerializer serializer = new S3ObjectSerializer();

    @Override
    public void configure(Map<String, ?> configs) {
    }

    @Override
    public R apply(R record) {
        byte[] v = serializer.serialize(record.topic(), (S3ObjectInputStream) record.value());
        String finalValue = new String(v);
        return record.newRecord(record.topic(), record.kafkaPartition(), null, record.key(), Schema.STRING_SCHEMA, finalValue, record.timestamp());
    }

    @Override
    public void close() {
    }

    @Override
    public ConfigDef config() {
        return CONFIG_DEF;
    }

}
