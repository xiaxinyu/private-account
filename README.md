# Private Account 1.0.0

## 概述
- 个人账单管理系统：信用卡账单、工资、房租、医保、养老、失业保险等数据的查询与维护。
- 技术栈：Spring Boot 3、Thymeleaf、MyBatis-Plus、EasyUI、MySQL。

## 主要特性
- 表格查询：日期范围、类型、卡种、消费树、关键字筛选；列自适配、条纹行、单元格省略号。
- 路由统一：提供语义化路径 `/account/*`，兼容旧 `.jsp` 路由。
- 资源精简：移除历史 JSP 与 demo 资源，统一以模板 `.html` 渲染。

## 运行要求
- JDK `21`
- Maven `3.9+`
- MySQL `8.x` 并准备数据库 `private-account`

## 配置
- 应用配置：`src/main/resources/application.yml`
  - 数据库连接：`spring.datasource.url`、`username`、`password`
  - 移除了 `spring.mvc.view.prefix/suffix`，使用模板渲染

## 安装与启动
```bash
mvn -q -DskipTests clean package
java -jar target/private-account-1.0.0.jar
```
- 浏览器访问：`http://localhost:8080/index.html`

## 主要页面
- 信用卡账单：`/account/credit/credit_bill.html`
- 工资：`/account/salary`
- 房租：`/account/house-rent`
- 医保：`/account/medical`
- 养老：`/account/endowment`
- 失业：`/account/unemployment`

## 开发说明
- 前端样式：`/css/default/credit/CreditBill.css`
- 数据接口：`/src/main/java/com/account/web/rest/*`
- 控制器与路由：`/src/main/java/com/account/web/contoller/*`

## 发布说明
- 版本：`1.0.0`
- 构建产物：`target/private-account-1.0.0.jar`
- 变更摘要：
  - 查询栏布局统一与收紧，标签加粗，控件高度统一为 `24px`
  - 导航统一到 `/account/*` 路由，移除 JSP 视图配置与未用 JSP 文件
  - 保留旧路由兼容，模板全部使用 `.html`

## 许可证
- 仅供个人学习与使用

