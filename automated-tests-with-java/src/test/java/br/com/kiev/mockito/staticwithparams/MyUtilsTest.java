package br.com.kiev.mockito.staticwithparams;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class MyUtilsTest {

    @Test
    void shouldMockStaticMethodWithParams() {
        try (MockedStatic<MyUtils> mockedStatic = mockStatic(MyUtils.class)) {
            mockedStatic.when(() -> MyUtils.getWelcomeMessage(eq("Kiev"), anyBoolean())).thenReturn("Howdy Kiev!");
            var result = MyUtils.getWelcomeMessage("Kiev", false);

            assertEquals("Howdy Kiev!", result);
        }
    }

    @Test
    public void testGetWelcomeMessage_IsCustomer_True() {
        String userName = "Kiev";
        Boolean isCustomer = true;

        String result = MyUtils.getWelcomeMessage(userName, isCustomer);

        assertEquals("Dear Kiev", result);
    }

    @Test
    public void testGetWelcomeMessage_IsCustomer_False() {
        String userName = "Kiev";
        Boolean isCustomer = false;

        String result = MyUtils.getWelcomeMessage(userName, isCustomer);

        assertEquals("Hello Kiev", result);
    }

    @Test
    public void testGetWelcomeMessage_NullUserName_NotCustomer_ReturnsEmptyString() {
        String userName = null;
        Boolean isCustomer = false;
        String expectedResult = "";

        String result = MyUtils.getWelcomeMessage(userName, isCustomer);

        assertEquals(expectedResult, "");
    }
}