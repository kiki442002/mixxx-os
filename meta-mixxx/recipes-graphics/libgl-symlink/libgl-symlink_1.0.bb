SUMMARY = "libGL.so stub linking to libGLESv2 for Wayland-only systems"
DESCRIPTION = "On Wayland-only (no X11/GLX) systems, mesa does not build libGL.so. \
This recipe provides a libGL.so symlink to libGLESv2.so.2 so that \
applications that link -lGL can compile and run using the GLES backend."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

RDEPENDS:${PN} = "libgles2-mesa"

do_install() {
    install -d ${D}${libdir}
    # Runtime symlink: libGL.so.1 -> libGLESv2.so.2
    ln -sf libGLESv2.so.2 ${D}${libdir}/libGL.so.1
    # Dev symlink: libGL.so -> libGL.so.1
    ln -sf libGL.so.1 ${D}${libdir}/libGL.so
}

FILES:${PN} = "${libdir}/libGL.so.1"
FILES:${PN}-dev = "${libdir}/libGL.so"

INSANE_SKIP:${PN}-dev = "dev-elf"
INSANE_SKIP:${PN} = "dev-so"
