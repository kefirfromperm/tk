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
        Assert.assertEquals("#000000", TwitCommand.colorToString(Color.BLACK));
        Assert.assertEquals("#ff0000", TwitCommand.colorToString(Color.RED));
        Assert.assertEquals("#00ff00", TwitCommand.colorToString(Color.GREEN));
        Assert.assertEquals("#0000ff", TwitCommand.colorToString(Color.BLUE));
        Assert.assertEquals("#ffffff", TwitCommand.colorToString(Color.WHITE));
    }
}
