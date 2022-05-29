package Cruise.model.entity;

public class Liner {
    String name;
    String built;
    int roomCount;
    int max_people;
    Type type;
    int roomInner;
    int roomWithWindow;
    int roomWithBalcony;
    int roomLuxury;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuilt() {
        return built;
    }

    public void setBuilt(String built) {
        this.built = built;
    }

    public int getMax_people() {
        return max_people;
    }

    public void setMax_people(int max_people) {
        this.max_people = max_people;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public int getRoomInner() {
        return roomInner;
    }

    public void setRoomInner(int roomInner) {
        this.roomInner = roomInner;
    }

    public int getRoomWithWindow() {
        return roomWithWindow;
    }

    public void setRoomWithWindow(int roomWithWindow) {
        this.roomWithWindow = roomWithWindow;
    }

    public int getRoomWithBalcony() {
        return roomWithBalcony;
    }

    public void setRoomWithBalcony(int roomWithBalcony) {
        this.roomWithBalcony = roomWithBalcony;
    }

    public int getRoomLuxury() {
        return roomLuxury;
    }

    public void setRoomLuxury(int roomLuxury) {
        this.roomLuxury = roomLuxury;
    }

    public enum Type {
        STANDARD, PREMIUM, LUXURY, EXCLUSIVE;

        public boolean isStandard() {
            return this == STANDARD;
        }
        public boolean isPremium() {
            return this == PREMIUM;
        }

        public boolean isLuxury() {
            return this == LUXURY;
        }

        public boolean isExclusive() {
            return this == EXCLUSIVE;
        }

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}
