package kr.co.ohjooyeo.vo;

public class BibleVO {
	private String book;
	private int chapter;
	private int section;
	private String contents;
	
	public BibleVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BibleVO(String book, int chapter, int section, String contents) {
		super();
		this.book = book;
		this.chapter = chapter;
		this.section = section;
		this.contents = contents;
	}

	public BibleVO(String book, int chapter, int section) {
		super();
		this.book = book;
		this.chapter = chapter;
		this.section = section;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public int getChapter() {
		return chapter;
	}

	public void setChapter(int chapter) {
		this.chapter = chapter;
	}

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "BibleVO [book=" + book + ", chapter=" + chapter + ", section=" + section + ", contents=" + contents
				+ "]";
	}
	
	
}
