package Downloads;

import com.microsoft.playwright.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Downloads {  public static void main(String[] args) throws InterruptedException {
    Map<String, String> env = mapOf("PLAYWRIGHT_BROWSERS_PATH", "src/Chromium",
            "PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD", "1");

    //browser ve playwright kurulumu
    Playwright.CreateOptions options = new Playwright.CreateOptions().setEnv(env);
    Playwright playwright = Playwright.create(options);
    Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));

    Page page = browser.newPage();//yeni bir boş sayfa açtık ve boyutları ayarladık

    page.navigate("https://demoqa.com/upload-download");// gitmek istediğimiz site

    Download download = page.waitForDownload(() -> {
        page.getByText("Download").last().click();
            }
    );

    System.out.println("page" + download.page().title());
    System.out.println("url" + download.url());
    System.out.println("path" + download.path().toString());

    String filePath = "src/utilities/downloads";
    download.saveAs(Paths.get(filePath));


    System.out.println(page.title());// Sayfanın title ' ı

    try {
        Thread.sleep(10000);
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
