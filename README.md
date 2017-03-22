#   敏捷开发基本控件之标题栏
![](HandyBase.png)
## 项目引用

***Step 2.添加maven地址到Project的build.gradle配置文件中***

```javascript

    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
```
***Step 2.添加compile引用到Module的build.gradle配置文件中***

```javascript

    dependencies {
        compile 'com.github.liujie045:HandyTitleBar:1.0'
    }
```

##  更新日志
***2017年3月22日 v1.0***

* 初始化敏捷开发控件之标题栏项目