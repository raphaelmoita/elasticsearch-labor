package org.moita.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

public class Book{

    private String title;
    private String genre;
    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) &&
                Objects.equals(genre, book.genre) &&
                Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, genre, author);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("title", title)
                .append("genre", genre)
                .append("author", author)
                .toString();
    }

    public static final class Builder {
        private String title;
        private String genre;
        private String author;

        private Builder() {
        }

        public static Builder aBook() {
            return new Builder();
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public Builder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public Book build() {
            Book book = new Book();
            book.setTitle(title);
            book.setGenre(genre);
            book.setAuthor(author);
            return book;
        }
    }
}
