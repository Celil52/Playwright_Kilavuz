package Actions;

import com.microsoft.playwright.*;

import java.util.HashMap;
import java.util.Map;

public class Actions_4 {
    public static void main(String[] args) throws InterruptedException {
        Map<String, String> env = mapOf("PLAYWRIGHT_BROWSERS_PATH", "src/Chromium",
                "PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD", "1");

        //browser ve playwright kurulumu
        Playwright.CreateOptions options = new Playwright.CreateOptions().setEnv(env);
        Playwright playwright = Playwright.create(options);
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));

        Page page = browser.newPage();//yeni bir boş sayfa açtık ve boyutları ayarladık

        page.navigate("https://demoqa.com/buttons");// gitmek istediğimiz site

        Locator clickme=page.getByText("Click Me").nth(2);
        clickme.click();

        Thread.sleep(3000);

        Locator dclickme=page.getByText("Double Click Me");
        dclickme.dblclick();

        Thread.sleep(3000);

        Locator rclickme=page.getByText("Right Click Me");
        rclickme.hover();




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
