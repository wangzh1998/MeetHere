# MeetHere
## Use:
### 先创建mysql数据库，库名为project
### 再到src/main/resources下修改application.yml，将其中datasource下的username和password分别改为你的数据库名和密码。
### 运行一次项目
### 进入你的mysql数据库运行这三个脚本：
#### source Session.sql 
#### source constrait.sql 
#### source meeHereData.sql
### 第二次运行前，将application.yml中的jpa:hibernate:ddl-auto后面的create修改为update.

#登陆url示例：http://localhost:8080/login/signIn?username=钟晖&password=123456
