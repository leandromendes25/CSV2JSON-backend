# 📄 CSV ↔ JSON Converter API

API REST desenvolvida em **Java com Spring Boot** para conversão de dados entre os formatos **CSV** e **JSON**, suportando estruturas dinâmicas.

---

## 🚀 Funcionalidades

* 🔄 Converter **JSON → CSV**
* 🔄 Converter **CSV → JSON**
* 📥 Download de CSV via endpoint
* 📤 Recebimento de CSV via requisição HTTP
* 🧠 Suporte a dados dinâmicos (sem estrutura fixa)

---

## 🛠️ Tecnologias

* Java 17+
* Spring Boot
* Spring Web
* Maven
* Postman (testes)

---

## 📌 Endpoints

### 🔹 Converter JSON → CSV

**POST** `/convert/json-to-csv`

#### 📥 Entrada (JSON)

```json
[
  { "nome": "leo", "idade": 15 },
  { "nome": "joão", "cidade": "SP" }
]
```

#### 📤 Saída (CSV)

```csv
nome,idade,cidade
leo,15,
joão,,SP
```

---

### 🔹 Converter CSV → JSON

**POST** `/convert/csv-to-json`

#### 📥 Entrada (text/plain)

```
nome,idade,cidade
leo,15,
joão,,SP
```

#### 📤 Saída (JSON)

```json
[
  { "nome": "leo", "idade": "15", "cidade": "" },
  { "nome": "joão", "idade": "", "cidade": "SP" }
]
```

---

## 🧪 Testando com Postman

### CSV → JSON

* Método: `POST`
* URL: `http://localhost:8080/convert/csv-to-json`
* Body:

    * Tipo: `raw`
    * Formato: `Text`
* Header:

```
Content-Type: text/plain
```

---

## 🧠 Regras de Conversão

### JSON → CSV

* Colunas são descobertas dinamicamente via `keySet()`
* Valores `null` são convertidos para vazio
* Valores com vírgula são encapsulados com aspas

---

### CSV → JSON

* Primeira linha define as colunas
* Linhas seguintes são convertidas em `Map<String, String>`
* Valores ausentes são tratados como `""`

---

## ⚠️ Limitações atuais

* Não trata aspas escapadas (`"valor, com vírgula"`)
* Não suporta CSV com delimitadores customizados
* Não faz inferência de tipos (tudo é String)

---

## 🚀 Melhorias futuras

* [ ] Suporte a arquivos `.csv` via upload (`MultipartFile`)
* [ ] Parser completo de CSV (tratamento de aspas)
* [ ] Exportação como arquivo para download
* [ ] Testes unitários com JUnit e Mockito
* [ ] Integração com frontend (Angular/React)

---

## 📂 Estrutura do Projeto

```
src/
 ├── controller/
 ├── service/
 ├── dto/
 └── util/
```

---

## 💡 Aprendizados

* Manipulação de dados dinâmicos com `Map`
* Construção de CSV manual
* Processamento de strings em Java
* Boas práticas de API REST
* Separação de responsabilidades

---

## 👨‍💻 Autor

Leandro Mendes

---

## 📄 Licença

Este projeto é livre para estudos e melhorias.
