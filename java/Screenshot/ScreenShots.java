package Screenshot;

import com.microsoft.playwright.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ScreenShots {
    public static void main(String[] args) throws InterruptedException {
        Map<String, String> env = mapOf("PLAYWRIGHT_BROWSERS_PATH", "src/Chromium",
                "PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD", "1");

        //browser ve playwright kurulumu
        Playwright.CreateOptions options = new Playwright.CreateOptions().setEnv(env);
        Playwright playwright = Playwright.create(options);
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));

        Page page = browser.newPage();//yeni bir boş sayfa açtık ve boyutları ayarladık

        page.navigate("https://www.ebay.com/");// gitmek istediğimiz site

        String tarih=new SimpleDateFormat("_hh_mm_ss__ddMMyyy").format(new Date());
        String dosyaYolu = "src/test/utilities/screenshot" + tarih + ".jpg" ;

         page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(dosyaYolu)));
         Thread.sleep(3000);

        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(dosyaYolu)).setFullPage(true));
        Thread.sleep(3000);

        Locator searchBox = page.getByPlaceholder("Search for anything");
        searchBox.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(dosyaYolu)));



        System.out.println(page.title());// Sayfanın title ' ı

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
