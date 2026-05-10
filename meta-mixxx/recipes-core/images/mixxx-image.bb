DESCRIPTION = "Mixxx image with Weston (Wayland compositor) for Raspberry Pi 4"

inherit core-image

IMAGE_FEATURES:append = " splash ssh-server-dropbear weston hwcodecs"
EXTRA_IMAGE_FEATURES += "debug-tweaks"  
IMAGE_INSTALL:append = " \
    weston \
    weston-init \
    weston-examples \
    kernel-modules \
    mixxx \
    mixxx-service \
"
