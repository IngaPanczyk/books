package com.kodilla.books;

import com.kodilla.books.domain.Book;
import com.kodilla.books.grafs.Graf;
import com.kodilla.books.service.BookService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;


@Route
public class MainView extends VerticalLayout {

    Graf graf = new Graf();

    private BookService bookService = BookService.getInstance();
    private Grid grid = new Grid<>(Book.class);
    Image image = new Image(graf.createGraf(),"Graf");

    //Pole wyszukiwania
    private TextField filter = new TextField();

    //Formularz
  //  private BookForm form = new BookForm(this);

    //Nowe pozycje
    private Button addNewBook = new Button("Add new book");

    public MainView(/*Graf drowGraf*/) {

        //this.graf = drowGraf;

        filter.setPlaceholder("Filter by title...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        grid.setColumns("title", "author", "publicationYear", "type");

        addNewBook.addClickListener(e -> {
            grid.asSingleSelect().clear();
          //  form.setBook(new Book());
        });

        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewBook,image);

     //  HorizontalLayout mainContent = new HorizontalLayout(grid, form);
      //  mainContent.setSizeFull();
     //   grid.setSizeFull();

     //   add(toolbar, mainContent);
     //   form.setBook(null);
        setSizeFull();
        refresh();

       // grid.asSingleSelect().addValueChangeListener(event -> form.setBook((Book) grid.asSingleSelect().getValue()));
    }

    public void refresh() {
        grid.setItems(bookService.getBooks());
    }

    private void update() {
        grid.setItems(bookService.findByTitle(filter.getValue()));
    }

}
