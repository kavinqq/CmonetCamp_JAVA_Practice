/*
1. 新增一個學生(空間不夠請使用doubleArr)
    doubleArr的意思是 陣列空間不夠用 把 找一個兩倍大的記憶體空間 替換
2. 印出指定"座號"的學生成績
3. 依照成績由大到小排序學生
4. 依照座號由小到大排序學生
5. 更改指定"座號"的學生成績
6. 結束程式
*/
public class StudentArray {
    /**
     * array students is full,so new double size array replace it
     * @param smallArr Student[] students in main() in class StudentManagerSystem
     * @return return new array locate in heap
     */
    public Student[] doubleArr(Student[] smallArr)
    {
        Student[] biggerArr = new Student[smallArr.length * 2];
        System.arraycopy(smallArr, 0, biggerArr, 0, smallArr.length);
        return biggerArr;
    }

    /**
     *
     * @param seat user specified seat attribute
     * @param studentArr store all student data in main()
     * @return specified student data
     */
    public String getStudentBySeat(int seat, Student[] studentArr)
    {
        for (Student student : studentArr) {
            if (student.getSeat() == seat) {
                return student.toString();
            }
        }
        return "沒有符合座號的資料!";
    }

    public String setStudentScoreBySeat(int seat, int score, Student[] studentArr)
    {
        for (Student student : studentArr) {
            if (student.getSeat() == seat) {
                student.setScore(score);
                return "該資料成功修改為:" + student;
            }
        }
        return "沒有符合座號的資料!";
    }

    /**
     * studentArr will sort by score order by descend
     * @param studentArr store all student data in main()
     * @param countStudent total student number
     */

    public void sortByScoreDESC(Student[] studentArr, int countStudent)
    {
        for(int i = 0; i < countStudent - 1; i ++)
        {
            for(int j = 0; j < countStudent - 1 - i; j ++)
            {
                if(studentArr[j].getScore() < studentArr[j + 1].getScore())
                {
                    swap(studentArr, j, j + 1);
                }
            }
        }
    }

    /**
     * studentArr will sort by seat order by ascend
     * @param studentArr  store all student data in main()
     * @param countStudent total student number
     */
    public void sortBySeatASC(Student[] studentArr, int countStudent)
    {
        for(int nowIndex = 0; nowIndex < countStudent - 1; nowIndex ++)
        {
            int minIndex = nowIndex,
                minSeat = studentArr[nowIndex].getSeat(),
                nextIndex = nowIndex + 1;
            while(nextIndex < countStudent && studentArr[nextIndex].getSeat() < minSeat)
            {
                nowIndex = nextIndex;
                minSeat = studentArr[nowIndex].getSeat();
                nextIndex++;
            }
            if(nowIndex != minIndex) {
                swap(studentArr, nowIndex, minIndex);
            }
        }
    }

    /**
     * to swap two data which was stored in studentsArr
     * @param studentsArr store all student data in main()
     */
    private void swap(Student[] studentsArr, int firstIndex, int secondIndex)
    {
        Student tmpStudent = studentsArr[firstIndex];
        studentsArr[firstIndex] = studentsArr[secondIndex];
        studentsArr[secondIndex] = tmpStudent;
    }
}
