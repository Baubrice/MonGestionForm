#!/bin/bash

# Test de connexion avec un utilisateur valide
echo "Test de connexion avec un utilisateur valide..."
curl -X POST http://localhost:3306/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"Admin123!"}' \
  -v

echo -e "\n\nTest de connexion avec des identifiants invalides..."
curl -X POST http://localhost:3306/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"wrongpassword"}' \
  -v

echo -e "\n\nTest d'accès à une ressource protégée sans token..."
curl -X GET http://localhost:3306/api/admin/test \
  -v

# Sauvegarder le token pour les tests suivants
TOKEN=$(curl -s -X POST http://localhost:3306/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"Admin123!"}' | jq -r '.token')

echo -e "\n\nTest d'accès à une ressource protégée avec token valide..."
curl -X GET http://localhost:3306/api/admin/test \
  -H "Authorization: Bearer $TOKEN" \
  -v 