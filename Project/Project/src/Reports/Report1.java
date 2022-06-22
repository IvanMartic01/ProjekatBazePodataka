package Reports;

import Model.Book;
import Model.CopyOfBook;
import Model.RentedBook;
import Model.Visitor;
import Repository.RentedBookRepository;
import Repository.VisitorRepository;
import Sevice.BookService;
import Sevice.CopyOfBookService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Report1 {


    private LocalDate startDate;
    private LocalDate endDate;


    public void generateReport() {
        try {

            getInput();

            VisitorRepository visitorRepository = new VisitorRepository();
            RentedBookRepository rentedBookRepository = new RentedBookRepository();

            ArrayList<Visitor> visitors = visitorRepository.getVisitors();
            ArrayList<RentedBook> rentedBooks = rentedBookRepository.getRentedBooks();

            for (Visitor visitor : visitors) {

                ArrayList<RentedBook> rentedBooksByVisitor = new ArrayList<>();
                for (RentedBook rentedBook : rentedBooks) {
                    if (visitor.getId() == rentedBook.getVisitorId()
                            && this.startDate.isBefore(rentedBook.getRentedDate())
                            && this.endDate.isAfter(rentedBook.getRentedDate())) {
                        rentedBooksByVisitor.add(rentedBook);
                    }
                }

                System.out.println(visitor);
                System.out.println();

                System.out.println("Rented books: ");
                CopyOfBookService copyOfBookService = new CopyOfBookService();
                BookService bookService = new BookService();
                for (RentedBook rentedBook : rentedBooksByVisitor) {
                    CopyOfBook copyOfBook = copyOfBookService.getCopyOfBook(rentedBook.getCopyOfBookId());
                    Book book = bookService.getBook(copyOfBook.getBookId());

                    System.out.println("********************************************");
                    System.out.println(rentedBook);
                    System.out.println(copyOfBook);
                    System.out.println(book);
                    System.out.println("********************************************");
                    System.out.println();
                }
                System.out.println("\n\n\n\n\n");
                System.out.println("Number of rented books: " + rentedBooksByVisitor.size());
                System.out.println("------------------------------------");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getInput() {

        while (true) {

            try {

                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter start date");
                String startDateString = scanner.nextLine();
                System.out.println("Enter end date");
                String endDateString = scanner.nextLine();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                this.startDate = LocalDate.parse(startDateString, formatter);
                this.endDate = LocalDate.parse(endDateString, formatter);

                break;

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
