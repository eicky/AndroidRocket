# AndroidRocket   
获取当前Activity的类名和包名

## 由来
每次接手一个别人的项目的时候，是不是感觉特别烦躁，为啥？

- 老板叫修改一个页面的界面，不知道当前的class文件在哪里

- 看前后页面的跳转逻辑，不知道在对应文件在哪里

通过查询百度，我们知道用命令行```adb shell dumpsys activity activities```可以查询，但是发现不方便，所以便有了```AndroidRocket```
<br/>

## 效果图

   ![截屏][1]

## 安装体验

[AndroidRocket.apk](https://github.com/eicky/AndroidRocket/blob/master/apk/app-debug.apk?raw=true)

## 使用方法
* 6.0 以上版本需要```悬浮窗权限```。所以打开```AndroidRocket```时，会进入权限管理页面。勾选```悬浮窗权限```才能正常使用。

  ![截屏][2]

* 然后开启辅助功能（无障碍功能） 注：部分手机需要手动打开设置进行对应的权限开启（如：小米、魅族）

  ![截屏][3]

* 返回app，点击```开启悬浮窗```按钮，即可使用（当然也可以在Log信息里面查看，Log的TAG为```Eicky```）
  ![截屏][4]

## 关于我

* Blog: [http://www.eicky.com][5]
* Email: eicky@eicky.com

## License

    Copyright 2017 Eicky

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

 [1]: https://github.com/eicky/AndroidRocket/blob/master/img/img1.png
 [2]: https://github.com/eicky/AndroidRocket/blob/master/img/img2.png
 [3]: https://github.com/eicky/AndroidRocket/blob/master/img/img3.png
 [4]: https://github.com/eicky/AndroidRocket/blob/master/img/img4.png
 [5]: http://www.eicky.com