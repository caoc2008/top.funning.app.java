package top.funning.app.bean;

import top.knxy.library.utils.TextUtils;

import java.util.ArrayList;

public class Page {

    private ArrayList<Item> items;
    private Item previous;
    private Item next;

    public Page(String pStr) {
        int page = 1;
        if (!TextUtils.isEmpty(pStr) && TextUtils.isNumeric(pStr)) {
            page = Integer.valueOf(pStr);
        }

        if (page < 1) {
            page = 1;
        }

        items = new ArrayList<>(3);
        if (page == 1) {
            previous = new Item(false, -1);
            items.add(new Item(true, 1));
            items.add(new Item(false, 2));
            items.add(new Item(false, 3));
            next = new Item(true, 2);
        } else {
            previous = new Item(true, page - 1);
            items.add(new Item(false, page - 1));
            items.add(new Item(true, page));
            items.add(new Item(false, page + 1));
            next = new Item(true, page + 1);
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public Item getPrevious() {
        return previous;
    }

    public void setPrevious(Item previous) {
        this.previous = previous;
    }

    public Item getNext() {
        return next;
    }

    public void setNext(Item next) {
        this.next = next;
    }

    public static class Item {
        private boolean active;
        private int value;

        public Item(boolean active, int value) {
            this.active = active;
            this.value = value;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
