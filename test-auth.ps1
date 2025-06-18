# Test de connexion avec un utilisateur valide
Write-Host "Test de connexion avec un utilisateur valide..."
$response = Invoke-RestMethod -Uri "http://localhost:3306/api/auth/login" `
    -Method Post `
    -ContentType "application/json" `
    -Body '{"username":"admin","password":"Admin123!"}'
Write-Host "Réponse : $($response | ConvertTo-Json)"

# Sauvegarder le token
$token = $response.token

# Test d'accès à une ressource protégée avec token valide
Write-Host "`nTest d'accès à une ressource protégée avec token valide..."
try {
    $protectedResponse = Invoke-RestMethod -Uri "http://localhost:3306/api/admin/test" `
        -Method Get `
        -Headers @{
            "Authorization" = "Bearer $token"
        }
    Write-Host "Réponse : $($protectedResponse | ConvertTo-Json)"
} catch {
    Write-Host "Erreur : $_"
}

# Test d'accès à une ressource protégée sans token
Write-Host "`nTest d'accès à une ressource protégée sans token..."
try {
    $noTokenResponse = Invoke-RestMethod -Uri "http://localhost:3306/api/admin/test" `
        -Method Get
    Write-Host "Réponse : $($noTokenResponse | ConvertTo-Json)"
} catch {
    Write-Host "Erreur : $_"
} 