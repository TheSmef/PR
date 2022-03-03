package com.example.firebase;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testIsEmailValid() {
        String testEmail = "anupamch123@gmail.com";
        String testPassword = "Qwerty123!";
        String testPasswordRep = "Qwerty123!";
        String testPhone = "+7(906)888-88-88";
        Assert.assertThat(String.format("Email id valid %s ", testEmail), Utilits.checkEmailForValidity(testEmail, testPassword, testPasswordRep, testPhone), Is.is(true));
    }
}