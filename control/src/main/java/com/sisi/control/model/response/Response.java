package com.sisi.control.model.response;

import lombok.Data;

@Data
public class Response<T> {
    private int code;
    private String message;
    private T data;

    public Response() {
    }

    public Response(int code) {
        this.code = code;
        this.message = "";
    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }




    public static <T> Response success(T data){
        return new Builder<T>(200,"success",data).build();
    }

    public static <T> Response success(){
        return new Builder<T>(200,"success",null).build();
    }

    public static <T> Response fail(String message){
        return new Builder<T>(400,message,null).build();
    }
    public static <T> Response fail(int code ,String message){
        return new Builder<T>(code,message,null).build();
    }

    public static <T> Builder builder(){
        return new Builder<T>();
    }




    public static class Builder<T> {
        private int code;
        private String message;
        private T data;

        public Builder() {

        }

        public Builder(int code,String message,T data){
            this.code = code;
            this.message = message;
            this.data = data;
        }

        public Builder code(int code){
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder data(T data) {
            this.data = data;
            return this;
        }


        public <T> Response<T> build(){
            return new Response(code,message,data);
        }
    }



}
