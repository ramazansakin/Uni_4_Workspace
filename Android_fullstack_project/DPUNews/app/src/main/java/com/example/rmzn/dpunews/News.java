package com.example.rmzn.dpunews;

/**
 * Created by Rmzn on 11/21/2015.
 */
public class News {

    private int newsID;
    private String newsTitle;
    private String UploadTime;
    private String About;
    private  int iconID;
    private String  newsImg;


    // constructor-1
    public News(){
        // default values
        newsID = 0;
        newsTitle = "Title";
        UploadTime = "Upload time";
        About = "About the news";
        newsImg = "http://ramazan.knighttube.com/images/mFBf7rMSBmerVNnurub1OYuB1vb9ZoCt0Oyo8Y0ft0yhQUlQDWIykntPdNff.jpg";

    }



    // constructor-2
    public News( String title, String upTime, String about, int icnID ){
        newsTitle = title;
        UploadTime = upTime;
        About = about;
        iconID=icnID;
    }

    // Getters and Setters

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getUploadTime() {
        return UploadTime;
    }

    public void setUploadTime(String uploadTime) {
        UploadTime = uploadTime;
    }

    public String getAbout() {
        return About;
    }

    public void setAbout(String about) {
        About = about;
    }

    public int getIconID() {
        return iconID;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }

    public int getNewsID() {
        return newsID;
    }

    public void setNewsID(int newsID) {
        this.newsID = newsID;
    }


    public String getNewsImg() {
        return newsImg;
    }

    public void setNewsImg(String newsImg) {
        this.newsImg = newsImg;
    }
}

