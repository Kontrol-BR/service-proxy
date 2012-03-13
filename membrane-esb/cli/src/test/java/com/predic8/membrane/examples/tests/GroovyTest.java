package com.predic8.membrane.examples.tests;

import static com.predic8.membrane.examples.AssertUtils.getAndAssert200;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.predic8.membrane.examples.DistributionExtractingTestcase;
import com.predic8.membrane.examples.Process2;
import com.predic8.membrane.examples.util.SubstringWaitableConsoleEvent;

public class GroovyTest extends DistributionExtractingTestcase {

	@Test
	public void test() throws IOException, InterruptedException {
		Process2 sl = new Process2.Builder().in(getExampleDir("groovy")).script("router").waitForMembrane().start();
		try {
			SubstringWaitableConsoleEvent groovyCalled = new SubstringWaitableConsoleEvent(sl, "X-Groovy header added.");
			getAndAssert200("http://localhost:2000/");
			assertTrue(groovyCalled.occurred());
		} finally {
			sl.killScript();
		}
	}

}