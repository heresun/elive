package com.sundehui.domain.help;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


public class PriceAnalysis {
    private Double transPrice;
    private Double pubPrice;
    private Double priceDiff;
    private String area;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getTransPrice() {
        return transPrice;
    }

    public void setTransPrice(Double transPrice) {
        this.transPrice = transPrice;
    }

    public Double getPubPrice() {
        return pubPrice;
    }

    public void setPubPrice(Double pubPrice) {
        this.pubPrice = pubPrice;
    }

    public Double getPriceDiff() {
        return priceDiff;
    }

    public void setPriceDiff(Double priceDiff) {
        this.priceDiff = priceDiff;
    }
}
