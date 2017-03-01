package com.beginner2winner.orange.app.network.model;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by richard, © copyright Beginner2Winner Ltd
 */

public class XmlParserUtil {

    public static void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

    public static String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    public static Boolean stringToBoolean(String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("true")) {
            return Boolean.TRUE;
        }
        if (value.equals("false")) {
            return Boolean.FALSE;
        }
        return null;
    }
}
