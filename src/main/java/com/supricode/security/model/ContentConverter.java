package com.supricode.security.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.sql.Blob;

@Converter
public class ContentConverter implements AttributeConverter<byte[], Blob> {
    @Override
    public Blob convertToDatabaseColumn(byte[] attribute) {
        return null;
    }

    @Override
    public byte[] convertToEntityAttribute(Blob dbData) {
        return new byte[0];
    }
    //code
}
