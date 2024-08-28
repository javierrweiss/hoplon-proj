FROM ubuntu:latest

# Actualizar y instalar paquetes base
RUN apt-get update && apt-get install -y \
    curl \
    openjdk-21-jdk \
    tar \
    unzip \
    wget \
    git \
    npm \
    git \
    vim \
    sudo \
    build-essential

# Descargar e instalar Clojure
RUN curl -L -O https://github.com/clojure/brew-install/releases/latest/download/linux-install.sh \
    && chmod +x linux-install.sh \
    && sudo ./linux-install.sh  


# Configurar zsh como shell por defecto
ENV SHELL /bin/bash

# Crear un usuario
RUN useradd -m -s /bin/bash devuser


# Establecer el directorio de trabajo
WORKDIR /app

# Configurar el usuario por defecto
USER devuser

# Comando por defecto
CMD ["bash"]