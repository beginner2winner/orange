package com.beginner2winner.orange.app.network.model;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Retrofit2 Converter Factory and Converter for EndClothingProductsList
 *
 * Created by richard, © copyright Beginner2Winner Ltd
 */

public class EndClothingConverterFactory extends Converter.Factory {

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return EndClothingConverter.INSTANCE;
    }

    final static class EndClothingConverter implements Converter<ResponseBody, EndClothingProductsList> {
        static final EndClothingConverter INSTANCE = new EndClothingConverter();

        @Override
        public EndClothingProductsList convert(ResponseBody responseBody) throws IOException {
            try {
                return new EndClothingParser().parse(responseBody.byteStream());
            } catch (XmlPullParserException e) {
                throw new IOException("Failed to parse XML", e);
            }
        }
    }

}
