package com.sistearth.spark.utils;

import org.junit.Test;

import java.util.MissingResourceException;

import static com.sistearth.spark.utils.LabelUtils.getLabel;
import static org.junit.Assert.assertEquals;

public class LabelUtilsTest {
    @Test
    public void testGetLabel_HasKey() throws Exception {
        assertEquals("Lorem ipsum", getLabel("foo.bar"));
    }

    @Test(expected = MissingResourceException.class)
    public void testGetLabel_HasNotKey() throws Exception {
        getLabel("bar.foo");
    }
}