# 計算機程式課共同筆記
這裡留下學生曾經問過的問題所延伸的解釋筆記，歡迎共同補充。

## 目錄 
  1. [基本認識](#基本認識)
  1. [For-each照妖鏡](#for-each照妖鏡) 
  1. [Java的物件導向](#java的物件導向) 
  1. [物件住在哪裡？](#物件住在哪裡？)
  1. [Bitwise Operator的奇淫巧技](#Bitwise-Operator的奇淫巧技)
  1. [亂數夠不夠「亂」](#亂數夠不夠「亂」)
  1. [問題大雜燴](#問題大雜燴)
  
## 基本認識
這篇講在這門課會遇到的小東西。

### TAFree Online Judge 
[TAFree](https://github.com/TAFree)是一個線上程式批改系統專案，不為什麼而做，純屬為了「解放助教」（後來開發時期delay卡到提thesis proposal的時間，所以就順便寫成論文）。專案裡面目前有6個人，都是從曾經上過這門課的學生挖來的，裡面的人沒有多神，但是有很多熱忱。我們非常歡迎覺得系統很爛的人加入改良，或是砍掉重練也可以。

### 常見錯誤
*    編譯時期錯誤(Compiling Error)
就是語法錯誤，通常在Eclipse Run之前就會被Eclipse AST機制發現。
*    執行時期錯誤(Runtime Error)
        *    陣列索引太大或太小使得陣列的操作爆掉。
        *    需要輸入浮點數，卻用nextInt()。
        *    其他所有Run之後才出問題的事情。
*    [Compile and Runtime Errors in Java](http://www.cs.pomona.edu/classes/cs051/handouts/JavaErrorsExplained.pdf)

### Eclipse的小撇步
*   排版 
Ctrl+Shift+F可以縮排整份source code。
*   改類別名稱
游標移到該檔案點右鍵，選Refactor。

### Java SE 8的API文件
[](https://docs.oracle.com/javase/8/docs/api/)https://docs.oracle.com/javase/8/docs/api/

**[Back to top](#目錄)**

## For-each照妖鏡
解釋for-each和傳統for loop的差別。

### 用for列舉array有兩種方式
以下用範例說明這兩種用法要注意的事情
```
public class Test {
    public static void main(String[] args) {
        int[] numbers = { 1, 2, 3 };             
        for (Integer e : numbers) { // Enhanced for loop (for-each)
            System.out.print(e + " ");
            e = e * 2;
        }
        System.out.print("\n");                
        for (int i = 0; i < numbers.length; i++) { // What does for-each exactly do...
            Integer e = numbers[i];
            System.out.print(e + " ");
            e = e * 2;
        }
        System.out.print("\n");                
        for (int i = 0; i < numbers.length; i++) { // Traditional for loop
            System.out.print(numbers[i] + " ");
        }
        System.out.print("\n");
        for (int i = 0; i < numbers.length; i++) { // Traditional for loop for changing elements in array
            numbers[i] = numbers[i] * 2;
            System.out.print(numbers[i] + " ");
        }
    }
}
```

首先要分辨e跟numbers的元素是不是存在電腦裡的相同記憶體位址? 
若不是，當我們操作e就無法改變到numbers裡的元素了。 
在範例裡面:
第一個迴圈是enhanced for loop的用法。
第二個迴圈是把第一個迴圈打回原貌。
我們看第三個迴圈印出來的東西，可以知道foreach改變不了numbers，於是我們用第四個迴圈才能達到我們真正想要的操作 。

### 結論

如果是只想要「取出」陣列裡的值，把它們印出來或是存到別的變數裡，就可以用for-each；如果要「改變」陣列裡的值，就必須用傳統的for loop寫法。

**[Back to top](#目錄)**

## Java的物件導向
這篇只講Java，別種語言的物件導向實作方式「不一定」是這樣。
不負責任推諉結束，請繼續看下去。

### 要不要static?
static是相對於this的關鍵字。this用來代表屬於「這個物件」的成員，static用來代表屬於「這個類別」的成員。我們用個範例來解釋這一切。

範例輸出：
This can not be called in main
Dog1's breed: Labrador
Dog1's leg: 6
Dog2's breed: Labrador
Dog2's leg: 6
This can be called in main

範例源始碼：
```
public class Dog{
    
    public String name;
    static String breed="Dachshund"; // It is public 
    private static int leg=4;
    private String color;
    
    public Dog(){
        // Create a TestDog object
        TestDog testdog = new TestDog();
        testdog.callMe();
    }
    public Dog (String name){
        this.name = name;
    }
    public void setColor(String color){
        this.color = color;
    }
    public void setLeg(int n){
        // We can not use this.leg because it is static
        leg = leg + n;
    }
    
    public int getLeg(){
        // We can not use this.leg because it is static
        return leg;
    }
}

class TestDog{
    public static void main(String[] args){
        // Create one dog with argument
        Dog dog1 = new Dog("Bibi");
        dog1.breed = "Labrador";
        
        // Create another dog without argument
        Dog dog2 = new Dog();
        dog2.setLeg(2);
        
        System.out.println("Dog1's breed: " + dog1.breed);
        System.out.println("Dog1's leg: " + dog1.getLeg());
        
        System.out.println("Dog2's breed: " + dog2.breed);
        System.out.println("Dog2's leg: " + dog2.getLeg());
        callMeInMain();
        
    }
    
    public static void callMeInMain(){
        System.out.println("This can be called in main");
    }
    
    public void callMe(){
        System.out.println("This can not be called in main");
    }
    
}
```
然後你就有一堆疑問...
*    沒寫public或private就預設為public
例如breed被預設是public，所以TestDog的main()就可以直接用dog1.breed。
*    前置static的data field會屬於類別
我們看到 dog1更新了Dog的品種，而dog2更新了Dog的腿數，因此最後不論是dog1或是dog2的品種都是拉布拉多並且有6條腿，這就是因為我們是直接更新了Dog的static成員。
*    前置static的method裡面只能呼叫其他也前置static的method
TestDog的main()只能呼叫callMeInMain()卻不能呼叫callMe()，這是因為main()是static method所以只能用也是static的method。
*    前置static的method在其他類別中就沒有限制
我讓Dog()去呼叫TestDog的callMe()是沒有問題的。

### 有沒有寫abstract會怎樣? 
*   abstract class宣告abstract method的時候
abstract class的method可以是非抽象方法，但至少有一個抽象方法。
*    interface宣告method的時候
interface的method只能是「公開(public)」「抽象(abstract)」的方法。
*   一般class定義method的時候
No way。

### 有沒有寫@Override會怎樣?

*    @開頭的東西是「標註(Annotation)」，用來請編譯器幫我檢查東西。
*    @Override使用時機
我只想得到怕手滑打錯要覆寫的method名字，所以請編譯器幫我檢查。

### class的定義應該放哪裡?

回答這個問題之前，要先有兩個觀念:

**1. class定義裡面只有兩種東西: data field和method然後就沒有了**

(至少在這個課程不會遇到其他東西, 除非要寫內部類別)

如果你對這句話有疑慮，可能是因為沒搞懂:

*   main method是程式的進入點
*   constructor是method，例如:
```
class Ship{
    Ship(){} // 這東西叫建構子，它是一個沒有回傳東西的public method
}
```
**2. class定義放在哪裡，會導致誰才有權限使用它?**

接著，我們要開始回憶我們總共遇過哪幾種class

*   Java寫的，例如:  Scanner
Scanner放哪裡? Scanner提供我們各種應付標準輸入型態的method，所以可想而知它很大，很大就要壓縮，所以Scanner跟其他跟它類似的東西被壓縮成.jar檔，放在函式庫裡面。因為Eclipse有自動抓取電腦裡的JRE(JRE裡面有很多.Jar)，所以你可安心的在Eclipse使用Scanner。(不相信你可以看你開的project裡面有個JRE的資料夾)

*   我們自己寫的，例如: Rectangle
我們可以把它放在哪裡?

*   某個Class的定義裡嗎? 不行!  (別忘了1.說過class定義裡只有data field和method然後就沒有了!)

*   某個method裡嗎?  可以，但是那個class的定義必須要放在要new它的物件之前(因為要先讀得到它的定義嘛!)。可是這樣做會發生什麼事情?如果我們new很多不同的class，然後這些不同class又彼此有很複雜的牽涉關連，我們會搞混到底class的定義誰先誰後，所以**不建議這麼做**。

*   某個public class的定義外面:
這樣又分成兩種: 在同package下，放同一份檔案或是開另外一份檔案?
在回答這個問題之前，我們要先搞懂**class的權限修飾**
**就是說class會依據它前面是public、private、沒有寫等等的不同而決定誰可以用它**。
![](https://hackpad-attachments.s3.amazonaws.com/java_ntuce.hackpad.com_kCzTFjVavKa_p.635140_1466605472462_undefined)
    *    public class 全世界都可以用它。
    *    所以把所有class的權限調成public是最安心的嗎?**但是別忘了一個.java檔只能出現一個public class**。
    *    private class 只有 class自己本身可以用它自己。
    *    什麼意思? 如果要使用這個class，勢必要在別的class的某個method(例如main)去new它，對不對? 但是只有它自己才能用它自己，所以**當然沒辦法在別的class去new它**。
    *    沒有寫，只要是同package的其他class才可以使用它。 
    *    Protected，教繼承再講…

現在可以回答，class的定義要放哪裡了?
如果你希望同package下的其他class都可以用某個class，那麼那個某個class就可以**不要加上權限修飾**或是**加上public在另一份檔案**。

記得，**同一個package下不可以寫兩個一樣名字的class(無論是public或沒有寫權限)**。

(後記:開心的話也可以把自己寫的class壓成.jar匯入project的library喔!)

### 實踐物件導向的精神

*   繼承(Inherit)和介面(Interface)的存在是為了避免重複設計。
*   繼承跟介面不一樣的地方：
<table style="font-size:13px;cell-spacing: 0px; border-collapse: collapse;"><tr><td style="border:1px solid #999; min-width: 50px;height: 22px;line-height: 16px;padding: 0 4px 0 4px;" class="added"></td>
<td style="border:1px solid #999; min-width: 50px;height: 22px;line-height: 16px;padding: 0 4px 0 4px;" class="added">&#32380;&#25215;</td>
<td style="border:1px solid #999; min-width: 50px;height: 22px;line-height: 16px;padding: 0 4px 0 4px;" class="added">&#20171;&#38754;</td>
</tr>
<tr><td style="border:1px solid #999; min-width: 50px;height: 22px;line-height: 16px;padding: 0 4px 0 4px;" class="added">&#38364;&#37749;&#23383;</td>
<td style="border:1px solid #999; min-width: 50px;height: 22px;line-height: 16px;padding: 0 4px 0 4px;" class="added">extends</td>
<td style="border:1px solid #999; min-width: 50px;height: 22px;line-height: 16px;padding: 0 4px 0 4px;" class="added">implements</td>
</tr>
<tr><td style="border:1px solid #999; min-width: 50px;height: 22px;line-height: 16px;padding: 0 4px 0 4px;" class="added">&#38364;&#20418;</td>
<td style="border:1px solid #999; min-width: 50px;height: 22px;line-height: 16px;padding: 0 4px 0 4px;" class="added">&#26159;&#19968;&#31278;</td>
<td style="border:1px solid #999; min-width: 50px;height: 22px;line-height: 16px;padding: 0 4px 0 4px;" class="added">&#25793;&#26377;&#34892;&#28858;</td>
</tr>
<tr><td style="border:1px solid #999; min-width: 50px;height: 22px;line-height: 16px;padding: 0 4px 0 4px;" class="added">&#30333;&#35441;&#25991;</td>
<td style="border:1px solid #999; min-width: 50px;height: 22px;line-height: 16px;padding: 0 4px 0 4px;" class="added">&#21482;&#21487;&#20197;&#32380;&#25215;&#19968;&#20491;&#39006;&#21029;</td>
<td style="border:1px solid #999; min-width: 50px;height: 22px;line-height: 16px;padding: 0 4px 0 4px;" class="added">&#21487;&#20197;&#23526;&#20316;&#24456;&#22810;&#20491;&#20171;&#38754;</td>
</tr>
</table>

*   多型(Polymorphism)就是一個物件在執行時期可以被當成不只一種型別(類別)。
講個沒弄懂多型會發生Runtime Error或Compiling Error的例子：
```
public class RPG {
    public static void main(String[] args){
                
        SwordsMan role1 = new SwordsMan();
        Role role2 = new SwordsMan();
        SwordsMan role3 = new Role(); //編譯時期被靠北
                  
        Role role4 = new SwordsMan();
        SwordsMan role5 = role4; //編譯時期被靠北
        Role role6 = new SwordsMan();
        SwordsMan role7 = (SwordsMan)role6;
                   
        Role role8 = new Magician();
        SwordsMan role9 = (SwordsMan)role8; //編譯通過 但是執行時期被靠北
    
    }
}
```

### 推薦的書
[1] 林信良 (2014)。Java SE 8 技術手冊。GOTOP Information Inc。

**[Back to top](#目錄)**
