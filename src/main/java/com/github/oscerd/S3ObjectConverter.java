package com.github.oscerd;

import java.util.Map;

import com.amazonaws.services.s3.model.S3ObjectInputStream;

import org.apache.camel.kafkaconnector.converters.S3ObjectSerializer;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaAndValue;
import org.apache.kafka.connect.storage.Converter;

public class S3ObjectConverter implements Converter {

    private final S3ObjectSerializer serializer = new S3ObjectSerializer();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] fromConnectData(String topic, Schema schema, Object value) {
        return serializer.serialize(topic, (S3ObjectInputStream) value);
    }

    @Override
    public SchemaAndValue toConnectData(String arg0, byte[] arg1) {
        return null;
    }

}