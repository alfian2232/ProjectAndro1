package com.example.asus.pict.apihelper;

public class UtilsApi {
    // 192.168.137.1 ini adalah localhost.
    public static final String BASE_URL_API = "https://bogelardi.000webhostapp.com/api_kuliah/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient().create(BaseApiService.class);
    }
}
