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
    googletest \
    qtdeclarative \
    qt5compat \
    soundtouch \
    wayland wayland-native\
    virtual/libgles2 virtual/egl \
    microsoft-gsl \
"

RDEPENDS:${PN} = "qtwayland"


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
    -DProtobuf_PROTOC_EXECUTABLE=${STAGING_BINDIR_NATIVE}/protoc \
    -DCMAKE_PROJECT_INCLUDE=${WORKDIR}/cmake-inject/protobuf-native-fix.cmake \
"

 
TARGET_CPPFLAGS += "-D_GNU_SOURCE -I${STAGING_INCDIR}"

FILES:${PN} += " \
    ${datadir}/appdata \
    ${datadir}/metainfo \
"

do_configure:prepend() {
    # Pre-create protobuf::protoc IMPORTED target pointing to the native x86 binary.
    # This runs via CMAKE_PROJECT_INCLUDE (after project() but before find_package),
    # so ProtobufConfig.cmake's "if(NOT TARGET protobuf::protoc)" guard is triggered
    # and the ARM binary path is never used as the command.
    mkdir -p ${WORKDIR}/cmake-inject
    echo "set(Protobuf_PROTOC_EXECUTABLE \"${STAGING_BINDIR_NATIVE}/protoc\" CACHE FILEPATH \"\" FORCE)" > ${WORKDIR}/cmake-inject/protobuf-native-fix.cmake
    echo "if(NOT TARGET protobuf::protoc)" >> ${WORKDIR}/cmake-inject/protobuf-native-fix.cmake
    echo "    add_executable(protobuf::protoc IMPORTED GLOBAL)" >> ${WORKDIR}/cmake-inject/protobuf-native-fix.cmake
    echo "endif()" >> ${WORKDIR}/cmake-inject/protobuf-native-fix.cmake
    echo "set_target_properties(protobuf::protoc PROPERTIES IMPORTED_LOCATION \"${STAGING_BINDIR_NATIVE}/protoc\")" >> ${WORKDIR}/cmake-inject/protobuf-native-fix.cmake

}