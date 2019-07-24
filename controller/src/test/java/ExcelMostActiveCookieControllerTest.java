import com.quantcast.activecookie.controller.ExcelMostActiveCookieController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ExcelMostActiveCookieControllerTest {

    private ExcelMostActiveCookieController controller;

    @Before
    public void setUp(){
        controller = new ExcelMostActiveCookieController();
    }

    @Test
    public void testFindMostActiveCookieByDate(){
        File file = new File(getClass().getClassLoader().getResource("cookie_file.xlsx").getFile());
        LocalDate date = LocalDate.of(2018, 12, 9);
        List<String> cookies = controller.findMostActiveCookieByDate(file, date);

        List<String> expectedCookies = new ArrayList<>();
        expectedCookies.add("AtY0laUfhglK3lC7");
        expectedCookies.add("SAZuXPGUrfbcn5UA");

        assertTrue(cookies.containsAll(expectedCookies));

    }
}
