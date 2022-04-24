public class Book {
    /**
     * 書名
     */
    private String bookName;
    /**
     * 出版日期
     */
    private Date date;
    /**
     * 作者
     */
    private String author;

    /**
     * 書的建構子
     * @param bookName 書名
     * @param date 出版日期
     * @param author 作者
     */
    public Book(String bookName, Date date, String author){
        this.bookName = bookName;
        this.date = date;
        this.author = author;
    }

    public void editBookName(String bookName){
        this.bookName = bookName;
    }

    public void editDate(Date date){
        this.date = date;
    }

    public void editAuthor(String author){
        this.author = author;
    }
    /**
     * @return 回傳書的書名
     */
    public String getName(){
        return bookName;
    }
    /**
     * @return 回傳書的作者
     */
    public String getAuthor(){
        return author;
    }
    /**
     * @return 回傳書的出版日期
     */
    public Date getDate(){
        return date;
    }
    public void edit(String bookName, Date date, String author){
        editBookName(bookName);
        editDate(date);
        editAuthor(author);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(" 書名: " + getName());
        sb.append(" 作者: " + getAuthor());
        sb.append(" 出版日: " + getDate());
        return sb.toString();
    }
}
