package Locators;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Locators {
    public static void main(String[] args) throws InterruptedException {
        Map<String, String> env = mapOf("PLAYWRIGHT_BROWSERS_PATH", "src/Chromium",
                "PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD", "1");

        //browser ve playwright kurulumu
        Playwright.CreateOptions options = new Playwright.CreateOptions().setEnv(env);
        Playwright playwright = Playwright.create(options);
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));

        Page page = browser.newPage();//yeni bir boş sayfa açtık ve boyutları ayarladık

        page.navigate("https://getir.com/");// gitmek istediğimiz site

        System.out.println(page.title());// Sayfanın title ' ı

        Locator shadowDom = page.locator("div", new Page.LocatorOptions().setHasText("Giriş yap veya kayıt ol")).last();
        System.out.println("aranan text 1: "+shadowDom.innerText());

        Locator telno=page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Telefon Numarası"));
        System.out.println("aranan text 2: "+telno.innerText()); //aradığımızı //bulduk mu testi

        Locator telno2=page.getByPlaceholder("Telefon Numarası");
        System.out.println("aranan text 3: "+telno2.innerText()); //aradığımızı //bulduk mu testi

        Locator loginButton = page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Giriş Yap"));
        loginButton.click();

        Locator phone=page.getByTestId("modal").getByPlaceholder("Telefon Numarası");
        phone.click();
        phone.fill("11111");




        try {
            Thread.sleep(5000);
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
