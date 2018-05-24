#   敏捷开发基本控件之标题栏

![](HandyBase.png)

## 中文说明

https://handy045.cn/2018/05/22/Android开发/强大的自定义标题栏：HandyTitleBar/

## 项目引用

***Step 1.添加maven地址到Project的build.gradle配置文件中***

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
        compile 'com.github.liujie045:HandyTitleBar:1.1.1'
    }
```
