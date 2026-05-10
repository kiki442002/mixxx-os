SUMMARY = "Qt based DJ software"
HOMEPAGE = "http://mixxx.org/"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cfa28a12c2cc3a8ab616457a67c5d19b"
inherit qt6-cmake gtk-icon-cache

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

DEPENDS += " \
    qtbase \
    qtsvg \
    qttools-native \
    libusb1 \
    hidapi \
    upower \
    sqlite3 \
    libid3tag \
    taglib \
    libmad \
    faad2 \
    libmp4v2 \
    libogg \
    libvorbis \
    protobuf protobuf-native \
    fftw \
    portaudio-v19 \
    portmidi \
    rubberband \
    lame \
    wavpack \
    libmodplug \
    libebur128 \
    chromaprint \
    libdjinterop \
    libkeyfinder \
    qtdeclarative \
    qt5compat \
    soundtouch \
    wayland wayland-native qtwayland \
    virtual/libgles2 virtual/egl \
    microsoft-gsl \
"


# causes segfault trying to find debug libs
#    gperftools

SRC_URI = "git://github.com/mixxxdj/${BPN}.git;branch=main;protocol=https"
   
SRCREV = "3ebac449e7e5fe2a0186596657696e87ce8b0e56"
S = "${WORKDIR}/git"
PV = "2.5.6"

EXTRA_OECMAKE += " \
    -DSHOUTCAST=OFF \
    -DLOCALECOMPARE=OFF \
    -DFAAD=ON \
    -DQTKEYCHAIN=OFF \
"

 
TARGET_CPPFLAGS += "-D_GNU_SOURCE"

FILES:${PN} += " \
    ${datadir}/appdata \
    ${datadir}/metainfo \
"

do_configure:prepend() {
    # 1. On remplace OpenGL::GL par la cible générique de Qt6 qui gère l'abstraction
    find ${S} -name "CMakeLists.txt" -exec sed -i 's/OpenGL::GL/Qt6::OpenGL/g' {} +
    
    # 2. Si Mixxx cherche spécifiquement EGL, on le redirige vers la cible Qt6
    find ${S} -name "CMakeLists.txt" -exec sed -i 's/OpenGL::EGL/Qt6::Gui/g' {} +

    # 3. Optionnel mais conseillé : Forcer CMake à ignorer la recherche du package OpenGL standard
    # qui est souvent la source du problème de détection GLX
    find ${S} -name "CMakeLists.txt" -exec sed -i 's/find_package(OpenGL/find_package(Qt6 COMPONENTS OpenGL/g' {} +
}