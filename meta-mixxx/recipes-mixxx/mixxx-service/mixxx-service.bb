SUMMARY = "XDG autostart entry to launch Mixxx in Weston (Wayland)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://mixxx-autostart.desktop"

RDEPENDS:${PN} = "mixxx weston weston-init"

do_install() {
    install -d ${D}${sysconfdir}/xdg/autostart
    install -m 0644 ${WORKDIR}/mixxx-autostart.desktop \
        ${D}${sysconfdir}/xdg/autostart/mixxx.desktop
}

FILES:${PN} += "${sysconfdir}/xdg/autostart/mixxx.desktop"
