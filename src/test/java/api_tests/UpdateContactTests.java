package api_tests;

import dto.Contact;
import dto.ResponseMessageDto;
import dto.TokenDto;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.BaseApi;
import utils.ContactFactory;

import java.io.IOException;

public class UpdateContactTests implements BaseApi, ILogin {
    TokenDto token;
    String contactId;
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void login(){
        token = login_qa_user();
    }

    @BeforeMethod
    public void createContact(){
        Contact contact = ContactFactory.positiveContact();
        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT)
                .addHeader(AUTH, token.getToken())
                .post(requestBody)
                .build();
        try(Response response = OK_HTTP_CLIENT.newCall(request).execute()){
            if(response.code() == 200){
                ResponseMessageDto responseMessageDto = GSON.fromJson(response.body().string(), ResponseMessageDto.class);
                contactId = responseMessageDto.getMessage().split("ID: ")[1];
                System.out.println(contactId);
            }
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("created exception");
        }
    }

    @Test
    public void updateContactPositiveTest(){
        Contact contact = ContactFactory.positiveContact();
        contact.setId(contactId);
        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT)
                .addHeader(AUTH, token.getToken())
                .put(requestBody)
                .build();
        try(Response response = OK_HTTP_CLIENT.newCall(request).execute()){
            softAssert.assertEquals(response.code(), 200);
            ResponseMessageDto responseMessageDto = GSON.fromJson(response.body().string(), ResponseMessageDto.class);
            softAssert.assertEquals(responseMessageDto.getMessage(), "Contact was updated");
            softAssert.assertAll();
        }catch (IOException e){
            e.printStackTrace();
            Assert.fail();
        }
    }
}