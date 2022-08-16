## 使用方法

1. 下载后放置于`Mirai Console`的`plugins`目录下
2. 启动`Mirai Console`
3. 在`Mirai Console`通过`permission`命令为插件添加权限
4. 去群里发几条消息看看效果吧

## 插件说明

### 文件名格式说明
文件名以如下格式组成:  
`插件名`.`插件版本号`.`打包模式`.jar
* 插件名参考[下表](#插件列表)
* 插件版本号格式
    > 主版本号.子版本号.修正版本号
* 打包模式
    * `mirai` 依赖项被打入包内,体积大
    * `mirai2` 依赖项作为清单存储,安装时从库中下载,体积小

### 前置需求
部分插件运行需要存在某些插件才能正常运行  
例如：带有定时功能的插件，需要定时任务核心插件 存在才能正常运行  
目前前置插件需求关系如下图所示  
[![](https://mermaid.ink/img/pako:eNptjsEKwjAQRH8l7Nn-QA6CRS_iQfSay5psbW2yW9LNQUr_3QgKHjwMDDMPZhbwEggs3DNOvTldHLfIo2marTnKrc2CweOsP_GZ8iyMcee9FH43Ef34qPpXwwYS5YRDqCOLY2McaE-JHNhqA3VYojpwvFa0TAGVDmFQyWA7jDNtAIvK9ckerOZCX2g_YP2cPtT6AvXcRXk)](https://mermaid-js.github.io/mermaid-live-editor/edit#pako:eNptjsEKwjAQRH8l7Nn-QA6CRS_iQfSay5psbW2yW9LNQUr_3QgKHjwMDDMPZhbwEggs3DNOvTldHLfIo2marTnKrc2CweOsP_GZ8iyMcee9FH43Ef34qPpXwwYS5YRDqCOLY2McaE-JHNhqA3VYojpwvFa0TAGVDmFQyWA7jDNtAIvK9ckerOZCX2g_YP2cPtT6AvXcRXk)  
[![](https://mermaid.ink/img/pako:eNqrVkrOT0lVslJKL0osyFDwCYrJKy5NgnCezt73ZF93TJ6jgq7uyzkNL5Y16uraKTjF5KXmpSjpKOWmFuUmZqYA9VbH5CkoxCiVZKTmpsYoWQGZKalpiaU5JTFKMXm1QKWlBSmJJamuKZkl-UVKVmmJOcWpOkqJpSX5wZV5yUpWJUWlqTBFLpmJQNtzoapqAb3vOpo)](https://mermaid-js.github.io/mermaid-live-editor/edit#pako:eNqrVkrOT0lVslJKL0osyFDwCYrJKy5NgnCezt73ZF93TJ6jgq7uyzkNL5Y16uraKTjF5KXmpSjpKOWmFuUmZqYA9VbH5CkoxCiVZKTmpsYoWQGZKalpiaU5JTFKMXm1QKWlBSmJJamuKZkl-UVKVmmJOcWpOkqJpSX5wZV5yUpWJUWlqTBFLpmJQNtzoapqAb3vOpo)

### 插件列表
插件名|解释
-:|-|
AcgImage|随机图片
Bank|银行
Blackjack|21点
GroupWife|群老婆
JobBroadcast|定时任务核心
NekoGenGo|喵语翻译
PersonalAccount|个人账户
PokeEcho|戳一戳回复
SearchByImage|以图搜图
Tarot|每日塔罗