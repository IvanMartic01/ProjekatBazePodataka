package Model;

public class CopyOfBook {
    private int id;
    private String stateComment;
    private float purchasePrice;
    private int libraryId;
    private int bookId;

    public CopyOfBook(int id, String stateComment, float purchasePrice, int libraryId, int bookId) {

        this.id = id;
        this.stateComment = stateComment;
        this.purchasePrice = purchasePrice;
        this.libraryId = libraryId;
        this.bookId = bookId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStateComment() {
        return stateComment;
    }

    public void setStateComment(String stateComment) {
        this.stateComment = stateComment;
    }

    public float getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "CopyOfBook{" +
                "id=" + id +
                ", stateComment='" + stateComment + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", libraryId=" + libraryId +
                ", bookId=" + bookId +
                '}';
    }
}
