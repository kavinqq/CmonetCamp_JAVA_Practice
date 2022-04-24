import java.security.SecureRandom;

public class DiceGame {
    /**
     * 比賽結果列舉
     */
    public enum Result{
        PlayerA_Win,
        PlayerB_Win,
        Tie_Game
    }

    /**
     * 比賽規則的列舉
     */
    public enum WinRule{
        Bigger(1,"BiggerWin"),
        Smaller(2,"SmallerWin"),
        MoreSamePoints(3,"MoreSamePointsWin");
        //第幾項規則
        private final int ruleOption;
        //規則名稱
        private final String rule;
        //列舉建構子
        private WinRule(int ruleOption, String rule){
            this.ruleOption = ruleOption;
            this.rule = rule;
        }
        //回傳 規則名稱字串
        public String getRule(){
            return rule;
        }
        //讓使用者在Main中 可以透過 輸入數字來選擇 規則
        public static WinRule ruleOptionOf(int ruleOption){
            //走訪列舉的每個值 如果他的 ruleOption 和 使用則選擇的相同，回傳他的名稱
            for(WinRule winRule: values()){
                if(winRule.ruleOption == ruleOption){
                    return winRule;
                }
            }
            return WinRule.Bigger;
        }
    }
    private final WinRule winRule;
    /**
     * 玩家數量為2 寫死
     */
    private final int NUMBERS_OF_PLAYER = 2;
    /**
     * 骰子的數量
     */
    private final int diceNum;
    /**
     * 骰子的最大點數
     */
    private final static int MAX_DICE_POINT = 6;
    /**
     * 進行的回合數
     */
    private int round;
    /**
     * 用來記錄 兩人積分的陣列
     */
    private final int[][] record;

    /**
     * 留一個 計算總和的接口 (因為計算點數一樣要跑回圈，只是計算總和的東西不同 1.所有點數 2.最大相同點數)
     */
    public interface Calculate_Sum{
        public int sum(int[] record);
    }

    /**
     * 留一個 比較的接口 (最後判斷勝負時使用，因為點數大贏 和 點數小贏 只差在 > 和 <不同， 所以抽出來)
     */
    public interface Comparator{
        public int compare(int playerA, int playerB);
    }

    /**
     * 骰子遊戲建構子 1.初始化有幾顆骰子 / 獲勝規則 / 回合數 2.新增一個紀錄點數陣列[幾個玩家][骰子點數最大幾點]
     * @param diceNum 幾顆骰子
     * @param winRule 獲勝規則
     * @param round 回合數
     */
    public DiceGame(int diceNum, WinRule winRule, int round){
        this.diceNum = diceNum;
        this.winRule = winRule;
        this.round = round;
        record = new int[NUMBERS_OF_PLAYER][MAX_DICE_POINT];
    }


    /**
     * 骰骰子 第一層for  幾回合  第二層for  有幾顆骰子
     */
    public void rolling(){
        for(int i = 0; i < round; i++){
            for(int j = 0; j < diceNum; j++){
                int randomNum = random();
                record[0][randomNum - 1]++;
                randomNum = random();
                record[1][randomNum - 1]++;
            }
        }
    }

    /**
     * 用來隨機產生一個 骰子點數範圍內的整數
     * @return 範圍內的整數(1 ~ 6)
     */
    public int random(){
        //相較於Random類，SecureRandom物件生產的隨機數是Unpredictable，減少被惡意程式員預測的不安全性。
        SecureRandom random = new SecureRandom();
        //產生 0 ~ 6以下(不包含6) 的 整數
        return random.nextInt(6) + 1;
    }

    /**
     * 用來拿取 一顆骰子最大點數是多少
     * @return 一顆骰子的最大點數
     */
    public static int getMaxDicePoint(){
        return MAX_DICE_POINT;
    }

    /**
     * 根據 贏的規則 來判斷 要使用 哪一種 Comparator 和 Calculate_Sum
     * @param winRule 勝利條件
     * @param comparator 該勝利條件的比較方法
     * @param sum 該勝利條件計算雙方點數的方法
     * @return A贏 或 B贏 或 平手
     */
    public String result(WinRule winRule, Comparator comparator, Calculate_Sum sum){
        StringBuilder sb = new StringBuilder();
        int compareA = sum.sum(record[0]),
            compareB = sum.sum(record[1]);

        if(winRule.getRule().equals("MoreSamePointsWin")){
            sb.append("PlayerA 的  最大相同點數數為: ").append(compareA).append("\n");
            sb.append("PlayerB 的  最大相同點數數為: ").append(compareB).append("\n");
        }
        else{
            sb.append("PlayerA 的  總和點數數為: ").append(compareA).append("\n");
            sb.append("PlayerB 的  總和點數數為: ").append(compareB).append("\n");
        }

        if(comparator.compare(compareA, compareB) == 0){
            sb.append(Result.Tie_Game);
        }
        else if(comparator.compare(compareA, compareB) > 0){
            sb.append(Result.PlayerA_Win);
        }
        else{
            sb.append(Result.PlayerB_Win);
        }
        return sb.toString();
    }

    /**
     * 拿到 獲勝規則的名稱字串
     * @return 獲勝規則名稱
     */
    public String getWinRule(){
        return winRule.getRule();
    }
}
