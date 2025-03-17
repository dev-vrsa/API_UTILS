package com.app.utility.me.repository;

public interface ApiClientRepository {


    public  <T> T sendGet(String endpoint, Class<T> valueType);
    public  <T> T sendPost(String endpoint, String payload, Class<T> valueType);


}
