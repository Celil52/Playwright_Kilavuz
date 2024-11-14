package assertions;

import com.microsoft.playwright.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class assertions {    public static void main(String[] args) throws InterruptedException {
    Map<String, String> env = mapOf("PLAYWRIGHT_BROWSERS_PATH", "src/Chromium",
            "PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD", "1");

    //browser ve playwright kurulumu
    Playwright.CreateOptions options = new Playwright.CreateOptions().setEnv(env);
    Playwright playwright = Playwright.create(options);
    Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));

    Page page = browser.newPage();//yeni bir boş sayfa açtık ve boyutları ayarladık

    page.navigate("https://www.ebay.com/");// gitmek istediğimiz site


    assertThat(page).hasURL("https://www.ebay.com/");
    assertThat(page).hasTitle("Electronics, Cars, Fashion, Collectibles & More | eBay");
    assertThat(page).not().hasTitle("dsafsad");

    Locator sign=page.getByText("Sign in").nth(1);
    assertThat(sign).containsText("Sign");

    Locator search=page.getByPlaceholder("Search for anything");
    assertThat(search).hasAttribute("type","text");

    Locator register=page.getByText("Register");
    assertThat(register).hasText("register");

    assertThat(search).isEditable();
    System.out.println(search.isEditable());

    assertThat(search).isEmpty();
    assertThat(search).isVisible();

    System.out.println(page.title());// Sayfanın title ' ı


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
