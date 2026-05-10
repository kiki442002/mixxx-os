SUMMARY = "Rubber Band Library library for audio time-stretching and pitch-shifting"
HOMEPAGE = "http://breakfastquay.com/rubberband/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=28f6289fba0406b8f491e9b2d5767488"

inherit meson pkgconfig

DEPENDS += " \
    fftw \
    libsamplerate0 \
    vamp-plugin-sdk \
    ladspa-sdk \
"

PV = "3.3.0"

SRC_URI = "https://github.com/breakfastquay/${BPN}/archive/refs/tags/v${PV}.tar.gz;downloadfilename=${BPN}-${PV}.tar.gz"
SRC_URI[md5sum] = "1125fda060b757bf1dc8de4b6a19f0fb"
SRC_URI[sha256sum] = "2bb837fe00932442ca90e185af8a468f7591df0c002b4a9e27a1bced1563ac84"

S = "${WORKDIR}/${BPN}-${PV}"

EXTRA_OEMESON = "-Dfft=fftw"

FILES:${PN} += " \
    ${datadir}/ladspa \
    ${libdir}/ladspa \
    ${libdir}/vamp \
"