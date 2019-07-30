# JhBaseFrame
基础框架之界面及网络请求框架整理,内含网络以及工具类,不必重复引用

----

### 引入依赖方式如下:

#### 1. 全局Gradle

```Gradle
	allprojects {
	   	repositories {
			maven { url 'https://jitpack.io' } 
		}
	}
```
#### 2. Module Gradle
```Gradle
  android{
    
    // butterknife 用到了一些java8的一些特性,如静态lambda等,所以需要声明
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
  }

	dependencies {
	  implementation 'com.github.chengamin:SimpleBusinessFrame:1.0'
          //butterknife的模块,切记需要引入
          annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'
	}
```
----

### 具体使用如下

- 具体使用参考app下的login模块
