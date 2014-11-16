
package com.cire.instagramphotoviewer.modal;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InstagramPhoto {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class User {
        private String username;
        private String profile_picture;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getProfile_picture() {
            return profile_picture;
        }

        public void setProfile_picture(String profile_picture) {
            this.profile_picture = profile_picture;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Caption {
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Likes {
        private int Count;

        public int getCount() {
            return Count;
        }

        public void setCount(int count) {
            Count = count;
        }

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Images {

        @JsonIgnoreProperties(ignoreUnknown = true)
        public class Image {
            private String url;
            private int width;
            private int height;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }
        }

        private Image thumbnail;

        private Image standard_resolution;

        public Image getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(Image thumbnail) {
            this.thumbnail = thumbnail;
        }

        public Image getStandard_resolution() {
            return standard_resolution;
        }

        public void setStandard_resolution(Image standard_resolution) {
            this.standard_resolution = standard_resolution;
        }

    }

    private User user;
    private Caption caption;
    private Images images;
    private Likes likes;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Caption getCaption() {
        return caption;
    }

    public void setCaption(Caption caption) {
        this.caption = caption;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

}
