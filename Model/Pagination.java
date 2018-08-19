package com.example.aebrahimi.firstmvp.Model;

public class Pagination {
   private float total_count;
   private float count;
   private float offset;


   // Getter Methods

   public float getTotal_count() {
       return total_count;
   }

   public float getCount() {
       return count;
   }

   public float getOffset() {
       return offset;
   }

   // Setter Methods

   public void setTotal_count(float total_count) {
       this.total_count = total_count;
   }

   public void setCount(float count) {
       this.count = count;
   }

   public void setOffset(float offset) {
       this.offset = offset;
   }
}
