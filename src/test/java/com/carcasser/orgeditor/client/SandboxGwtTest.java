package com.carcasser.orgeditor.client;

import com.google.gwt.junit.client.GWTTestCase;

public class SandboxGwtTest extends GWTTestCase {
    @Override
    public String getModuleName() {
        return "com.carcasser.orgeditor.Orgeditor";
    }

    public void testSandbox() {
        assertTrue(true);
    }
}
