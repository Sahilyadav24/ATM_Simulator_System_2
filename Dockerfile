FROM openjdk:21-jdk-slim

# Install Maven, Xvfb, AWT dependencies, and X11 libraries
RUN apt-get update && apt-get install -y \
    maven \
    xvfb \
    libxext6 \
    libxrender1 \
    libxtst6 \
    libxi6 \
    libxt6 \
    libxinerama1 \
    libfreetype6 \
    fonts-dejavu-core \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copy Maven configuration and resolve dependencies
COPY pom.xml .
RUN mvn dependency:resolve

# Copy all project files and build the jar
COPY . .
RUN mvn clean package

# Set environment for virtual display
ENV DISPLAY=:99

# Start Xvfb and run the ATM simulator jar
CMD ["sh", "-c", "Xvfb :99 -screen 0 1024x768x16 & java -jar target/atm_Simulator_System-1.0-SNAPSHOT.jar"]
