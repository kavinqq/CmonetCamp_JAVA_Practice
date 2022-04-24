public class Date {
    /**
     * 希望使用者能透過Builder 來初始化書的日期 而非建構子
     * 以預防 只想輸入 年和日 但是沒有月的狀況
     */
    public static class Builder{
        /**
         *  書的出版年
         */
        private int year;
        /**
         * 書的出版月
         */
        private int month;
        /**
         * 書的出版日
         */
        private int day;
        /**
         * 設定書的出版日
         * @param year 出版年
         * @return 回傳自己 再次設定年月日
         */
        public Builder setYear(int year){
            this.year = year;
            return this;
        }
        /**
         * 設定書的出版日
         * @param month 出版月
         * @return 回傳自己 再次設定年月日
         */
        public Builder setMonth(int month){
            this.month = month;
            return this;
        }

        /**
         * 設定書的出版日
         * @param day 出版日
         * @return 回傳自己 再次設定年月日
         */
        public Builder setDay(int day){
            this.day = day;
            return this;
        }
        /**
         * 書的已知 年 月 日 設定完成了
         * @return 回傳一個Date物件
         */
        public Date gen(){
            return new Date(year, month, day);
        }
    }
    private int year;    // 出版年
    private int month;   // 出版月
    private int day;     // 出版日
    /**
     * 希望使用者用Builder來建構，避免用建構子，所以改成private
     * @param year 出版年
     * @param month 出版月
     * @param day 出版日
     */
    private Date(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }
    /**
     * 拿到書的出版日
     * @return 書的出版日
     */
    public int getDay() {
        return day;
    }

    /**
     * 拿到書的出版年
     * @return 書的出版年
     */
    public int getYear(){
        return year;
    }
    /**
     * 拿到書的出版月
     * @return 書的出版月
     */
    public int getMonth(){
        return month;
    }
    /**
     * 比較兩個 Date內容是否一致
     * @param date 指定的出版日期
     * @return 一樣回傳true 不同 回傳false
     */
    public boolean compare(Date date)
    {
        if(getYear() == date.getYear() && getMonth() == date.getMonth() && getDay() == date.getDay())
        {
            return true;
        }
        return false;
    }

    /**
     * 檢查 書的出版日 是否在這段期間之內
     * @param start 開始的日期
     * @param end   結束的日期
     * @return 是否有在這段日期中
     */
    public boolean isInRange(Date start, Date end)
    {
        //查詢日期的年份 開始日期 和 結束日期 的年區間外 回傳false
        if(getYear() < start.getYear() || getYear() > end.getYear()){
            return false;
        }
        //查詢日期 開始日期 結束日期 都在同一年的
        if(getYear() == start.getYear() || getYear() == end.getYear()){
            //查詢日期月份在 開始與結束月份區間之外 回傳false
            if(getMonth() < start.getMonth() || getMonth() > getMonth()){
                return false;
            }
            //查詢日期 開始日期 結束日期 都在 同一年 同一月
            else if(getMonth() == start.getMonth() || getMonth() == end.getMonth()){
                //查詢日期 的 日 在 開始和結束日 區間之外
                if(getDay() < start.getDay() || getDay() > end.getDay())
                {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * 書本資訊(書名 作者 出版日期)
     * @return 回傳 書本資訊字串
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(" " +getYear() +"年 ");
        sb.append(" " +getMonth() +"月 ");
        sb.append(" " +getDay() +"日 ");
        return sb.toString();
    }
}
