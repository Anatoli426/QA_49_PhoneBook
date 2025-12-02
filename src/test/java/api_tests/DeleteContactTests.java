package api_tests;

import dto.ContactsDto;
import dto.TokenDto;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class DeleteContactTests implements ILogin {
    TokenDto token;
    SoftAssert softAssert = new SoftAssert();
    String contactId;

    @BeforeClass
    public void login() {
        token = login_qa_user();
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT)
                .addHeader(AUTH, token.getToken())
                .build();
        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            if (response.code() == 200) {
                ContactsDto contactsDto = GSON.fromJson(response.body().string(), ContactsDto.class);
                contactId = contactsDto.getContacts().get(0).getId();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deletePositiveTest() {
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT + "/" + contactId)
                .addHeader(AUTH, token.getToken())
                .delete()
                .build();
        try(Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            Assert.assertEquals(response.code(), 200);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void deleteNegativeTest() {
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CONTACT + "/" + "wrong")
                .addHeader(AUTH, token.getToken())
                .delete()
                .build();
        try(Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            Assert.assertEquals(response.code(), 400);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}