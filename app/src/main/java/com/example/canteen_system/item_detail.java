package com.example.canteen_system;

public class item_detail {
    String food_name,item_descriptions;
    String coast,number_of_items,time_to_get_ready;

    public item_detail(String food_name, String item_descriptions, String coast, String number_of_items, String time_to_get_ready) {
        this.food_name = food_name;
        this.item_descriptions = item_descriptions;
        this.coast = coast;
        this.number_of_items = number_of_items;
        this.time_to_get_ready = time_to_get_ready;
    }

    public item_detail() {
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getItem_descriptions() {
        return item_descriptions;
    }

    public void setItem_descriptions(String item_descriptions) {
        this.item_descriptions = item_descriptions;
    }

    public String getCoast() {
        return coast;
    }

    public void setCoast(String coast) {
        this.coast = coast;
    }

    public String getNumber_of_items() {
        return number_of_items;
    }

    public void setNumber_of_items(String number_of_items) {
        this.number_of_items = number_of_items;
    }

    public String getTime_to_get_ready() {
        return time_to_get_ready;
    }

    public void setTime_to_get_ready(String time_to_get_ready) {
        this.time_to_get_ready = time_to_get_ready;
    }
}
