package by.it.academy.toolbar;

import java.util.Date;

public class Info {
    private int id;
    private String type;
    private String number;
    private Date date;

    private Info(int id, String type, String number, Date date) {
        this.id = id;
        this.type = type;
        this.number = number;
        this.date = date;
    }

    public static class Builder {
        private int id;
        private String type;
        private String number;
        private Date date;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder setDate(Date date) {
            this.date = date;
            return this;
        }

        public Info build(){
            return new Info(id, type, number, date);
        }
    }
}
