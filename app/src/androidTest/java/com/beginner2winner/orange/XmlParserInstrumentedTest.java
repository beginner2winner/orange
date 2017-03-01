package com.beginner2winner.orange;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.beginner2winner.orange.app.network.model.AttributeValue;
import com.beginner2winner.orange.app.network.model.EndClothingParser;
import com.beginner2winner.orange.app.network.model.EndClothingProductsList;
import com.beginner2winner.orange.app.network.model.ItemAttribute;
import com.beginner2winner.orange.app.network.model.ProductItem;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 */
@RunWith(AndroidJUnit4.class)
public class XmlParserInstrumentedTest {

    @Test
    public void xmlParser() throws Exception {

        // Get test data stream
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("test_data.xml");

        assertNotNull(in);

        // Fire up a parser
        EndClothingParser parser = new EndClothingParser();

        // Run it!
        try {
            EndClothingProductsList p = parser.parse(in);

            Assert.assertEquals(p.getItemCount(), 10);

            for (int i = 0; i < p.getItemCount(); i++) {
                ProductItem item = p.getItem(i);
                String colour = "none";
                ItemAttribute value = item.getAttribute("colour");
                if (value != null) {
                    colour = value.toString();
                }
                Log.d("item", ">>>" + item.getAttribute("name").toString() + " : " + colour);
            }

        } catch (XmlPullParserException ex) {
            ex.printStackTrace();
            Assert.fail(ex.getMessage());
        } catch (IOException ioex) {
            Assert.fail(ioex.getMessage());
        }

    }
}
