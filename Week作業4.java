public class CommandSolver {
    /*
    Q:要求的指令 (CommandLine模擬 傳統電腦桌面 只是現在圖像化)
    1. ls : 檢視所在目錄底下的所有檔名與目錄夾名稱 [linux][windows 是 dir]
    2. cd <資料夾名稱>: 進入某一個資料夾中.
    3. cd.. : 回上一層目錄 [linux 只吃 cd ..][windows 吃cd.. 和 cd ..]
    4. touch<檔名>: 建立一個空檔案(註: 並非真的建立檔案, 僅僅是新增名稱而已)
    5. mkdir<資料夾名稱> : 建立一個新的資料夾.
    6. search<關鍵字>: 根據關鍵字搜尋目錄與其子目錄夾中所有包含關鍵字的檔案與資料夾(大小寫相同)
    */

    /*
        新增一個類別之後的步驟
        1. 你要先判斷 這個類別 他有沒有需要存取的狀態
            扣分案例: 老師教大家拉一個Input類別出來, 有人會開始就先加屬性 可是用不到 => 扣分

        程式就是一步一步設計 => 設計當中發現瑕疵遺漏(例如這個東西: 每次都傳 不好 => 抽出來) => refactor(重構)
                          => 需求改變 => refactor(重構)

        回到這個類
        CommandSolver => 只處理Command就好 => 真正的作動由 FileSystem來處理

        那麼我要如何設計 這個專門處理指令的地方? 要判斷什麼?
        1. 這個指令有沒有效 / 輸入是否正確 / 有沒有這個指令
        2. 指令參數是否正確(Ex: cd 兩個參數, 我要touch一個檔案 但沒有給檔名...etc.)
        3. 最後要告訴FileSystem什麼東西? 要執行哪個動作?? 要如何定義動作有哪些??? 用什麼定義???? ->用enum
    */

    public enum Command{
        LS("ls", 0),
        CD("cd", 1),
        TOUCH("touch", 1),
        MKDIR("mkdir", 1),
        SEARCH("search", 1);

        private final String value;
        private final int argCount;

        Command(String value, int argCount){
            this.value = value;
            this.argCount = argCount;
        }

        // 我們需要寫一個getValue()嗎?? => 因為他是內部類別, CommandSolver內可以直接拿到value
        // getValue()是要讓外面也能拿到 => 但外面根本不需要拿到這個, 所以不用寫getValue();
    }

    public static class C {
        private Command command;
        private String[] args;

        // 建構子private,我不希望外面可以創建這個東西, 他只是一個Command, 只能經過CommandSolver來創建他
        private C(Command command, String[] args){
            this.command = command;
            this.args = args;
        }

        public Command getCommand(){
            return command;
        }

        public String[] getArgs(){
            return args;
        }
    }




    // 老師的一步步設計想法:
    // 1. 目前的假設就是 CommandSolver不會handle屬性 => 把對外的接口做成靜態的
    // 2. 還不確定回傳什麼 => 先設void
    // 3. 想個好名字
    // 4. 傳進來什麼東西

    public static C input(String str){
        // 進來之後要先幹嘛??
        // 1. 你傳進來什麼東東?? => 一行指令  (Ex: dir / cd Videos ..)
        // 2. 你拿到這個要幹嘛? => 去判斷 上面提到的 這個指令有沒有效 / 輸入是否正確 / 有沒有這個指令
        // 3. 由於指令都是用 空格 來做間隔 => 所以我們用 .spilt(空格) 去分離
        String[] arr = str.split(" ");

        Command[] commands = Command.values();

        // 如果指令是 LS 那麼 參數切開後的arr長度 應該要是1
        // 如果是其他指令 應該要是2 指令 + 名稱
        // 那麼 這些動作感覺很重複 => 把參數存在 enum裡面 感覺比較好
        for (int i = 0; i < commands.length; i ++){
            if(arr[0].equals(commands[i].value) && arr.length - 1 == commands[i].argCount){
                // 得到啥?? 一個Command
                // 只把Command 傳回去 外面沒辦法拿到參數怎辦? => 不怎辦, 因為我本來就是要設計成 純粹處理指令的地方
                // 既然我不能一次傳兩個東西回去 那怎辦? => 打包起來 => 開一個內部類別出來.(上面)

                C c;
                String[] args = null;
                // 重新打包參數
                if(commands[i].argCount > 0){
                    args = new String[commands[i].argCount];
                    for(int n = 0 ; n < args.length; n++){
                        args[n] = arr[n + 1];
                    }
                }
                return new C(commands[i], args);
            }
        }
        return null;
    }
}
