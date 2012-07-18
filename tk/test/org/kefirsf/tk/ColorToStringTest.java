package org.kefirsf.tk;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

/**
 * Test color transformation
 */
public class ColorToStringTest {
    @Test
    public void test(){
        Assert.assertEquals("#000000", ColorUtils.colorToString(Color.BLACK));
        Assert.assertEquals("#ff0000", ColorUtils.colorToString(Color.RED));
        Assert.assertEquals("#00ff00", ColorUtils.colorToString(Color.GREEN));
        Assert.assertEquals("#0000ff", ColorUtils.colorToString(Color.BLUE));
        Assert.assertEquals("#ffffff", ColorUtils.colorToString(Color.WHITE));
    }
}
