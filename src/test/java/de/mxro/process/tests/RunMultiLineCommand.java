package de.mxro.process.tests;

import org.junit.Assert;
import org.junit.Test;

import de.mxro.process.Spawn;

public class RunMultiLineCommand {

    @Test
    public void test() {
        if (Spawn.isWindows()) {
            System.out.println("Test cannot run on Windows.");
            return;
        }

        final String res = Spawn.sh("echo '123'\n echo '456'");
        Assert.assertTrue(res.contains("123"));
        Assert.assertTrue(res.contains("456"));

    }

}
