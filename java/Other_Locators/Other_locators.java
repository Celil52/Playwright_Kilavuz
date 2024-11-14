package Other_Locators;

import com.microsoft.playwright.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Other_locators {
    public static void main(String[] args) throws InterruptedException {
        Map<String, String> env = mapOf("PLAYWRIGHT_BROWSERS_PATH", "src/Chromium",
                "PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD", "1");

        //browser ve playwright kurulumu
        Playwright.CreateOptions options = new Playwright.CreateOptions().setEnv(env);
        Playwright playwright = Playwright.create(options);
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));

        Page page = browser.newPage();//yeni bir boş sayfa açtık ve boyutları ayarladık

        page.navigate("https://getir.com/");// gitmek istediğimiz site

        Locator ornek=page.locator("h5:has-text(\"Giriş yap veya kayıt ol\")");
        System.out.println("aranan : "+ornek.innerText());

        Locator ornek2=page.locator("h5:text(\"Giriş yap veya kayıt ol\")");
        System.out.println("aranan text: "+ornek2.innerText()); //aradığımızı //bulduk mu testi
        System.out.println(page.title());// Sayfanın title ' ı

        Locator orn1=page.locator("h5:has-text(\"Giriş yap\"),h5:has-text(\"condition2\")");
        System.out.println(orn1.innerText());
        Locator orn2=page.locator("h5:has-text(\"Giriş yap\"),h5:has-text(\"Kayıt ol\")");
        System.out.println(orn2.innerText());
        Locator orn3=page.locator("h5:has-text(\"Giriş yapma\"),h5:has-text(\"Kayıt olma\")");
        System.out.println(orn3.innerText());

        Locator deneme=page.locator(":nth-match(text('Giriş Yap'),1)");
        System.out.println(deneme.innerText());

        try {
            Thread.sleep(3000);
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
