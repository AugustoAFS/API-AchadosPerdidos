@echo off
echo Carregando variaveis de ambiente...

REM MySQL Configuration
set MYSQL_URL=jdbc:mysql://localhost:3306/AP?useSSL=false&serverTimezone=America/Sao_Paulo&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=10&useServerPrepStmts=true&cachePrepStmts=true&prepStmtCacheSize=250&prepStmtCacheSqlLimit=2048&rewriteBatchedStatements=true&useTimezone=true&createDatabaseIfNotExist=true
set MYSQL_USERNAME=root
set MYSQL_PASSWORD=

REM MongoDB Configuration
set MONGODB_URI=mongodb+srv://User-AP-ADM:JkBrEmp04121518@chat-ap.vrtzujj.mongodb.net/?retryWrites=true&w=majority&appName=Chat-AP
set MONGODB_DATABASE=achadosperdidos

REM Google OAuth2 Configuration
set GOOGLE_CLIENT_ID=177007607025-sf6u6lfg3pjd2smq4daorpmnvjtbtheu.apps.googleusercontent.com
set GOOGLE_CLIENT_SECRET=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
set GOOGLE_REDIRECT_URI=http://localhost:8080/api/google-auth/callback

REM JWT Configuration
set JWT_SECRET_KEY=K8Q#mP9$vL2@nX5&jR7*hF4!wY3^cB6%tN1
set JWT_ISSUER=http://localhost:8080
set JWT_AUDIENCE=http://localhost:8080
set JWT_EXPIRY_MINUTES=60

echo Variaveis carregadas com sucesso!
echo.
echo Para executar a aplicacao, use:
echo mvn spring-boot:run
