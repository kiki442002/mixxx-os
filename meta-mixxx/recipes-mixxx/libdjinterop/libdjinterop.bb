SUMMARY = "C++ library for interoperability with DJ software databases"
HOMEPAGE = "https://github.com/xsco/libdjinterop"
LICENSE = "LGPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6a600fd5e1d9cbde2d983680233ad02"

PV = "0.24.3"

SRC_URI = "https://github.com/xsco/libdjinterop/archive/refs/tags/${PV}.tar.gz;downloadfilename=${BPN}-${PV}.tar.gz"
SRC_URI[sha256sum] = "df41fe39bed9d16d27a3649d237b68edd2cdb6fc71a82cae5cd746d4e4ef6578"

S = "${WORKDIR}/${BPN}-${PV}"

DEPENDS = "sqlite3 zlib"

inherit cmake

EXTRA_OECMAKE += " \
    -DSYSTEM_SQLITE=ON \
    -DBUILD_SHARED_LIBS=ON \
    -DBUILD_TESTING=OFF \
"
