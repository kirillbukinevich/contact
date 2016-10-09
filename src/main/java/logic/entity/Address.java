package logic.entity;

import org.apache.commons.lang3.StringUtils;

public class Address {
    private int id;
    private String countryName;
    private String cityName;
    private String streetName;
    private String houseNumber;
    private Integer flatNumber;
    private Integer index;

    public Address() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return this.houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Integer getFlatNumber() {
        return this.flatNumber==0 ? null:flatNumber;
    }

    public void setFlatNumber(Integer flatNumber) {
        this.flatNumber = flatNumber;
    }

    public Integer getIndex() {
        return this.index==0 ? null:index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }


    public String toString() {
        StringBuilder result = new StringBuilder();
        if(StringUtils.isNotEmpty(countryName)){
            result.append(countryName);
        }
        if(StringUtils.isNotEmpty(cityName)){
            result.append(" г.").append(cityName);
        }
        if(StringUtils.isNotEmpty(streetName)){
            result.append(" ул.").append(streetName);
        }
        if(StringUtils.isNotEmpty(houseNumber)){
            result.append(" д.").append(houseNumber);
        }
        if(flatNumber!=0){
            result.append(" кв.").append(flatNumber);
        }

        return result.toString();
    }

}
