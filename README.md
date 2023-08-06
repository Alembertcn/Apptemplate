**AppTemplate** 是一个App快速构建模板[Demo App](https://github.com/Alembertcn/Apptemplate/blob/main/android/app/release/app-release.apk)

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
<img src="https://github.com/Alembertcn/Apptemplate/blob/main/android/app/release/wx_pay.jpeg" width="98%">
</div>
