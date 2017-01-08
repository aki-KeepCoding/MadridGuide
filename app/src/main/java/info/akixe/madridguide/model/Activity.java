package info.akixe.madridguide.model;


import java.io.Serializable;

public class Activity implements Serializable{

    // TODO: 8/1/17 Borrar comentarios en Activity
    // Modelo
    /*
    id
    name
    img
    logo_img
    address
    opening_hours_es
    opening_hours_en
    opening_hours_cn
    opening_hours_jp
    opening_hours_mx
    opening_hours_cl
    telephone
    email
    url
    description_es/en/...
    gps_lat
    gps_lon
    special_offer
    categories[..],
    rutas: [...],
    keywords_es/...
    * */

    private long id;
    private String name;
    private String imgUrl;
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
}
