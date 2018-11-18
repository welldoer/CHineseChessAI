# ChineseChessAI
用TDD方法来开发一版中国象棋

[![Build Status](https://travis-ci.com/welldoer/ChineseChessAI.svg?branch=master)](https://travis-ci.com/welldoer/ChineseChessAI)

【说明】本开发过程严重参考 [JavaScript中国象棋程序（0） - 前言](http://www.cnblogs.com/royhoo/p/6426394.html)



#### 待开发任务清单
0. 将车、炮、马、相的移动检查（含吃子、阻碍）复制到Rule中；

1. 添加Tiles类来存放原有的Positions的非GUI信息；

2. 支持从FEN类加载棋局信息至Tiles，故Tiles成为无GUI显示的完整象棋；

3. 后续再将ChessBoard基于Tiles来完成GUI显示；

4. 在Rule规则类中支持步法的生成；