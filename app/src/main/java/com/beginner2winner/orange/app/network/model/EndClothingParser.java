package com.beginner2winner.orange.app.network.model;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import retrofit2.Converter;

/**
 * Created by richard
 *
 * Rudimentary XmlPullParser, doing the minimum to achieve the result: parse source XML to obtain
 * simplified list from a much more complex data set.
 *
 * For more scalable and flexible solution, use something like Simple (http://simple.sourceforge.net/home.php)
 *
 */

public class EndClothingParser {

    private static final String soapns = "soapenv";

    public EndClothingProductsList parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readEnvelope(parser);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    private EndClothingProductsList readEnvelope(XmlPullParser parser) throws XmlPullParserException, IOException {

        //parser.require(XmlPullParser.START_TAG, null, "soapns:Envelope");

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("soapenv:Body")) {
                return readBody(parser);
            } else {
                XmlParserUtil.skip(parser);
            }
        }
        return null;
    }

    private EndClothingProductsList readBody(XmlPullParser parser) throws XmlPullParserException, IOException {

        //parser.require(XmlPullParser.START_TAG, null, "soapns:Body");

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("page")) {
                return readPage(parser);
            } else {
                XmlParserUtil.skip(parser);
            }
        }
        return null;
    }

    private EndClothingProductsList readPage(XmlPullParser parser) throws XmlPullParserException, IOException {

        parser.require(XmlPullParser.START_TAG, null, "page");

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("universes")) {
                return readUniverses(parser);
            } else {
                XmlParserUtil.skip(parser);
            }
        }
        return null;
    }

    private EndClothingProductsList readUniverses(XmlPullParser parser) throws XmlPullParserException, IOException {

        parser.require(XmlPullParser.START_TAG, null, "universes");

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            String attrname = parser.getAttributeValue(null, "name");
            if (name.equals("universe") && attrname != null && attrname.equals("defaultcategory2")) {
                return readUniverseDefaultCategory2(parser);
            } else {
                XmlParserUtil.skip(parser);
            }
        }
        return null;
    }

    private EndClothingProductsList readUniverseDefaultCategory2(XmlPullParser parser) throws XmlPullParserException, IOException {

        parser.require(XmlPullParser.START_TAG, null, "universe");

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("items-section")) {
                return readItemsSection(parser);
            } else {
                XmlParserUtil.skip(parser);
            }
        }
        return null;
    }

    private EndClothingProductsList readItemsSection(XmlPullParser parser) throws XmlPullParserException, IOException {

        parser.require(XmlPullParser.START_TAG, null, "items-section");

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("items")) {
                return readItems(parser);
            } else {
                XmlParserUtil.skip(parser);
            }
        }
        return null;
    }

    private EndClothingProductsList readItems(XmlPullParser parser) throws XmlPullParserException, IOException {

        parser.require(XmlPullParser.START_TAG, null, "items");

        EndClothingProductsList list = new EndClothingProductsList();

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("item")) {
                list.add(readItem(parser));
            } else {
                XmlParserUtil.skip(parser);
            }
        }
        return list;
    }

    private ProductItem readItem(XmlPullParser parser) throws XmlPullParserException, IOException {

        parser.require(XmlPullParser.START_TAG, null, "item");

        String attrId = parser.getAttributeValue(null, "id");

        ProductItem item = new ProductItem(attrId);

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();
            if (name.equals("attribute")) {
                ItemAttribute attr = readAttribute(parser, item);
                item.addAttribute(attr);
            } else {
                XmlParserUtil.skip(parser);
            }
        }

        return item;
    }

    private ItemAttribute readAttribute(XmlPullParser parser, ProductItem item) throws XmlPullParserException, IOException {

        parser.require(XmlPullParser.START_TAG, null, "attribute");

        String attrIsNull = parser.getAttributeValue(null, "isnull");
        String attrName = parser.getAttributeValue(null, "name");
        String attrBaseType = parser.getAttributeValue(null, "basetype");
        String attrSelected = parser.getAttributeValue(null, "selected");

        ItemAttribute attribute = new ItemAttribute(attrIsNull, attrSelected, attrName, ItemAttribute.AttributeBaseType.findByname(attrBaseType));

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();
            if (name.equals("value")) {
                AttributeValue attrValue = readAttributeValue(parser, attribute);
                attribute.addValue(attrValue);
            } else {
                XmlParserUtil.skip(parser);
            }
        }

        return attribute;
    }

    private AttributeValue readAttributeValue(XmlPullParser parser, ItemAttribute attribute) throws XmlPullParserException, IOException {

        parser.require(XmlPullParser.START_TAG, null, "value");

        String attrNonML = parser.getAttributeValue(null, "non-ml");
        String text = XmlParserUtil.readText(parser);

        parser.require(XmlPullParser.END_TAG, null, "value");

        return new AttributeValue(attrNonML, text);
    }

}
