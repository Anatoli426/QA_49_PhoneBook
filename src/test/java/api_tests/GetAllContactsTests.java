package api_tests;

import dto.ErrorMessageDto;
import dto.TokenDto;
import dto.User;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.BaseApi;

import java.io.IOException;

public class GetAllContactsTests implements BaseApi, ILogin {
    TokenDto token;
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void login(){
        token = login_qa_user();
    }

    @Test
    public void getAllContactsPositiveTest(){
        System.out.println(token.toString());
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT)
                .addHeader(AUTH, token.getToken())
                .build();
        try(Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            Assert.assertEquals(response.code(), 200);
        }catch (IOException e){
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void getAllContactsNegativeTest(){
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT)
                .addHeader(AUTH, "11111111111111111111")
                .build();
        try(Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            softAssert.assertEquals(response.code(), 401);
            ErrorMessageDto errorMessageDto = GSON.fromJson(response.body().string(), ErrorMessageDto.class);
            System.out.println(errorMessageDto.toString());
            softAssert.assertEquals(errorMessageDto.getError(), "Unauthorized");
            softAssert.assertTrue(errorMessageDto.getMessage().contains("JWT strings must contain exactly 2 period characters"));
            softAssert.assertAll();
        }catch (IOException e){
            e.printStackTrace();
            Assert.fail();
        }
    }
}