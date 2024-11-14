package Frames;

import com.microsoft.playwright.*;
import com.microsoft.playwright.Frame;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class frames {
    public static void main(String[] args) throws InterruptedException {
        Map<String, String> env = mapOf("PLAYWRIGHT_BROWSERS_PATH", "src/Chromium",
                "PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD", "1");

        //browser ve playwright kurulumu
        Playwright.CreateOptions options = new Playwright.CreateOptions().setEnv(env);
        Playwright playwright = Playwright.create(options);
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));

        Page page = browser.newPage();//yeni bir boş sayfa açtık ve boyutları ayarladık

        page.navigate("https://the-internet.herokuapp.com/frames ");// gitmek istediğimiz site
        FrameLocator framelocator=page.frameLocator("#mce_0_ifr");
        Locator inputtext= framelocator.getByLabel("Rich Text Area. Press ALT-0 for help");
        inputtext.click();
        inputtext.fill("dolduralacak cümle");


        System.out.println(page.title());// Sayfanın title ' ı
        List<Frame> frames=page.frames();
        //frame.url();
        //page.frameByUrl("Url");
        //page.frame("name");


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        page.close();
        browser.close();
        playwright.close();

    }

    static <K,V> Map<K, V> mapOf(Object... entries) {
        Map result = new HashMap();
        for (int i = 0; i + 1 < entries.length; i += 2) {
            result.put(entries[i], entries[i + 1]);
        }
        return result;
    }
}
