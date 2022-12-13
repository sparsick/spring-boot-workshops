package com.github.sparsick.workshop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("bookshelf")
public class BooksProperties {

    private int someNumber;
    private String someText;

    public int getSomeNumber() {
        return someNumber;
    }

    public void setSomeNumber(int someNumber) {
        this.someNumber = someNumber;
    }

    public String getSomeText() {
        return someText;
    }

    public void setSomeText(String someText) {
        this.someText = someText;
    }
}
