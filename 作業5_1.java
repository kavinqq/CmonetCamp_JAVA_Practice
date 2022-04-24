public class BookShelf {
   private String bookShelfType;
   /**
    * 書櫃
    */
   private Book[] books;
   /**
    * 總書量
    */
   private int totalBook = 0;
   /**
    * 該書櫃最大藏書量
    */
   private int size;

   /**
    * 依照指定大小 類型 建造書櫃
    * @param size 書櫃大小
    * @param bookShelfType 書櫃類型
    */
   public BookShelf(String bookShelfType, int size){
      this.size = size;
      this.bookShelfType = bookShelfType;
      books = new Book[size];
   }
   /**
    * 取得書櫃的分類
    * @return 該書櫃的分類
    */
   public String getBookShelfType(){
      return bookShelfType;
   }
   /**
    * 取得書櫃最大藏書量
    * @return 該書櫃最大藏書量
    */
   public int getSize(){
      return size;
   }
   /**
    * 查詢現在存幾本書
    *
    * @return 回傳目前存幾本
    */
   public int getTotalBook() {
      return totalBook;
   }
   /**
    * 書櫃是否滿了
    *
    * @return 滿了回傳 true 沒滿false
    */
   public boolean isFull() {
      if (totalBook >= size) {
         return true;
      }
      return false;
   }
   /**
    * 書櫃滿了 擴建一倍
    */
   public void doubleArr() {
      if (isFull()) {
         int newSize = size * 2;
         Book[] tmpBookShelf = new Book[newSize];
         //把所有的書 移動到新的書櫃
         for (int i = 0; i < totalBook; i++) {
            tmpBookShelf[i] = books[i];
         }
         //新書櫃 取代 舊書櫃
         books = tmpBookShelf;
      }
   }
   /**
    * 新增一本書到書櫃
    *
    * @param bookName 書名
    * @param date     出版日期
    * @param author   作者
    */
   public void addBook(String bookName, Date date, String author) {
      books[totalBook] = new Book(bookName, date, author);
      totalBook += 1;
   }

   /**
    * 從書櫃中根據書名找書
    * @param bookName 要尋找的書名
    *
    * @return 是否有這本書
    */
   public Book getBookByName(String bookName) {
      for (int i = 0; i < totalBook; i++) {
         if (books[i].getName().equals(bookName)) {
            return books[i];
         }
      }
      return null;
   }
   /**
    * 從書櫃中根據作者找書
    *
    * @param author 要尋找的作者
    */
   public void findBookByAuthor(String author) {
      int count = 0;
      for (int i = 0; i < totalBook; i++) {
         if (books[i].getAuthor().equals(author)) {
            count += 1;
            System.out.println(books[i].toString());
         }
      }
      if(count == 0){
         System.out.println("沒有資料!");
      }
   }

   /**
    * 從書櫃中根據出版日期找書
    *
    * @param date 指定的出版日期
    */
   public void findBookByDate(Date date) {
      int count = 0;
      for (int i = 0; i < totalBook; i++) {
         count += 1;
         if (books[i].getDate().compare(date)) {
            System.out.println(books[i].toString());
         }
      }
      if(count == 0){
         System.out.println("沒有資料!");
      }
   }
   /**
    * 根據 開始日期 和 結束日期 找尋書櫃中所有出版日期在這段期間當中的書
    *
    * @param start 開始日期
    * @param end   結束日期
    */
   public void findBookInDateRange(Date start, Date end) {
      int count = 0;
      for (int i = 0; i < totalBook; i++) {
         if (books[i].getDate().isInRange(start, end)) {
            count += 1;
            System.out.println(books[i].toString());
         }
      }
      if(count == 0){
         System.out.println("沒有資料!");
      }
   }
   /**
    *
    */
   public String showInfo(){
      StringBuilder sb = new StringBuilder();
      sb.append("書櫃分類: " + bookShelfType + " 藏書量: " + getTotalBook() + "\n");
      if(totalBook <= 0){
         sb.append("此書櫃現在是空的!");
      }
      else{
         for(int i = 0; i < totalBook; i++){
            sb.append(books[i].toString() + "\n");
         }
      }
      return sb.toString();
   }
}
