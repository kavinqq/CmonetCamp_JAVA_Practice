import java.security.SecureRandom;


public class Slime {
    /**
     * 寫一個列舉 裡面存有史萊姆初始技能庫
     * (note: 1. )
     */
    public enum Skill{
        /*
        Java官網: An enum type is a special data type that enables for a variable to be a set of predefined constants.
        HEAL為 常數值1 → IDE會自動加上存取修飾詞 public static final
        */
        HEAL(1,"治癒",40),
        NORMAL_ATK(2,"普通攻擊", 1),
        CRITICAL_ATK(3,"會心一擊",1.5);
        /**
         * 該技能的選項編號
         */
        private final int option;
        /**
         * 該技能的 中文名
         */
        private final String skillName;
        /**
         * 該技能產生的效果數值
         */
        private final double skillEffect;

        /**
         * enum的建構子
         * @param option 第幾號技能
         * @param skillName 技能中文名
         * @param skillEffect 技能效果數值
         */
        private Skill(int option, String skillName,double skillEffect){
            this.option = option;
            this.skillName = skillName;
            this.skillEffect = skillEffect;
        }

        /**
         * 拿取一個 Skill內的一個常數值
         * @param option 技能的選項
         * @return Skill內的一個常數值
         */
        public static Skill getSkill(int option){
            //values() method can be used to return all values present inside the enum.
            for(Skill skill: values()){
                if(skill.option == option){
                    return skill;
                }
            }
            //預設回傳 HEAL
            return Skill.HEAL;
        }
        // 拿取該技能效果數值
        public double getSkillEffect(){
            return skillEffect;
        }
        // 拿取該技能中文名字
        public String getSkillName(){return skillName;}
    }

    /**
     * 定義 史萊姆的 生命值 產生的上下限
     * 定義 史萊姆的 攻擊力 產生的上下限
     * 定義 史萊姆的 最多有幾個技能
     */
    private static final int MAX_HP = 200;
    private static final int MIN_HP = 50;
    private static final int MAX_ATK = 80;
    private static final int MIN_ATK = 40;
    private static final int MAX_SKILL = 2;
    /**
     * 生命值
     */
    private int hp;
    /**
     * 攻擊力
     */
    private final int atk;
    /**
     * 現在已經有幾個技能
     */
    private int skillCount;
    /**
     * 該史萊姆編號
     */
    private final int num;
    /**
     * 現在總共有幾隻史萊姆
     */
    private static int serialNum = 1;
    /**
     * 史萊姆的技能欄位
     */
    private final Skill[] skills;

    SecureRandom secureRandom = new SecureRandom();

    public Slime(){
        //編號 為 所有史萊姆產生的流水號
        num = serialNum;
        //流水號 + 1
        serialNum++;
        //技能數 初始化為 0
        skillCount = 0;
        //隨機產生 生命值
        hp = secureRandom.nextInt(MAX_HP - MIN_HP + 1 ) + MIN_HP;
        //隨機產生 攻擊力
        atk = secureRandom.nextInt(MAX_ATK - MIN_ATK + 1 ) + MIN_ATK;
        //配給技能欄 型態為Skill 長度為MAX_SKILL 的連續記憶體空間
        skills = new Skill[MAX_SKILL];
        //自動產生 MAX_SKILL個技能
        autoGenSkill(MAX_SKILL);
    }

    /**
     * 獲取這一個史萊姆物件的編號
     * @return 編號
     */
    public int getNum(){
        return num;
    }

    /**
     * 設定這一個史萊姆物件的血量
     * @param number 血量增加值
     */
    public void setHp(int number){
        hp -= number;
    }

    /**
     * 獲取這一個史萊姆物件的生命值
     * @return 生命值
     */
    public int getHp(){
        return hp;
    }

    /**
     * 判斷是否能夠產生這一個技能 (不能重複)
     * @param newSkill 要給這個史萊姆物件產生的技能
     * @return ture 可以產生 false 不能產生
     */
    public boolean canGenTheSkill(Skill newSkill){
        //跑一次 這個史萊姆物件的 技能欄
        for(Skill skill: skills){
            //如果有null 表示有空格 && 檢查到空格前 沒有相同的技能
            if(skill == null){
                return true;
            }
            //已經有這個技能了
            else if(skill == newSkill){
                return false;
            }
        }
        //預設不行
        return false;
    }

    /**
     * 自動替初始史萊姆物件產生技能
     * @param MAX_SKILL 史萊姆物件最多能有幾個技能
     */
    public void autoGenSkill(int MAX_SKILL){
        for(int i = 0; i < MAX_SKILL; i ++){
            int option;
            Skill randomSkill;
            do {
                //隨機產生一個數字(1 ~ enum Skill 常數值量)
                option = secureRandom.nextInt(3) + 1;
                //利用getSkill 從 enum Skill 隨機抓一個技能
                randomSkill = Skill.getSkill(option);
                //抓到能產生為止
            }while(!canGenTheSkill(randomSkill));
            //成功產生一個後 把它放進去 存放技能的陣列(技能欄)， 技能陣列index++
            skills[skillCount++] = randomSkill;
        }
    }

    /**
     * 計算技能產生的實際數值
     * @param skill 施放的技能
     * @return 技能對HP造成的實際數值
     */
    public int calculateByRandomSkill(Skill skill){
        if(skill == Skill.HEAL){
            return (int)(skill.getSkillEffect() * - 1);
        }
        else{
            return (int)(skill.getSkillEffect() * atk);
        }
    }

    /**
     * 隨機從 史萊姆的技能欄 抓一個技能施放
     * @return 隨機一種該史萊姆會的技能
     */
    public Skill randomChooseSkill(){
        SecureRandom random = new SecureRandom();
        //要注意一下條件值:  .nextInt()是回傳 0 ~ n ( 不 包 含 n !)
        int randomNum = random.nextInt(skills.length);
        return skills[randomNum];
    }

    /**
     * 顯示該史萊姆的資訊
     * @return 史萊姆的 1.編號 2.HP 3.ATK  4.技能欄
     */
    public String showInfo(){
        StringBuilder sb = new StringBuilder();
        int count = 1;
        sb.append("我是史萊姆 ").append(num).append(" 號!").append("\n");
        sb.append("我的生命值有: ").append(hp).append("  攻擊力有: ").append(atk).append("\n");
        sb.append("我的技能有: ").append("\n");
        for(Skill skill: skills){
            sb.append(count).append(". ").append(skill.getSkillName()).append("\n");
            count ++;
        }
        return sb.toString();
    }
}
