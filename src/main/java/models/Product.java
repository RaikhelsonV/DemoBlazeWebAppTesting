package models;

import org.openqa.selenium.WebElement;

public class Product {
    private String title;
    private int price;
    private WebElement deleteButton;

    public Product(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public Product(String title, int price, WebElement deleteButton) {
        this.title = title;
        this.price = price;
        this.deleteButton = deleteButton;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public WebElement getDeleteButton() {
        return deleteButton;
    }
    public void setDeleteButton(WebElement deleteButton) {
        this.deleteButton = deleteButton;
    }
}