package com.booker.model;

public class Book {

	private Integer BookId;
	private String title;
	private String author;

	public Book()
	{

	}
	
	public Book(Integer newId, String newTitlte, String newAuthor)
	{
		this.BookId = newId;
		this.title = newTitlte;
		this.author = newAuthor;
	}

	public Integer getBookId() {
		return BookId;
	}

	public void setBookId(Integer bookId) {
		BookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((BookId == null) ? 0 : BookId.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (BookId == null) {
			if (other.BookId != null)
				return false;
		} else if (!BookId.equals(other.BookId))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book \n[BookId: " + BookId + ", title: " + title + ", author: " + author + "]";
	}
	
}
