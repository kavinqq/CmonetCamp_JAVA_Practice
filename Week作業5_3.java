public class Library {
    /**
     * 用來儲存 圖書館書櫃 的陣列
     */
    private BookShelf[] bookShelvesArr;
    /**
     * 當前 圖書館總書櫃數
     */
    private int totalBookShelf;

    /**
     * 圖書館的建構子 一開始的沒有書櫃 要自己建
     */
    public Library(){
        totalBookShelf = 0;
        bookShelvesArr = new BookShelf[totalBookShelf];
    }

    /**
     * 更改 圖書館總書櫃數
     * @param totalBookShelf 更新過後的 圖書館總書櫃數
     */
    public void setTotalBookShelf(int totalBookShelf) {
        this.totalBookShelf = totalBookShelf;
    }

    /**
     * 圖書館目前有幾個書櫃
     * @return 圖書館總書櫃數
     */
    public int getTotalBookShelf(){
        return totalBookShelf;
    }

    /**
     * 建立一個新的書櫃
     *
     * @param bookshelfType 書櫃類別
     * @param maxStore 該書櫃最大藏書量
     */
    public void addBookShelves(String bookshelfType, int maxStore){
        //新的 總書櫃量
        int newSize = getTotalBookShelf() + 1;
        //建立一個新的 暫時BookShelf[] 大小為 原本的大小 + 1
        BookShelf[] tmpBookShelf = new BookShelf[newSize];
        //把 原本存在bookShelvesArr[] 中的 類別Book位址 一一 存到 暫時BookShelf[] 中 (getTotalBookShelf為舊的總書櫃數)
        for (int i = 0; i < getTotalBookShelf(); i++) {
            tmpBookShelf[i] = bookShelvesArr[i];
        }
        //把要新增書櫃的資料 存進 暫時的tmpBookShelf[]的最後一格
        tmpBookShelf[getTotalBookShelf()] = new BookShelf(bookshelfType, maxStore);
        //把Library的 總書架數 更新
        setTotalBookShelf(newSize);
        //最後把 bookShelvesArr 換成 tmpBookShelf
        bookShelvesArr = tmpBookShelf;
    }
    /**
     * 從圖書館 新增一本書 到 書櫃裡面
     * 首先 先檢查 bookShelvesArr[i] 放滿沒
     * 再檢查 這個書櫃 的 書櫃類別 是不是我們要放的
     *
     * @param bookName 書名
     * @param date 出版日期
     * @param author 作者
     * @param bookshelfType 存在哪類書櫃
     * @return 回傳 true表示 新增成功  false表示 失敗
     */
    public boolean hasAddedBook(String bookName, Date date, String author,String bookshelfType) {
        for(int i = 0; i < getTotalBookShelf(); i ++){
            if(!bookShelvesArr[i].isFull() && bookShelvesArr[i].getBookShelfType().equals(bookshelfType)){
                bookShelvesArr[i].addBook(bookName, date, author);
                return true;
            }
        }
        return false;
    }

    /**
     * 透過書櫃編號 來找書
     * @param bookShelfNum 書櫃編號
     * @return 回傳該編號 書櫃
     */
    public BookShelf findBookshelfByNum(int bookShelfNum){
        if(bookShelfNum - 1 < 0 && bookShelfNum > getTotalBookShelf()){
            return null;
        }
        else{
            return bookShelvesArr[bookShelfNum - 1];
        }
    }

    /**
     * 查詢 要印出的書櫃類別中 所有藏書資訊
     * 首先 1. bookShelvesArr[i].getTotalBook() 看看這個書櫃 有沒有藏書 也就是 totalBook > 0
     * 利用 && 的捷徑運算 如果沒藏書 就不用看後面
     * 再看看 2. bookShelvesArr[i].getBookShelfType() 這個書櫃的類別 是不是和 我們要找書櫃的類別一樣(藝術類/電腦類...)
     *
     * @param bookshelfType 要印出藏書資訊的 書櫃類別
     * @return 回傳該 書櫃類別的所有藏書資訊
     */
    public String printBooksByBookshelfType(String bookshelfType){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < getTotalBookShelf(); i ++){
            if(bookShelvesArr[i].getTotalBook() > 0 && bookShelvesArr[i].getBookShelfType().equals(bookshelfType)){
                sb.append("書櫃編號: " + (i + 1) + " ");
                sb.append(bookShelvesArr[i].showInfo());
            }
        }
        return sb.toString();
    }

    /**
     * 先行查詢 是否有這本書 能修改 以免使用者做無謂輸入浪費時間
     *  首先  1. findBookshelfByNum() 先看看有沒有這個書櫃
     *       2. 再看看 這個書櫃有沒有同名的書
     *
     *
     * @param bookShelfNum
     * @param bookName
     * @return 如果有這本書能改 true 沒有 false
     */
    public boolean canEditBook(int bookShelfNum,String bookName){
        if(findBookshelfByNum(bookShelfNum) != null && findBookshelfByNum(bookShelfNum).getBookByName(bookName) != null)
        {
            return true;
        }
        return false;
    }
    /**
     * findBookshelfByNum(bookShelfNum).getBookByName(oldBookName).edit(newBookName,date,author);
     * 首先 findBookshelfByNum() 會藉由 書櫃編號 去找相對應的書櫃
     * 然後再用 寫在 書櫃(BookShelf) 的方法 getBookByName() 去找到 書名相符合的書
     * 最後使用 寫在 書(Book)  的方法 edit() 去把自己的 資訊做更改
     *
     * @param bookShelfNum 編號多少的書櫃
     * @param oldBookName 原本的書名
     * @param newBookName 要改成什麼書名
     * @param date 要改成哪個時候出版
     * @param author 要改成哪位作者
     */
    public void editBook(int bookShelfNum,String oldBookName, String newBookName,Date date, String author){
       findBookshelfByNum(bookShelfNum).getBookByName(oldBookName).edit(newBookName,date,author);
    }

    /**
     * 輸出 該書櫃的 所有藏書資訊
     * @return 該書櫃的所有藏書資訊
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < getTotalBookShelf(); i ++){
            sb.append("\n書櫃編號: " + (i + 1) + " ");
            sb.append(bookShelvesArr[i].showInfo());
        }
        return sb.toString();
    }
}
