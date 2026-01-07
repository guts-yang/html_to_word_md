# HTML to Multi-Format Converter Platform

这是一个功能完整的 Web 应用程序，提供将 HTML 文件转换为 Markdown、PDF 和 Word (.docx) 格式的服务。同时也保留了命令行工具 (CLI) 的功能。

## Web 应用程序功能

1.  **用户系统**
    *   用户注册与登录 (RESTful API + Basic Auth)
    *   个人仪表盘
2.  **文档转换**
    *   支持上传 HTML 文件
    *   支持转换为 PDF (带中文支持), Word, Markdown
    *   实时任务状态更新 (Polling)
    *   历史记录查看与下载
3.  **技术栈**
    *   后端: Spring Boot 3, Spring Security, Spring Data JPA, H2 Database
    *   前端: HTML5, Bootstrap 5, Vanilla JS (SPA 风格)
    *   构建: Maven

## 快速开始

### 1. 环境准备
确保您的系统已安装：
- Java JDK 17 或更高版本
- Maven 3.x 或 Maven Daemon (mvnd)

### 2. 构建项目
运行构建脚本 (自动检测 mvn/mvnd):
```cmd
.\build.bat
```

### 3. 运行 Web 应用
启动服务器:
```cmd
.\run_web.bat
```
或者手动运行:
```bash
java -jar target/html-converter-1.0-SNAPSHOT.jar
```

启动后，访问浏览器: [http://localhost:8080](http://localhost:8080)

### 4. 使用指南
1.  在登录页面点击 "Register" 注册新账号。
2.  登录后进入仪表盘。
3.  选择 HTML 文件并指定目标格式，点击 "Convert"。
4.  等待状态变为 "Completed"，点击 "Download" 下载结果。

---

## 命令行 (CLI) 使用
仍然可以通过命令行使用核心转换功能（注意：由于集成了 Spring Boot，jar 包体积变大，但 Main-Class 仍保留）：

```bash
# 转换单个文件
java -cp target/html-converter-1.0-SNAPSHOT.jar -Dloader.main=com.example.htmlconverter.cli.Main org.springframework.boot.loader.launch.PropertiesLauncher -i input.html -o output.pdf -f pdf
```
*(注: Spring Boot 打包后运行特定 Main 类比较复杂，建议主要使用 Web 模式)*

## 项目结构

- `src/main/java/com/example/htmlconverter`
    - `controller`: REST API 接口
    - `service`: 业务逻辑 (转换、用户)
    - `model`: 数据库实体
    - `repository`: 数据访问层
    - `converter`: 核心转换逻辑 (PDF/Word/MD)
    - `config`: 安全配置
- `src/main/resources/static`: 前端资源 (HTML/CSS/JS)

## CI/CD
项目包含 GitHub Actions 配置 `.github/workflows/maven.yml`，支持自动构建和测试。

## 注意事项
- **PDF 中文支持**：PDF 转换器尝试加载 Windows 系统字体 (SimSun, Microsoft YaHei)。Linux 环境下需配置字体路径。
- **数据库**：使用 H2 内存数据库，重启应用后数据会重置。
