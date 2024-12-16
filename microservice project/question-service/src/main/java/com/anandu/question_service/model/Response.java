package com.anandu.question_service.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Response {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    private Integer id;

    public Response(Integer id, String response) {
        this.id = id;
        this.response = response;
    }

    private  String response;
}
