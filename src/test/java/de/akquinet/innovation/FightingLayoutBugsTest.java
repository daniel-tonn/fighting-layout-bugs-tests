package de.akquinet.innovation;


import com.googlecode.fightinglayoutbugs.FightingLayoutBugs;
import com.googlecode.fightinglayoutbugs.LayoutBug;
import com.googlecode.fightinglayoutbugs.WebPage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Collection;

/**
 * Simple tests for using fighting-layout-bugs library from Michael Tamm
 */
public class FightingLayoutBugsTest
{
    @Test
    public void testATSHomepage()
    {
        // spree.de: wrong Content-type for Favicon
        runLayoutTests("http://www.spree.de");
    }

    @Test
    public void testAmazon()
    {
        // Amazon: missing Image
        // false alarm textNearOrOverlappingVerticalEdges
        runLayoutTests("http://www.amazon.de/");

    }

    @Test
    public void testATSTechnologyRadar()
    {
        // Missing Images
        // false alarm textNearOrOverlappingVerticalEdges
        runLayoutTests("http://radar.spree.de/overview");
    }

    
    private void runLayoutTests(String url) {
        WebDriver driver = new FirefoxDriver();

        // Intiale Loginseite aufrufen
        driver.get(url);

        WebPage webPage = new WebPage(driver);
        FightingLayoutBugs flb = new FightingLayoutBugs();
        final Collection<LayoutBug> layoutBugs = flb.findLayoutBugsIn(webPage);
        System.out.println("Found " + layoutBugs.size() + " layout bug(s).");
        for (LayoutBug bug : layoutBugs) {
            System.out.println(bug);
        }

        // Closing Browser
        driver.quit();

    }
}
