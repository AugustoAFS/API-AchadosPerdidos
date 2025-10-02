# Script para executar a aplicação com variáveis de ambiente
Write-Host "Configurando e executando a aplicação..." -ForegroundColor Green

# Carregar variáveis de ambiente
& .\setup-env.ps1

# Executar aplicação
& .\mvnw.cmd spring-boot:run

