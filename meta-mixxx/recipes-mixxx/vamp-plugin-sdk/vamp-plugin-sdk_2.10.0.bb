SUMMARY = "The Vamp audio analysis plugin system - SDK"
HOMEPAGE = "http://www.vamp-plugins.org/develop.html"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=af2765066122f0233483605ef6d299fa"

inherit autotools-brokensep pkgconfig

DEPENDS = "libsndfile1"

PV = "2.10.0"

SRC_URI = " \
    https://github.com/vamp-plugins/vamp-plugin-sdk/releases/download/vamp-plugin-sdk-v2.10/${BPN}-${PV}.tar.gz \
    file://0001-do-not-perform-test-it-tries-to-run-cross-binaries.patch \
"
SRC_URI[md5sum] = "848f7ac0227b5c783bee0dd7a5cb3642"
SRC_URI[sha256sum] = "aeaf3762a44b148cebb10cde82f577317ffc9df2720e5445c3df85f3739ff75f"

do_compile() {
    oe_runmake sdk plugins host rdfgen
}

do_install() {
    oe_runmake 'DESTDIR=${D}' 'INSTALL_SDK_LIBS=${libdir}' 'INSTALL_PLUGINS=${libdir}/vamp' 'INSTALL_PKGCONFIG=${libdir}/pkgconfig' install
}

FILES:${PN} += "${libdir}/vamp"
