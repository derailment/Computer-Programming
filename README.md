# 計算機程式課共同筆記
這裡留下學生曾經問過的問題所延伸的解釋筆記，歡迎共同補充。

## 目錄 
  1. [基本認識](#基本認識)
  1. [For-each照妖鏡](#for-each照妖鏡) 
  1. [Java的物件導向](#Java的物件導向) 
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
