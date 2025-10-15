# springWorkspace
My springBoot Tutorial

# 🛒 Basic Server with OpenAI Integration

This project is a Spring Boot server that integrates with the OpenAI API.  
For security, API keys must be provided via **environment variables** and not hardcoded in source code.

---

## 🔑 Environment Setup

This project requires an **OpenAI API key**.

### 1. Set the Environment Variable

#### Windows PowerShell
```powershell
$env:OPENAI_API_KEY="your-real-key"; 
```
### Permanent (available in all future sessions):
```
setx OPENAI_API_KEY "your-real-key"
mvn spring-boot:run
```

### Linux / macOS (Bash or Zsh)

## Temporary (valid for current terminal session only):

```
export OPENAI_API_KEY=your-real-key
mvn spring-boot:run

```

### Validate $env key in windows

```
echo $env:OPENAI_API_KEY
```
### on Linux/macOS

```
echo $OPENAI_API_KEY
```

# Databse Refrence
## using Docker
### locate your docker-compose.yml in your terminal then call the following command
```
docker compose up -d
```
### this command will shows you a live list of running containers on your system.
```
docker ps
```
### you can enter the database admin by following the ports listed in the terminal and use the user name and password 
### that been set in your .env file
```
http://localhost:<and enter your admin port>
```

### you can use the following commands to apply any changes to the yml file or .env file
```
docker-compose down
docker-compose up -d
```
### Video Reference
[Watch Related youtube Video on my Youtube Channel](https://youtu.be/kgkd7eFV5ww?si=WU5vHE98GX9Nb9QU)