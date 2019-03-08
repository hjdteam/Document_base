# 每日一图壁纸可视化 项目文档
## 一、项目目标
    运用java语言，在eclipse或者vscode的环境下，使用Spring Boot快速搭建一套Web项目，使用它的框架来完成网页的壁纸读取功能，实现壁纸的可视化界面，并且从数据库中读出壁纸，然后显示到可视化界面上。

## 二、项目分工
|项目分工|责任人|
|:---|:---|
|代码编写| 杨磊、黄俊达|
|代码测试| 杨磊、黄俊达|
|文档编写| 杨磊、黄俊达|
|项目展示| 杨磊、黄俊达|

## 三、项目整体架构
![image loading failed](https://github.com/hjdteam/Document_base/blob/master/image_result/%E7%B3%BB%E7%BB%9F%E8%AE%BE%E8%AE%A1.png)
  
    小组成员按照项目整体架构设计，目前完成界面简单开发，数据结构设计、account、wallpaper、admin的接口编写及相关简单测试。由于时间关系，项目其他部分完成不够精细，但整体运行效果已见成效。本小组将于后期继续开发改进。详情可关注后续GitHub账号更新。

## 四、项目详细设计
1、利用Spring Boot快速搭建web项目框架；  
2、在wallpapers_h5_basic文件中实现前端界面设计；  
3、配置nodejs、setting.xml、maven等库和插件；  
4、搭建数据库，构建新表wallpaper；  
5、将本机下载的bing图片导入数据库wallpaper.sql中;  
6、连接数据库，分别编译运行Spring Boot文件和wallpaper文件终端下的npm run dev，访问页面，在页面中显示数据库图片信息。

## 五、项目软件
|类型|编译软件|
|:---|:---|
|开发工具|Vscode|
|Java版本|Jdk1.8.0_161|

|配置文件|版本|
|:---|:---|
|nodejs|v10.15.3|
|npm|6.4.1|
|maven|3.6.0|
|mysql|8.0.15|

## 六、项目成果
![image loading failed](https://github.com/hjdteam/Document_base/blob/master/image_result/page.jpg)
