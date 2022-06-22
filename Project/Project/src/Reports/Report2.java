package Reports;

import Model.CopyOfBook;
import Model.Library;
import Model.RentedBook;
import Model.Visitor;
import Repository.LibraryRepository;
import Repository.RentedBookRepository;
import Sevice.BookService;
import Sevice.CopyOfBookService;
import Sevice.VisitorService;

import java.util.ArrayList;
import java.util.HashMap;

public class Report2 {

    public void generateReport() {

        try {
            LibraryRepository libraryRepository = new LibraryRepository();
            RentedBookRepository rentedBookRepository = new RentedBookRepository();

            ArrayList<Library> libraries = libraryRepository.getLibraries();
            ArrayList<RentedBook> rentedBooks = rentedBookRepository.getRentedBooks();

            for (Library library : libraries) {

                ArrayList<Visitor> obligatedVisitors = new ArrayList<>();
                HashMap<Integer, ArrayList<CopyOfBook>> rentedBooksInLibraryByUser = new HashMap<>();

                System.out.println(library);
                System.out.println("Visitors who must return the book:");
                System.out.println("\n");
                for (RentedBook rentedBook : rentedBooks) {
                    if (rentedBook.getReturnDate() != null) {
                        CopyOfBookService copyOfBookService = new CopyOfBookService();
                        CopyOfBook copyOfBook = copyOfBookService.getCopyOfBook(rentedBook.getCopyOfBookId());

                        if (copyOfBook.getLibraryId() == library.getId()) {

                            VisitorService visitorService = new VisitorService();
                            Visitor visitor = visitorService.getVisitor(rentedBook.getVisitorId());

                            if (!containsVisitor(obligatedVisitors, visitor)) {
                                obligatedVisitors.add(visitor);
                                if (rentedBooksInLibraryByUser.containsKey(visitor.getId())) {
                                    ArrayList<CopyOfBook> rentedBooksByUser = rentedBooksInLibraryByUser.get(visitor.getId());
                                    rentedBooksByUser.add(copyOfBook);
                                    rentedBooksInLibraryByUser.replace(visitor.getId(), rentedBooksByUser);
                                } else {
                                    ArrayList<CopyOfBook> rentedBooksByUser = new ArrayList<>();
                                    rentedBooksByUser.add(copyOfBook);
                                    rentedBooksInLibraryByUser.put(visitor.getId(), rentedBooksByUser);
                                }

                            }

                        }
                    }
                }
                displayVisitorsRentedBookData(obligatedVisitors, rentedBooksInLibraryByUser);
                System.out.println("\n");
                System.out.println("Number of visitors who must return the book: " + obligatedVisitors.size());
                System.out.println("----------------------------------------------------------------------------------------");
                System.out.println("\n\n\n\n\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayVisitorsRentedBookData(ArrayList<Visitor> obligatedVisitors, HashMap<Integer, ArrayList<CopyOfBook>> rentedBooksInLibraryByUser) {
        try {
            for (Visitor visitor : obligatedVisitors) {
                System.out.println(visitor);
                ArrayList<CopyOfBook> rentedBooksByUser = rentedBooksInLibraryByUser.get(visitor.getId());
                System.out.println("Rented books: ");
                System.out.println("********************************************************************************************");
                for (CopyOfBook copyOfBook : rentedBooksByUser) {
                    BookService bookService = new BookService();
                    System.out.println(copyOfBook + " " + bookService.getBook(copyOfBook.getBookId()));
                }
                System.out.println("********************************************************************************************");
                System.out.println();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private boolean containsVisitor(ArrayList<Visitor> c, Visitor visitor) {
        for (Visitor o : c) {
            if (o != null && o.getId() == (visitor.getId())) {
                return true;
            }
        }
        return false;
    }
}
