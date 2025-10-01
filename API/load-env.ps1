# Script para carregar variáveis de ambiente
Write-Host "Carregando variáveis de ambiente..." -ForegroundColor Green

# MySQL Configuration
$env:MYSQL_URL = "jdbc:mysql://localhost:3306/AP?useSSL=false&serverTimezone=America/Sao_Paulo&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=10&useServerPrepStmts=true&cachePrepStmts=true&prepStmtCacheSize=250&prepStmtCacheSqlLimit=2048&rewriteBatchedStatements=true&useTimezone=true&createDatabaseIfNotExist=true"
$env:MYSQL_USERNAME = "root"
$env:MYSQL_PASSWORD = ""

# MongoDB Configuration
$env:MONGODB_URI = "mongodb+srv://User-AP-ADM:JkBrEmp04121518@chat-ap.vrtzujj.mongodb.net/?retryWrites=true&w=majority&appName=Chat-AP"
$env:MONGODB_DATABASE = "achadosperdidos"

# Google OAuth2 Configuration
$env:GOOGLE_CLIENT_ID = "177007607025-sf6u6lfg3pjd2smq4daorpmnvjtbtheu.apps.googleusercontent.com"
$env:GOOGLE_CLIENT_SECRET = "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"
$env:GOOGLE_REDIRECT_URI = "http://localhost:8080/api/google-auth/callback"

# JWT Configuration
$env:JWT_SECRET_KEY = "K8Q#mP9`$vL2@nX5&jR7*hF4!wY3^cB6%tN1"
$env:JWT_ISSUER = "http://localhost:8080"
$env:JWT_AUDIENCE = "http://localhost:8080"
$env:JWT_EXPIRY_MINUTES = "60"

Write-Host "Variáveis carregadas com sucesso!" -ForegroundColor Green
Write-Host ""
Write-Host "Para executar a aplicação, use:" -ForegroundColor Yellow
Write-Host "mvn spring-boot:run" -ForegroundColor Cyan
