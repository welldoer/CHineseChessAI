# ChineseChessAI
用TDD方法来开发一版中国象棋

[![Build Status](https://travis-ci.com/welldoer/ChineseChessAI.svg?branch=master)](https://travis-ci.com/welldoer/ChineseChessAI)

【说明】本开发过程严重参考 [JavaScript中国象棋程序（0） - 前言](http://www.cnblogs.com/royhoo/p/6426394.html)



#### 待开发任务清单
~~1. 先添加基本的Rule规则类；~~

~~2. 将车、炮的简单走法检查从Piece类迁移到Rule类中；~~

~~3. 将马的简单走法检查从Piece类迁移到Rule类中；~~

~~4. 将相、士的简单走法检查从Piece类迁移到Rule类中；~~

~~5. 在Rule规则类中支持其它棋子的走法检查；~~

~~6. 替换PieceTest中的棋子走法测试；~~

~~6. 完整将旧的Piece走法检查改为Rule走法检查，并删除Piece中不再使用的走法检查方法；~~

7. 为了有效隔离GUI与非GUI测试，准备将GUI仅限制于ChessBoard及以上；

7.1. 添加Tiles类来存放原有的Positions的非GUI信息；

7.2. 支持从FEN类加载棋局信息至Tiles，故Tiles成为无GUI显示的完整象棋；

7.3. 后续再将ChessBoard基于Tiles来完成GUI显示；

8. 在Rule规则类中支持步法的生成；