# 計算機程式課共同筆記
這裡留下學生曾經問過的問題所延伸的解釋筆記，歡迎共同補充。

## 目錄 
  1. [基本認識](#基本認識)
  1. [For-each照妖鏡](#For-each照妖鏡) 
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
