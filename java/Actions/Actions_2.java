package Actions;

import com.microsoft.playwright.*;

import java.util.HashMap;
import java.util.Map;

public class Actions_2 {
    public static void main(String[] args) throws InterruptedException {
        Map<String, String> env = mapOf("PLAYWRIGHT_BROWSERS_PATH", "src/Chromium",
                "PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD", "1");

        //browser ve playwright kurulumu
        Playwright.CreateOptions options = new Playwright.CreateOptions().setEnv(env);
        Playwright playwright = Playwright.create(options);
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));

        Page page = browser.newPage();//yeni bir boş sayfa açtık ve boyutları ayarladık

        page.navigate("https://www.ebay.com/");// gitmek istediğimiz site

        Locator register = page.getByText("register").first();
        register.click();
        /*
        Locator checkbox = page.getByText(“register”).first();
        ile bir checkbox locatorunu almış olalım
        Bazı eylemleri inceleyelim:
        checkbox.click();= Checkbox’a tıklar.
        checkbox.check();= Checkbox’u işaretler.
        checkbox.ischecked();= Checkbox işaretlenmiş mi bakar.
        checkbox.setchecked(true);= Checkbox’u işaretler*/


        System.out.println(page.title());// Sayfanın title ' ı

        try {
            Thread.sleep(20000);
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
