package psn.cyf.utils.sax.xml.util.vo;

import psn.cyf.base.vo.BaseVO;

public class ScanningUnitVO extends BaseVO {
    private String id;
    private String name;
    private String street;
    private String housenumber;
    private String country;
    private String postcode;
    private String city;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "ScanningUnitVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", housenumber='" + housenumber + '\'' +
                ", country='" + country + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public String getPrimaryKey() {
        return null;
    }

    @Override
    public String getPkField() {
        return null;
    }
}
