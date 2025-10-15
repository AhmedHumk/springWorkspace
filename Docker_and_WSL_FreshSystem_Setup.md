# 🧩 Docker + WSL Setup Guide for a Fresh System (Windows & Linux)
Updated: 2025

This guide helps you set up Docker and WSL2 correctly from scratch on Windows or Linux systems.

---

## 🪟 WINDOWS SETUP (Docker Desktop + WSL2)

### 1. Install Docker Desktop
- Download from: https://www.docker.com/products/docker-desktop/
- Run the installer and **enable WSL 2 support**.
- Reboot if asked.

### 2. Update WSL
Open **PowerShell as Administrator**:
```powershell
wsl --update
```

### 3. Install Ubuntu (or other distro)
From PowerShell:
```powershell
wsl --install -d Ubuntu-22.04
```

Or install it from the **Microsoft Store**:
- Open Microsoft Store → Search “Ubuntu 22.04 LTS” → Install → Launch it.
- Create username and password.

### 4. Verify installation
In PowerShell:
```powershell
wsl -l -v
```

You should see:
```
  NAME              STATE           VERSION
* Ubuntu-22.04      Running         2
  docker-desktop    Running         2
```

If it’s not version 2:
```powershell
wsl --set-version Ubuntu-22.04 2
```

### 5. Enable Docker integration
1. Open **Docker Desktop**
2. Go to **Settings → Resources → WSL Integration**
3. Toggle ON your Ubuntu distro
4. Click **Apply & Restart**

### 6. Test Docker inside Ubuntu
Open Ubuntu (not docker-desktop terminal):
```bash
docker run hello-world
```

✅ You should see:  
“Hello from Docker! This message shows that your installation appears to be working correctly.”

---

## 🐧 LINUX SETUP (Ubuntu / Debian)

### 1. Update system
```bash
sudo apt update && sudo apt upgrade -y
```

### 2. Install dependencies
```bash
sudo apt install -y ca-certificates curl gnupg lsb-release
```

### 3. Add Docker’s GPG key
```bash
sudo mkdir -p /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg |   sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
```

### 4. Add Docker repository
```bash
echo   "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg]   https://download.docker.com/linux/ubuntu   $(lsb_release -cs) stable" |   sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
```

### 5. Install Docker Engine
```bash
sudo apt update
sudo apt install -y docker-ce docker-ce-cli containerd.io
```

### 6. Add user to docker group
```bash
sudo usermod -aG docker $USER
```
Then log out and back in or reboot.

### 7. Install Docker Compose
```bash
COMPOSE_VERSION=$(curl -s https://api.github.com/repos/docker/compose/releases/latest | grep '"tag_name":' | sed -E 's/.*"([^"]+)".*/\1/')
sudo curl -L "https://github.com/docker/compose/releases/download/${COMPOSE_VERSION}/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
```

### 8. Verify
```bash
docker --version
docker-compose --version
```

### 9. Enable Docker at boot
```bash
sudo systemctl enable docker
sudo systemctl start docker
```

✅ You’re ready to use Docker on Linux.

---

## 🧱 Optional: Basic Docker Test
Run:
```bash
docker run hello-world
```
If you see “Hello from Docker!”, everything is working fine.

---

## 🧩 Next Step
Once Docker works, create your first `docker-compose.yml` file for databases like MySQL, PostgreSQL, and Redis.

---

Author: ChatGPT (GPT-5)
For user setup reference (Windows & Linux, 2025)
