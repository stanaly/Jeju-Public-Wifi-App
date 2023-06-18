package com.jejuPublicWifi.Explore.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;
@Entity
@Table(name = "wifis")
public class Wifi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique=true)
    private String macAddress; // 맥 주소
    @NotBlank
    private String apGroupName; // AP 그룹명
    @NotBlank
    private String installLocationDetail; // 설치된 상세주소
    private String category; // 카테고리
    private String categoryDetail; // 카테고리상세
    private String addressDong; // 행정구역
    private String addressDetail; // 상세주소
    private Double latitude; // 위도
    private Double longitude; // 경도
    private Double score; // 별점
    private String congestion; // 혼잡도

    public Wifi(String macAddress, String apGroupName, String installLocationDetail, String category, String categoryDetail, String addressDong, String addressDetail, Double latitude, Double longitude, Double score, String congestion) {
        this.macAddress = macAddress;
        this.apGroupName = apGroupName;
        this.installLocationDetail = installLocationDetail;
        this.category = category;
        this.categoryDetail = categoryDetail;
        this.addressDong = addressDong;
        this.addressDetail = addressDetail;
        this.latitude = latitude;
        this.longitude = longitude;
        this.score = score;
        this.congestion = congestion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getApGroupName() {
        return apGroupName;
    }

    public void setApGroupName(String apGroupName) {
        this.apGroupName = apGroupName;
    }

    public String getInstallLocationDetail() {
        return installLocationDetail;
    }

    public void setInstallLocationDetail(String installLocationDetail) {
        this.installLocationDetail = installLocationDetail;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryDetail() {
        return categoryDetail;
    }

    public void setCategoryDetail(String categoryDetail) {
        this.categoryDetail = categoryDetail;
    }

    public String getAddressDong() {
        return addressDong;
    }

    public void setAddressDong(String addressDong) {
        this.addressDong = addressDong;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getCongestion() {
        return congestion;
    }

    public void setCongestion(String congestion) {
        this.congestion = congestion;
    }
}
