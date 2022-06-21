package Cruise.model.entity;

import java.io.Serializable;
import java.util.Date;

public class UserRequest implements Serializable {
    String login;
    String cruiseName;
    int price;
    Date createTime;
    int countPeople;
    Status status;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCountPeople() {
        return countPeople;
    }

    public void setCountPeople(int countPeople) {
        this.countPeople = countPeople;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCruiseName() {
        return cruiseName;
    }

    public void setCruiseName(String cruiseName) {
        this.cruiseName = cruiseName;
    }

    public enum Status {
        CREATED, AVAILABLE, PAID, FINISHED;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }

        public int getId() {
            return ordinal();
        }

        public String getStatus() {
            return toString();
        }
    }
}
