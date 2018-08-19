package com.example.aebrahimi.firstmvp.Model;

import com.google.gson.annotations.SerializedName;

public class GifModel{
    User user;
    Images images;

    public Images getImage() {
        return images;
    }

    public void setImage(Images image) {
        this.images= image;
    }

    public class Images {

        @SerializedName("fixed_height_still")
          Fixed_height Fixed_heightObject;
        @SerializedName("original_still")
        OrginalImage originalImage;
        public OrginalImage getOriginalImage() {
            return originalImage;
        }

        public void setOriginalImage(OrginalImage originalImage) {
            this.originalImage = originalImage;
        }
    public Fixed_height getFixed_heightObject() {
        return Fixed_heightObject;
    }

    public void setFixed_heightObject(Fixed_height fixed_heightObject) {
        Fixed_heightObject = fixed_heightObject;
    }

}

public class Fixed_height {
    private String url;
    private String width;
    private String height;
    private String size;
    private String mp4;
    private String mp4_size;
    private String webp;
    private String webp_size;


    // Getter Methods

    public String getUrl() {
        return url;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    public String getSize() {
        return size;
    }

    public String getMp4() {
        return mp4;
    }

    public String getMp4_size() {
        return mp4_size;
    }

    public String getWebp() {
        return webp;
    }

    public String getWebp_size() {
        return webp_size;
    }

    // Setter Methods

    public void setUrl(String url) {
        this.url = url;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setMp4(String mp4) {
        this.mp4 = mp4;
    }

    public void setMp4_size(String mp4_size) {
        this.mp4_size = mp4_size;
    }

    public void setWebp(String webp) {
        this.webp = webp;
    }

    public void setWebp_size(String webp_size) {
        this.webp_size = webp_size;
    }
}

public class User {
    private String avatar_url;
    private String banner_url;
    private String banner_image;
    private String profile_url;
    private String username;
    private String display_name;


    // Getter Methods

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getBanner_url() {
        return banner_url;
    }

    public String getBanner_image() {
        return banner_image;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplay_name() {
        return display_name;
    }

    // Setter Methods

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public void setBanner_url(String banner_url) {
        this.banner_url = banner_url;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }
}
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public class OrginalImage {
        private String url;
        private String width;
        private String height;
        private String size;


        // Getter Methods

        public String getUrl() {
            return url;
        }

        public String getWidth() {
            return width;
        }

        public String getHeight() {
            return height;
        }

        public String getSize() {
            return size;
        }

        // Setter Methods

        public void setUrl(String url) {
            this.url = url;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public void setSize(String size) {
            this.size = size;
        }
    }
}
