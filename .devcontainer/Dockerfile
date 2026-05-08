FROM ubuntu:22.04

ENV DEBIAN_FRONTEND=noninteractive
ENV LANG=en_US.UTF-8
ENV LC_ALL=en_US.UTF-8

# Install Yocto dependencies
RUN apt-get update && apt-get install -y \
    gawk wget git diffstat unzip texinfo gcc build-essential \
    chrpath socat cpio python3 python3-pip python3-pexpect \
    xz-utils debianutils iputils-ping python3-git python3-jinja2 \
    libegl1-mesa libsdl1.2-dev xterm python3-subunit mesa-common-dev \
    zstd liblz4-tool file locales libacl1 \
    && locale-gen en_US.UTF-8 \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Install yocto-cooker
RUN pip3 install git+https://github.com/cpb-/yocto-cooker.git

# Create non-root build user (Yocto recommends not building as root)
RUN useradd -m -s /bin/bash yocto
USER yocto
WORKDIR /workspace

CMD ["bash"]
