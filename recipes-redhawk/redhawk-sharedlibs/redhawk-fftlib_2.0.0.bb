DESCRIPTION = "FFT Library"
HOMEPAGE = "http://www.redhawksdr.org"
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=d4d548a7833916fd41218fdc2430246e"

DEPENDS = "redhawk-bulkio redhawk-dsp fftwf"
RDEPENDS_${PN} = "redhawk-bulkio redhawk-dsp fftwf"
SRC_URI = "git://github.com/RedhawkSDR/fftlib.git;branch=master;protocol=git \
file://01_fix_dep_checks.patch \
"

# Track the latest
SRCREV = "2.0.0"

PR = "r0" 

S = "${WORKDIR}/git/cpp"

FILES_${PN} += "${SDRROOT}/*"

FILES_${PN}-dev +="${SDRROOT}/dom/deps/rh/fftlib/cpp/lib/libfftlib.so"
FILES_${PN}-dev +="${SDRROOT}/dom/deps/rh/fftlib/cpp/lib/pkgconfig"
FILES_${PN}-dev +="${SDRROOT}/dom/deps/rh/fftlib/include"

FILES_${PN}-dbg +="${SDRROOT}/dom/deps/rh/fftlib/cpp/lib/.debug/libfftlib.so.0.0.0"

FILES_${PN}-staticdev +="${SDRROOT}/dom/deps/rh/fftlib/cpp/lib/libfftlib.a"

inherit redhawk-sharedlib

export PKG_CONFIG_PATH.=":${STAGING_DIR_TARGET}/${SDRROOT}/dom/deps/rh/dsp/cpp/lib/pkgconfig"

