# AppTemplate

![Image](app/src/main/ic_launcher-playstore.png)

[![Download](https://img.shields.io/badge/download-App-blue.svg)](https://raw.githubusercontent.com/jenly1314/AppTemplate/master/app/release/app-release.apk)
[![API](https://img.shields.io/badge/API-21%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=21)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://opensource.org/licenses/mit-license.php)
[![Blog](https://img.shields.io/badge/blog-Jenly-9933CC.svg)](https://jenly1314.github.io/)
[![QQGroup](https://img.shields.io/badge/QQGroup-20867961-blue.svg)](http://shang.qq.com/wpa/qunwpa?idkey=8fcc6a2f88552ea44b1411582c94fd124f7bb3ec227e2a400dbbfaad3dc2f5ad)

**AppTemplate** 是一个App快速构建模板 [Demo App](https://github.com/jenly1314/AppTemplate/raw/master/app/release/app-release.apk)

## 相关介绍

- 开发一款App时，前期一般都需要做很多的准备工作。包括但不限于：搭建项目主框架，写各种基类和分层，画界面，关联交互逻辑等。
- 而大部分App都有一些相通的主体和功能，包括但不限于：主界面、Banner、登录、注册、修改/重置密码、列表数据、Tab切换等。
- **AppTemplate** 的出现就是尽可能的将这些相通的一些主体功能写成通用的模板，大大的简化这些重复性的一些工作，从而更加快速的去开发实现一款App。
- 
## 架构设计 （最简单的方式实现组件化）
- 控制config.gradle 中modularName 当前需要跑的module 动态编译当前可运行项目 
- 这样做的好处，在于即使是组件化开发可以 依赖其他的组件做链调开发 不像传统组件化只能单独组件运行 不能选择性的同时运行 我们这样更为的灵活和实用
- 只抽取基础module使用的gradle 简洁干净  lib的通常是固定的最多依赖一下公共库 和版本号 后面看情况是否抽取
- lib_common 公共库需要全局引用 负责基本功能和 一些通用三方库的统一集成
> 当您的需求与 **AppTemplate** 中的模板有类似功能的时候，您可以通过简单的一些修改，就能快速的去实现您的功能。
>
> 当您的需求在中 **AppTemplate** 没有找到类似的模板时，你也可以基于 **AppTemplate** 已有的主体架构，很方便的去实现您的功能。
>
> 如果您有好的想法或建议，可以反馈给我。也可以一起参与到其中,提交Pull Request。和我一起去完善 **AppTemplate**。

` 
温馨提示：模板中写了一些示例，方便更友好的查看模板效果。如果想通过模板快速的实现相关类似功能，您可以通过查看 TODO 标记，快速定位一些需要处理的地方。
`

## 赞赏
如果您喜欢AppTemplate，或感觉AppTemplate帮助到了您，可以点右上角“Star”支持一下，您的支持就是我的动力，谢谢 :smiley:<p>
您也可以扫描下面的二维码，请作者喝杯咖啡 :coffee:
<div>
<img src="https://github.com/Alembertcn/Apptemplate/blob/main/android/app/release/wx_pay.png" width="100%">
</div>

## 关于我
Name: <a title="关于作者" href="https://jenly1314.github.io" target="_blank">Jenly</a>

Email: <a title="欢迎邮件与我交流" href="mailto:jenly1314@gmail.com" target="_blank">jenly1314#gmail.com</a> / <a title="给我发邮件" href="mailto:jenly1314@vip.qq.com" target="_blank">jenly1314#vip.qq.com</a>

CSDN: <a title="CSDN博客" href="http://blog.csdn.net/jenly121" target="_blank">jenly121</a>

CNBlogs: <a title="博客园" href="https://www.cnblogs.com/jenly" target="_blank">jenly</a>

GitHub: <a title="GitHub开源项目" href="https://github.com/jenly1314" target="_blank">jenly1314</a>

Gitee: <a title="Gitee开源项目" href="https://gitee.com/jenly1314" target="_blank">jenly1314</a>

加入QQ群: <a title="点击加入QQ群" href="http://shang.qq.com/wpa/qunwpa?idkey=8fcc6a2f88552ea44b1411582c94fd124f7bb3ec227e2a400dbbfaad3dc2f5ad" target="_blank">20867961</a>
   <div>
       <img src="https://jenly1314.github.io/image/jenly666.png">
       <img src="https://jenly1314.github.io/image/qqgourp.png">
   </div>
