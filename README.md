# Deploying a Spring Boot Application on AWS VM

## Prerequisites
- An AWS EC2 instance (Ubuntu)
- SSH access to the instance
- A Git repository containing the Spring Boot project
- A private key (`.pem`) file for SSH access

---

## 1. Connect to Your AWS VM
Use SSH to connect to your AWS EC2 instance. Replace `<your-key.pem>` with your actual key file and `<your-vm-ip>` with the public IP of your VM:

```bash
ssh -i /path/to/your-key.pem ubuntu@your-vm-ip
```

If prompted about authenticity, type `yes` and press **Enter**.

---

## 2. Update and Install Dependencies
Once logged in, update package lists and install required dependencies:

```bash
sudo apt update
sudo apt install openjdk-21-jdk git maven -y
```

Verify Java installation:
```bash
java -version
```
Expected output:
```
openjdk version "21" ...
```
Verify Maven installation:
```bash
mvn -version
```
Expected output:
```
Apache Maven ...
```

---

## 3. Clone the Spring Boot Project
Clone your GitHub repository into the VM:

```bash
git clone <repository-url>
```

Navigate to the project directory:
```bash
cd <project-folder>
```

---

## 4. Build the Application
If your project is a Maven project, build it using:

```bash
mvn clean package
```

After a successful build, a JAR file will be generated inside the `target/` directory.

---

## 5. Run the Application
Navigate to the `target/` folder and start the application:

```bash
java -jar target/*.jar
```

For example, if your JAR file is named `myapp-0.0.1-SNAPSHOT.jar`, run:
```bash
java -jar target/myapp-0.0.1-SNAPSHOT.jar
```

If your application requires configuration, edit the `application.properties` file before running.

---

## 6. Running the Application in the Background (Optional)
If you want your application to keep running after closing SSH:

```bash
nohup java -jar target/*.jar > app.log 2>&1 &
```

- To check logs:
  ```bash
  tail -f app.log
  ```
- To find the running process:
  ```bash
  ps aux | grep java
  ```
- To stop the application:
  ```bash
  kill <PID>
  ```
  (Replace `<PID>` with the actual process ID.)

---

## 7. Allow External Access (AWS Security Group Configuration)
By default, AWS blocks external traffic. To allow access:
1. Go to **AWS EC2 Console** → **Security Groups**.
2. Find the security group associated with your instance.
3. Click **Edit Inbound Rules**.
4. **Add Rule**:
   - **Type:** Custom TCP
   - **Port Range:** `8080` (or your app’s port)
   - **Source:** `0.0.0.0/0` (or restrict to your IP)
5. Save changes.

Now, your application should be accessible at:
```
http://your-vm-ip:8080
```

---




