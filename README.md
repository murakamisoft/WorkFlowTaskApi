# WorkflowTaskAPI

WorkflowTaskAPIは、タスク管理システムのためのRESTful APIです。このAPIは、タスクのCRUD操作を提供し、Apache Kafkaを利用してメッセージ駆動型の処理を行います。

## 目次

- [WorkflowTaskAPI](#workflowtaskapi)
  - [目次](#目次)
  - [機能](#機能)
  - [技術スタック](#技術スタック)
  - [セットアップ手順](#セットアップ手順)
  - [APIエンドポイント](#apiエンドポイント)
  - [ライセンス](#ライセンス)
  - [お問い合わせ](#お問い合わせ)

## 機能

- タスクの作成、取得、削除
- Kafkaを使用したメッセージのリスニング
- Oracle Databaseとの連携

## 技術スタック

- Java 17
- Spring Boot 3.2.10
- Spring Kafka
- MyBatis
- Oracle Database
- Apache Commons Lang
- Lombok

## セットアップ手順

1. リポジトリをクローンします。
   ```bash
   git clone https://github.com/murakamisoft/WorkFlowTaskApi.git
   cd WorkFlowTaskApi
   ```

2. 必要な依存関係をダウンロードします。
   ```bash
   ./gradlew build
   ```

3. Oracle Databaseの設定を `application.yml` に記入します。
   ```yaml
   spring:
     datasource:
       url: jdbc:oracle:thin:@localhost:1521/XEPDB1
       username: oracle_user
       password: pass
   ```

4. アプリケーションを起動します。
   ```bash
   ./gradlew bootRun
   ```

## APIエンドポイント

- `GET /tasks` - すべてのタスクを取得
- `POST /tasks` - 新しいタスクを作成
- `DELETE /tasks/{taskId}` - 指定されたIDのタスクを削除

## ライセンス

このプロジェクトはMITライセンスの下でライセンスされています。詳細はLICENSEファイルを参照してください。

## お問い合わせ

開発者: [のり](mailto:nori@vbminigame.sakura.ne.jp)
