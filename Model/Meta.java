package com.example.aebrahimi.firstmvp.Model;

public class Meta {
   private float status;
   private String msg;
   private String response_id;


   // Getter Methods

   public float getStatus() {
       return status;
   }

   public String getMsg() {
       return msg;
   }

   public String getResponse_id() {
       return response_id;
   }

   // Setter Methods

   public void setStatus(float status) {
       this.status = status;
   }

   public void setMsg(String msg) {
       this.msg = msg;
   }

   public void setResponse_id(String response_id) {
       this.response_id = response_id;
   }
}
