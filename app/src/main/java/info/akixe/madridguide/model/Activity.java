package info.akixe.madridguide.model;


import java.io.Serializable;

public class Activity implements Serializable{

    private long id;
    private String name;
    private String imgUrl;
    private String mapImgUrl;
    private String address;
    private String url;
    private String description;
    private float latitude;
    private float longitude;

    public Activity(long id, String name){
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public Activity setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Activity setName(String name) {
        this.name = name;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Activity setImgUrl(String imageUrl) {
        this.imgUrl = imageUrl;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Activity setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Activity setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Activity setDescription(String description) {
        this.description = description;
        return this;
    }

    public float getLatitude() {
        return latitude;
    }

    public Activity setLatitude(float latitude) {
        this.latitude = latitude;
        return this;
    }

    public float getLongitude() {
        return longitude;
    }

    public Activity setLongitude(float longitude) {
        this.longitude = longitude;
        return this;
    }

    public String getMapImgUrl() {
        return mapImgUrl;
    }

    public Activity setMapImgUrl(String mapImgUrl){
        this.mapImgUrl = mapImgUrl;
        return this;
    }

    public Activity setMapImgUrlWithLatLon(float lat, float lon) {
        StringBuilder builder = new StringBuilder("http://maps.googleapis.com/maps/api/staticmap?")
                .append("center=")
                .append(lat).append(",").append(lon)
                .append("&zoom=17&size=320x220");
        this.mapImgUrl = builder.toString();
        return this;
    }
}
