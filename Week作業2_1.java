/*
撰寫一個類別 Student
Student有四個屬性 座號(int) 班級(String) 姓名(String) 成績(int)
Student 的方法請自行添加

可以讓使用者選擇功能
1. 新增一個學生(空間不夠請使用doubleArr)
2. 印出指定"座號"的學生成績
3. 依照成績由大到小排序學生
4. 依照座號由小到大排序學生
5. 更改指定"座號"的學生成績
6. 結束程式
 */

public class Student
{
    private int seat;
    private String schoolClass;
    private String name;
    private int score;
    //Student's constructor
    public Student(int seat, String schoolClass, String name, int score)
    {
        setSeat(seat);
        this.schoolClass = schoolClass;
        this.name = name;
        setScore(score);
    }
    //set value method
    private void setSeat(int seat)
    {
        if(seat > 0) {
            this.seat = seat;
        }
        else{
            seat = 0;
        }
    }
    public void setSchoolClass(String schoolClass)
    {
        this.schoolClass = schoolClass;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setScore(int score)
    {
        this.score = score;
    }
    //get value method
    public int getSeat()
    {
        return seat;
    }
    public String getSchoolClass()
    {
        return schoolClass;
    }
    public String getName()
    {
        return name;
    }
    public int getScore()
    {
        return score;
    }

    @Override
    public String toString()
    {
        return "學生班級: " + getSchoolClass() + "  座號: " + getSeat()+ "  姓名: " + getName() + " 成績: " + getScore();
    }
}
