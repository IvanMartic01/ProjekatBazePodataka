package Model;

import java.time.LocalDate;

public class Book {

    private int id;
    private String name;
    private LocalDate publicationDate;
    private String description;
    private String editionNumber;
    private int numberOfPage;

    public Book(int id, String name, LocalDate publicationDate, String description, String editionNumber, int numberOfPage) {
        this.id = id;
        this.name = name;
        this.publicationDate = publicationDate;
        this.description = description;
        this.editionNumber = editionNumber;
        this.numberOfPage = numberOfPage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(String editionNumber) {
        this.editionNumber = editionNumber;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publicationDate=" + publicationDate +
                ", description='" + description + '\'' +
                ", editionNumber='" + editionNumber + '\'' +
                ", numberOfPage=" + numberOfPage +
                '}';
    }
}
