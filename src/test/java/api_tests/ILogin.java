package api_tests;

import dto.TokenDto;
import dto.User;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import utils.BaseApi;

import java.io.IOException;

public interface ILogin extends BaseApi {

    default TokenDto login_qa_user() {
        User user = new User("a@mail.ru", "Password123!");
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TokenDto token;
        try {
            token = GSON.fromJson(response.body().string(), TokenDto.class);
            return token;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}