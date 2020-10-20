/**
 * 
 */
package com.automic.hpqc.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author sumitsamson
 * 
 */
@XmlRootElement(name = "Items")
public class Items {

    private ArrayList<Item> itemList;

    /**
     * @return the itemList
     */
    public ArrayList<Item> getItemList() {
        return itemList;
    }

    /**
     * @param itemList
     *            the itemList to set
     */
    @XmlElement(name = "Item")
    public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }

    public java.util.List<String> getItemValues() {

        java.util.List<String> valueList = new ArrayList<String>();
        for (Item item : this.itemList) {
            valueList.add(item.getValue());
        }

        return valueList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Items [itemList=");
        builder.append(itemList);
        builder.append("]");
        return builder.toString();
    }

}
