package com.cx.wz.banner.dto;

public class BannerDto {

    private String url;
    private String detailUrl;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public BannerDto() {
    }

    public BannerDto(String url) {
        this.url = url;
    }

    public BannerDto(String url, String detailUrl) {
        this.url = url;
        this.detailUrl = detailUrl;
    }

}
