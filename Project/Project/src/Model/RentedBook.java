package Model;

import java.time.LocalDate;

public class RentedBook {

    private int id;
    private LocalDate rentedDate;
    private LocalDate lastReturnDate;
    private LocalDate returnDate;
    private int visitorId;
    private int copyOfBookId;

    public RentedBook(int id, LocalDate rentedDate, LocalDate lastReturnDate, LocalDate returnDate, int visitorId, int copyOfBookId) {
        this.id = id;
        this.rentedDate = rentedDate;
        this.lastReturnDate = lastReturnDate;
        this.returnDate = returnDate;
        this.visitorId = visitorId;
        this.copyOfBookId = copyOfBookId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getRentedDate() {
        return rentedDate;
    }

    public void setRentedDate(LocalDate rentedDate) {
        this.rentedDate = rentedDate;
    }

    public LocalDate getLastReturnDate() {
        return lastReturnDate;
    }

    public void setLastReturnDate(LocalDate lastReturnDate) {
        this.lastReturnDate = lastReturnDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(int visitorId) {
        this.visitorId = visitorId;
    }

    public int getCopyOfBookId() {
        return copyOfBookId;
    }

    public void setCopyOfBookId(int copyOfBookId) {
        this.copyOfBookId = copyOfBookId;
    }

    @Override
    public String toString() {
        return "RentedBook{" +
                "id=" + id +
                ", rentedDate=" + rentedDate +
                ", lastReturnDate=" + lastReturnDate +
                ", returnDate=" + returnDate +
                ", visitorId=" + visitorId +
                ", copyOfBookId=" + copyOfBookId +
                '}';
    }
}
