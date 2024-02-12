# Hello-World

### Install jdk21 on linux

```shell
wget https://download.java.net/openjdk/jdk21/ri/openjdk-21+35_linux-x64_bin.tar.gz
```
### Extract the tar file

```shell
tar -xzvf openjdk-21+35_linux-x64_bin.tar.gz
```
### To Execute the `java` executable
```shell
./jdk-21/bin/java --version
```


### Download hello-world executable from the github release latest tag (make sure you download the `.jar` file)

```shell
# Make sure you find the correct release version and replace v1.0.19 from the URL
wget https://github.com/samitkumarpatel/hello-world/releases/download/v1.0.19/hello-world-1.0.0-SNAPSHOT.jar
```
### To start the program
```shell
./jdk-21/bin/java -jar hello-world-1.0.0-SNAPSHOT.jar

```

### Made some request to the application

```shell
# This will invoke 1000 request but 100 in concurrent manner.
ab -n 1000 -c 100 <host>:<port>/
# ab -n 1000 -c 100 192.168.0.1:8080/
```